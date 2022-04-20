package com.example.clientsservice.controllers;

import com.example.clientsservice.controllers.tools.BootstrapManager;
import com.example.clientsservice.models.Account;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.qualifiers.AccountServiceQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    @AccountServiceQualifier
    private AccountService accountService;

    @GetMapping("/accounts")
    public String account(Model model){
        BootstrapManager.setBootstrapHead(model);
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        if(accounts.isEmpty()) model.addAttribute("no-accounts", "");
        return "account";
    }

    @PostMapping("add_account")
    public String addAccount(Model model, @RequestParam("amount")int amount){
        Account acc = new Account(0L, amount);
        accountService.save(acc);
        return "redirect:/accounts";
    }
}
