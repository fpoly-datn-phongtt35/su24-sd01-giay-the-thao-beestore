package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/ma-giam-gia")
public class MaGiamGiaController {


    @GetMapping()
    public String getAll(Model model) {
          return "admin-template/ma_giam_gia/ma_giam_gia";
    }




    @GetMapping("/view-add")
    public String viewAdd(

            Model model
    ) {
        return "admin-template/ma_giam_gia/them_ma_giam_gia";
    }


    @GetMapping("/view-update")
    public String viewUpdate(

            Model model
    ) {
       return "admin-template/ma_giam_gia/sua_ma_giam_gia";
    }


}
