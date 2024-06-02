package com.example.datnsum24sd01.security.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {
    @GetMapping("/login")

    public String LoginAdmin(Model model) {

        return "admin-template/login";
    }
    @GetMapping("/quen-mat-khau")
    public String QuenMatKhau(Model model) {

        return "admin-template/fogotpassword";

    }
}
