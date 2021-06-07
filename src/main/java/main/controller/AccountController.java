package main.controller;

import main.api.request.NewAccountRequest;
import main.api.request.TransferRequest;
import main.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "all";
    }

    @GetMapping("/new")
    public String newAccountPage(Model model) {
        model.addAttribute("currency", accountService.getListOfCurrency());
        return "new";
    }

    @PostMapping("/new")
    public void createNewAccount(@RequestBody NewAccountRequest request) {
        accountService.createNewAccount(request);
    }

    @GetMapping("/transfer")
    public String transferPage(Model model) {
        model.addAttribute("numbers", accountService.getListOfAccNumbers());
        return "transfer";
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest request) {
        accountService.transfer(request);
    }
}
