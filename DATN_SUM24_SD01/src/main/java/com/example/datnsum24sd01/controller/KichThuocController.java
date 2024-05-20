package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/kich-thuoc")
public class KichThuocController {



    @GetMapping
    public String getAll(Model model) {
         return "admin-template/kich_thuoc/kich_thuoc";
    }

    @GetMapping("/view-add")
    public String viewAdd(
                          Model model) {
          return "admin-template/kich_thuoc/them_kich_thuoc";
    }



    @GetMapping("/view-update")
    public String viewUpdate( Model model) {
         return "admin-template/kich_thuoc/sua_kich_thuoc";
    }


}

