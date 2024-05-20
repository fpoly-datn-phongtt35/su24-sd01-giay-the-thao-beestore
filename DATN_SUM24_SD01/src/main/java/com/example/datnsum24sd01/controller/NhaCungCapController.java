package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/nha-cung-cap")
public class NhaCungCapController {


    @GetMapping()
    public String getAll(Model model) {
       return "admin-template/nha_cung_cap/nha_cung_cap";
    }


    @GetMapping("/view-add-nha-cung-cap")
    public String getViewAdd( Model model) {
         return "admin-template/nha_cung_cap/them_nha_cung_cap";
    }




    @GetMapping("/view-update")
    public String viewUpdate(
                             Model model) {
       return "admin-template/nha_cung_cap/sua_nha_cung_cap";
    }


}

