//package main.controller;
//
//import main.api.dto.AccountDTO;
//import main.api.request.NewAccountRequest;
//import main.service.AccountService;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/acc")
//public class AccountRestController {
//    final AccountService accountService;
//
//    public AccountRestController(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
////    @GetMapping("/all")
////    public List<AccountDTO> getAllAccounts() {
////        List<AccountDTO> accounts = accountService.getAllAccounts();
////        return accounts;
////    }
//
//    @PostMapping("/new")
//    public void createNewAccount(@RequestBody NewAccountRequest request) {
//        System.out.println(request.getBalance());
//        System.out.println(request.getCurrency());
//        accountService.createNewAccount(request);
//    }
//
//    @PutMapping("/transfer")
//    public void transfer(@RequestParam String fromAccNumber,
//                         @RequestParam String toAccNumber,
//                         @RequestParam String amount) {
//        accountService.transfer(fromAccNumber, toAccNumber, amount);
//    }
//}
