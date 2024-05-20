package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/de-giay")
public class DeGiayController {


    @GetMapping
    public String getAll(
            Model model
    ) {
        return "admin-template/de_giay/de_giay";
    }

    @GetMapping("/view-add")
    public String viewAdd(

            Model model
    ) {
        return "admin-template/de_giay/them_de_giay";
    }

    @GetMapping("/view-update")
    public String viewUpdate(

            Model model) {
        return "admin-template/de_giay/sua_de_giay";
    }


}

