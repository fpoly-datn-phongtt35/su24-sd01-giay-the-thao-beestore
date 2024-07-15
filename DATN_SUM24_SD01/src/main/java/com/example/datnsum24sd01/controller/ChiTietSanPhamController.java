package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.controller.request.ChiTietSanPhamRequest;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.DeGiayService;
import com.example.datnsum24sd01.service.KichThuocService;
import com.example.datnsum24sd01.service.MauSacService;
import com.example.datnsum24sd01.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//chi tiết sản phẩm trong admin

@Controller
@RequestMapping("/admin/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    private DeGiayService deGiayService;
    private KichThuocService kichThuocService;
    private MauSacService mauSacService;
    private SanPhamService sanPhamService;

    public ChiTietSanPhamController(DeGiayService deGiayService, KichThuocService kichThuocService, MauSacService mauSacService,
                                    SanPhamService sanPhamService) {
        this.deGiayService = deGiayService;
        this.kichThuocService = kichThuocService;
        this.mauSacService = mauSacService;
        this.sanPhamService = sanPhamService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.getAllChiTietSanPham());
        return "admin-template/chi_tiet_san_pham/chi_tiet_san_pham";
    }


    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("chiTietSanPhamRequest", new ChiTietSanPhamRequest());
        model.addAttribute("listDeGiay", deGiayService.getAllDeGiay());
        model.addAttribute("listKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("listMauSac", mauSacService.getAllMauSac());
        model.addAttribute("listSanPham", sanPhamService.getAllSanPham());
        return "admin-template/chi_tiet_san_pham/them_chi_tiet_san_pham";
    }


    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") long id, Model model) {
        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamService.getOne(id);
        model.addAttribute("chiTietSanPhamUpdate", chiTietSanPham);
        model.addAttribute("listDeGiay", deGiayService.getAllDeGiay());
        model.addAttribute("listKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("listMauSac", mauSacService.getAllMauSac());
        model.addAttribute("listSanPham", sanPhamService.getAllSanPham());
        return "admin-template/chi_tiet_san_pham/sua_chi_tiet_san_pham";
    }

    @PostMapping("/themChiTietSanPham")
    public String addChiTietSanPham(@ModelAttribute("chiTietSanPhamRequest") ChiTietSanPhamRequest chiTietSanPhamRequest) {
        chiTietSanPhamService.add(chiTietSanPhamRequest);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @PostMapping("/suaChiTietSanPham")
    public String update(@ModelAttribute("chiTietSanPhamRequest") ChiTietSanPham chiTietSanPhamRequest) {
        this.chiTietSanPhamService.update(chiTietSanPhamRequest);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @GetMapping("/thay-doi-trang-thai/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        chiTietSanPhamService.thayDoiTrangThai(id);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @GetMapping("/xoaChiTietSanPham/{id}")
    public String deleteChiTietSanPham(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String note = chiTietSanPhamService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/chi-tiet-san-pham";
    }


}
