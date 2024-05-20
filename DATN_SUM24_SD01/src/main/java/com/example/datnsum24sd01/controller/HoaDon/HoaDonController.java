package com.example.datnsum24sd01.controller.HoaDon;


import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {


    @GetMapping
    public String getAll(Model model) {

        return "admin-template/hoa_don/hoa_don";
    }


    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model) {

        return "admin-template/hoa_don/hoa_don";
    }



    @GetMapping("/chi-tiet-hoa-don")
    public String hoadontaiquay(Model model) {

        return "admin-template/hoa_don/chi_tiet_hoa_don";
    }


    @GetMapping("/chi-tiet-gio-hang")
    public String hoadononline(Model model) {

        return "admin-template/hoa_don/chi_tiet_hd_online";
    }


}
