package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thongtincanhan")
public class ThongTinCaNhanController {

    @GetMapping()
    public String hienThithongtin(Model model) {

        return "customer-template/ThongTinTaiKhoan";
    }
}
