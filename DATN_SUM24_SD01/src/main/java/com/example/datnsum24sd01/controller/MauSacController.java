package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/mau-sac")
public class MauSacController {



    @GetMapping
    public String getAll(Model model){
        return "admin-template/mau_sac/mau_sac";
    }

    @GetMapping("/view-add")
    public String viewAdd(
            Model model){
         return "admin-template/mau_sac/them_mau_sac";
    }


    @GetMapping("/view-update")
    public String viewUpdate(
            Model model){
       return "admin-template/mau_sac/sua_mau_sac";
    }


}

