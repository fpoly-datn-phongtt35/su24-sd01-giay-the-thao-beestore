package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//chi tiết sản phẩm trong admin
@Controller
public class ChiTietSanPhamController {


    @GetMapping("/admin/chi-tiet-san-pham")
    public String getAll(Model model) {
        return "admin-template/chi_tiet_san_pham/chi_tiet_san_pham";
    }


    @GetMapping("/admin/chi-tiet-san-pham/view-add")
    public String viewAdd(Model model) {
        return "admin-template/chi_tiet_san_pham/them_chi_tiet_san_pham";
    }


    @GetMapping("/admin/chi-tiet-san-pham/view-update")
    public String viewUpdate(Model model) {
        return "admin-template/chi_tiet_san_pham/sua_chi_tiet_san_pham";
    }


}
