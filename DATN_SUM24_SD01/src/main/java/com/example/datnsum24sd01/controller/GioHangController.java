package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/beestore/cart")
public class GioHangController {

    @GetMapping
    public String cart(Model model) {

        return "customer-template/cart";
    }



    @GetMapping("/checkout")
    public String checkout(Model model) {

        return "customer-template/checkout";
    }



}
