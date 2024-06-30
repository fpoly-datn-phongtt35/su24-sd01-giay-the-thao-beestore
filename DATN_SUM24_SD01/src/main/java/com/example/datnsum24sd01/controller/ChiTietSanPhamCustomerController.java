package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.worker.PrincipalKhachHang;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//cua hàng
@Controller
@RequestMapping("/beestore")
public class ChiTietSanPhamCustomerController {

    private PrincipalKhachHang principalKhachHang = new PrincipalKhachHang();

    @GetMapping("/trang-chu")
    public String gettrangchu(Model model) {
        Long id = principalKhachHang.getCurrentUserId();
        Boolean checkSecurity=false;
        if (id != null) {
            checkSecurity= true;
        }
       return "customer-template/trangchu";
    }



}
