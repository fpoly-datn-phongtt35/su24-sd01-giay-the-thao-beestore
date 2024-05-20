package com.example.datnsum24sd01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//cua hàng
@Controller
@RequestMapping("/beestore")
public class ChiTietSanPhamCustomerController {



    @GetMapping("/cua-hang")
    public String getAllShopCustomer(Model model)  {
      return "customer-template/shop";
        }

    @GetMapping("/trang-chu")
    public String get3TrangChuCustomer(Model model) {
       return "customer-template/index";
    }

//    @GetMapping("/chi-tiet-san-pham/{id}")
//    public String detailCustomerSanPham(@PathVariable("id") Long id, Model model) {
//        Long idKh=principalKhachHang.getCurrentUserId();
//        Boolean checkSecurity=false;
//        if (idKh == null) {
//            model.addAttribute("checkSecurity",checkSecurity);
//            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(id);
//            model.addAttribute("spDetail", chiTietSanPham);
//            List<AnhCustomerCustom> listAnhdetail = chiTietSanPhamService.listAnhDetail(id);
//            model.addAttribute("listAnhDetail", listAnhdetail.stream().toList());
//            List<ChiTietSanPhamCustomerCustom> listRand = chiTietSanPhamService.list4Random();
//            model.addAttribute("listRandom", listRand.stream().toList());
//            List<ChiTietSanPhamCustomerCustom> listRand2 = chiTietSanPhamService.list4Random();
//            model.addAttribute("listRandom2", listRand2.stream().toList());
//            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
//        }else {
//            checkSecurity= true;
//            model.addAttribute("checkSecurity",checkSecurity);
//            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(id);
//            model.addAttribute("spDetail", chiTietSanPham);
//            List<AnhCustomerCustom> listAnhdetail = chiTietSanPhamService.listAnhDetail(id);
//            model.addAttribute("listAnhDetail", listAnhdetail.stream().toList());
//            List<ChiTietSanPhamCustomerCustom> listRand = chiTietSanPhamService.list4Random();
//            model.addAttribute("listRandom", listRand.stream().toList());
//            List<ChiTietSanPhamCustomerCustom> listRand2 = chiTietSanPhamService.list4Random();
//            model.addAttribute("listRandom2", listRand2.stream().toList());
//            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
//            List<GioHangChiTiet> cartItems = gioHangChiTietService.getAll(idKh);
//
//            // Tìm mục trong giỏ hàng dựa trên ID sản phẩm
//            GioHangChiTiet gioHangChiTiet = cartItems.stream()
//                    .filter(item -> item.getChiTietSanPham().getId().equals(id))
//                    .findFirst()
//                    .orElse(null);
//
//            int soLuongTrongGioHang = (gioHangChiTiet != null) ? gioHangChiTiet.getSoLuong() : 0;
//            model.addAttribute("soLuongTrongGioHang", soLuongTrongGioHang);
//        }
//
//        return "customer-template/detail";
//    }

}
