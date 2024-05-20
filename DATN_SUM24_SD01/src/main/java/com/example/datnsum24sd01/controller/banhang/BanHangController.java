package com.example.datnsum24sd01.controller.banhang;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/ban-hang")
public class BanHangController {


    @GetMapping
    public String hienThiBanHang(Model model) {

        return "admin-template/ban_hang/ban_hang";
    }
    @GetMapping("/login")
    public String login(Model model) {

        return "admin-template/login";
    }




}
