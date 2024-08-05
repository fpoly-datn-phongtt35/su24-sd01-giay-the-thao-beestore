//package com.example.datnsum24sd01.controller;
//
//import com.example.datnsum24sd01.worker.Spingsecurity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
////cua hàng
//@Controller
//@RequestMapping("/beestore")
//public class ChiTietSanPhamCustomerController {
//
//    private Spingsecurity spingsecurity = new Spingsecurity();
//
//    @GetMapping("/trang-chu")
//    public String gettrangchu(Model model) {
//        Long id = spingsecurity.getCurrentUserId();
//        Boolean checkSecurity=false;
//        if (id != null) {
//            checkSecurity= true;
//        }
//       return "customer-template/trangchu";
//    }
//    @GetMapping("/cua-hang")
//    public String getcuahang(Model model) {
//
//        return "customer-template/product";
//    }
//    @GetMapping("/detaisp")
//    public String getspdetail(Model model) {
//
//        return "customer-template/detailproduct";
//    }
//    @GetMapping("/checkout")
//    public String getthanhtoan(Model model) {
//
//        return "customer-template/checkout";
//    }
//
//
//}
