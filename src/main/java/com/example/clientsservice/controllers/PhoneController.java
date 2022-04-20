package com.example.clientsservice.controllers;

import com.example.clientsservice.controllers.tools.BootstrapManager;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.PhoneService;
import com.example.clientsservice.services.data.qualifiers.PhoneServiceQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PhoneController {
    @Autowired
    @PhoneServiceQualifier
    private PhoneService phoneService;

    @GetMapping("/phones")
    public String phone(Model model){
        BootstrapManager.setBootstrapHead(model);
        List<Phone> phones = phoneService.findAll();
        model.addAttribute("phones", phones);
        if(phones.isEmpty()) model.addAttribute("no-phones", "");
        return "phone";
    }

    @PostMapping("add_phone")
    public String addPhone(Model model, @RequestParam("phone")String phone) {
        Phone phone1 = new Phone(0L, phone);
        phoneService.save(phone1);
        return "redirect:/phones";
    }
}
