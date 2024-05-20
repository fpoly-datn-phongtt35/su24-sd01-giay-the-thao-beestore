package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {



    @GetMapping()
    public String getAll(Model model) {
        return "admin-template/thuong_hieu/thuong_hieu";
    }


    @GetMapping("/view-add-thuong-hieu")
    public String getViewAdd( Model model) {
         return "admin-template/thuong_hieu/them_thuong_hieu";
    }



    @GetMapping("/view-update")
    public String viewUpdate(
                             Model model) {
        return "admin-template/thuong_hieu/sua_thuong_hieu";
    }


}

