package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {


    @GetMapping()
    public String getAll(Model model) {
         return "admin-template/nhan_vien/nhan_vien";
    }

    @GetMapping("/view-update")
    public String viewUpdate(Model model) {
         return "admin-template/nhan_vien/sua_nhan_vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
         return "admin-template/nhan_vien/them_nhan_vien";
    }




}
