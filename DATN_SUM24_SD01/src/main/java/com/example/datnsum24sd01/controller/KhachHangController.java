package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {



    @GetMapping
    public String getAll(Model model) {
      return "admin-template/khach_hang/khach_hang";
    }



    @GetMapping("/view-update")
    public String viewUpdate( Model model) {
         return "admin-template/khach_hang/sua_khach_hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
     return "admin-template/khach_hang/them_khach_hang";
    }



}