package main.service;

import main.api.dto.AccountDTO;
import main.api.request.NewAccountRequest;
import main.api.request.TransferRequest;
import main.exception.AccountNotFoundException;
import main.exception.CurrencyNotTheSameException;
import main.exception.NotEnoughMoneyException;
import main.exception.SameAccountsException;
import main.model.Account;
import main.repo.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return getAccountDTOList(accounts);
    }

    @Override
    @Transactional
    public void createNewAccount(NewAccountRequest request) {
        Currency currency = Currency.getInstance(request.getCurrency());

        String stringBalance = request.getBalance().replaceAll(",", ".");
        double balance = Double.parseDouble(stringBalance);
        double roundBalance = getRoundedAmount(currency, balance);

        String accountNumber = RandomStringUtils.randomNumeric(20);

        Account account = new Account(accountNumber, currency.getCurrencyCode(), roundBalance);
        accountRepository.saveAndFlush(account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer(TransferRequest request) {
        if (request.getFromAccNumber().equals(request.getToAccNumber())) {
            throw new SameAccountsException("It's the same accounts");
        }

        Account fromAcc = accountRepository.findByAccountNumber(request.getFromAccNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Account toAcc = accountRepository.findByAccountNumber(request.getToAccNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        String correctStringAmount = request.getAmount().replaceAll(",", ".");
        double amount = Double.parseDouble(correctStringAmount);

        if (!fromAcc.getCurrency().equals(toAcc.getCurrency())) {
            throw new CurrencyNotTheSameException("Currency is not the same");
        }
        if (amount > fromAcc.getBalance()) {
            throw new NotEnoughMoneyException("Not enough money");
        }

        Currency currency = Currency.getInstance(fromAcc.getCurrency());

        fromAcc.setBalance(getRoundedAmount(currency, fromAcc.getBalance() - amount));
        toAcc.setBalance(getRoundedAmount(currency, toAcc.getBalance() + amount));
        accountRepository.saveAll(List.of(fromAcc, toAcc));
    }

    @Override
    public List<String> getListOfCurrency() {
        List<String> list = Currency.getAvailableCurrencies().stream()
                .map(Currency::getCurrencyCode)
                .sorted()
                .collect(Collectors.toList());
        list.set(0, list.get(list.indexOf("USD")));
        list.set(1, list.get(list.indexOf("RUB")));
        list.set(2, list.get(list.indexOf("EUR")));
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getListOfAccNumbers() {
        return accountRepository.findAll().stream()
                .map(a -> a.getAccountNumber() + " = " + a.getBalance() + " " + a.getCurrency())
                .collect(Collectors.toList());
    }

    private List<AccountDTO> getAccountDTOList(List<Account> accounts) {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account acc : accounts) {
            AccountDTO accountDTO = new AccountDTO(acc);
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

    private double getRoundedAmount(Currency currency, double amount) {
        int fraction = currency.getDefaultFractionDigits();
        BigDecimal round = new BigDecimal(amount).setScale(fraction, RoundingMode.HALF_DOWN);
        return round.doubleValue();
    }
}
