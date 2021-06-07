package main.service;

import main.api.dto.AccountDTO;
import main.api.request.NewAccountRequest;
import main.api.request.TransferRequest;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    void createNewAccount(NewAccountRequest request);
    void transfer(TransferRequest request);
    List<String> getListOfCurrency();
    List<String> getListOfAccNumbers();
}
