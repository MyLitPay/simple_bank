package main.api.dto;

import main.model.Account;

public class AccountDTO {
    private String accountNumber;
    private String currency;
    private double balance;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.currency = account.getCurrency();
        this.balance = account.getBalance();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
