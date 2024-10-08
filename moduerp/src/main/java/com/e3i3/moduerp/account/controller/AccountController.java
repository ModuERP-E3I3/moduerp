package com.e3i3.moduerp.account.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.e3i3.moduerp.account.model.dto.AccountDTO;
import com.e3i3.moduerp.account.service.AccountService;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // account.jsp ��û ó
    @RequestMapping("/account.do")
    public String showAccount(Model model) {
        List<AccountDTO> accountList = accountService.getAllAccounts();
        model.addAttribute("accountList", accountList);
        return "account/account";
    }

}
