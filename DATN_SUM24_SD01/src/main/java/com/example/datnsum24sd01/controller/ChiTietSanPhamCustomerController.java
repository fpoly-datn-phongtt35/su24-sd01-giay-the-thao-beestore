package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//cua hàng
@Controller
@RequestMapping("/beestore")
public class ChiTietSanPhamCustomerController {


    @GetMapping("/cua-hang")
    public String getAllShopCustomer(Model model) {
        return "customer-template/shop";
    }

    @GetMapping("/trang-chu")
    public String get3TrangChuCustomer(Model model) {
        return "customer-template/index";
    }



}
