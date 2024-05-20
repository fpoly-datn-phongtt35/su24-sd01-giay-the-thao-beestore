package com.example.datnsum24sd01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/san-pham")
@RequiredArgsConstructor
public class SanPhamController {

    @GetMapping()
    public String hienThi(Model model) {

        return "admin-template/san_pham/san_pham";
    }


    @GetMapping("/view-add-san-pham")
    public String getViewAdd(Model model) {

        return "admin-template/san_pham/them_san_pham";
    }



    @GetMapping("edit")
    public String editProduct( Model model) {

        return "admin-template/san_pham/sua_san_pham";
    }


}

