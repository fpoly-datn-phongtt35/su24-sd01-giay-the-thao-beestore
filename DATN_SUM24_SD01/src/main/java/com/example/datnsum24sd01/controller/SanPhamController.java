package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.controller.request.SanPhamRequest;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.service.SanPhamService;
import com.example.datnsum24sd01.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("sanPham", sanPhamService.getAllSanPham());
        return "admin-template/san_pham/san_pham";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("sanPhamRequest", new SanPhamRequest());
        return "admin-template/san_pham/them_san_pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") long id, Model model) {
        SanPham sanPham = this.sanPhamService.getOne(id);
        model.addAttribute("sanPhamUpdate", sanPham);
        return "admin-template/san_pham/sua_san_pham";
    }

    @PostMapping("/themSanPham")
    public String addSanPham(@ModelAttribute("sanPhamRequest") SanPhamRequest sanPhamRequest) {
        sanPhamService.add(sanPhamRequest);
        return "redirect:/admin/san-pham";
    }

    @PostMapping("/suaSanPham")
    public String update(@ModelAttribute("sanPhamRequest") SanPham sanPhamRequest) {
        this.sanPhamService.update(sanPhamRequest);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/thay-doi-trang-thai/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        sanPhamService.thayDoiTrangThai(id);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/xoaSanPham/{id}")
    public String deleteSanPham(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String note = sanPhamService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/san-pham";
    }

}

