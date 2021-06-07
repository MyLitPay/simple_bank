package main.service;

import main.api.dto.AccountDTO;
import main.api.request.NewAccountRequest;

import java.util.List;
import java.util.Set;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    void createNewAccount(NewAccountRequest request);
    void transfer(String fromAccNumber, String toAccNumber, String amount);
    List<String> getListOfCurrency();
    List<String> getListOfAccNumbers();
}
