package com.example.datnsum24sd01.controller.ThongKe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {


    @GetMapping
    public String hienThi(Model model) {
        return "admin-template/thong_ke/thong_ke";
    }


}
