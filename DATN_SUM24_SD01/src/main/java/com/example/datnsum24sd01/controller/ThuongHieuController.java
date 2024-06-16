package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.controller.request.ThuongHieuRequest;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.service.ThuongHieuService;
import com.example.datnsum24sd01.service.ThuongHieuService;
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
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {


    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("thuongHieu", thuongHieuService.getAllThuongHieu());
        return "admin-template/thuong_hieu/thuong_hieu";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("thuongHieuRequest", new ThuongHieuRequest());
        return "admin-template/thuong_hieu/them_thuong_hieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") long id, Model model) {
        ThuongHieu thuongHieu = this.thuongHieuService.getOne(id);
        model.addAttribute("thuongHieuUpdate", thuongHieu);
        return "admin-template/thuong_hieu/sua_thuong_hieu";
    }

    @PostMapping("/themThuongHieu")
    public String addThuongHieu(@ModelAttribute("thuongHieuRequest") ThuongHieuRequest thuongHieuRequest) {
        thuongHieuService.add(thuongHieuRequest);
        return "redirect:/admin/thuong-hieu";
    }

    @PostMapping("/suaThuongHieu")
    public String update(@ModelAttribute("thuongHieuRequest") ThuongHieu thuongHieuRequest) {
        this.thuongHieuService.update(thuongHieuRequest);
        return "redirect:/admin/thuong-hieu";
    }

    @GetMapping("/thay-doi-trang/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        thuongHieuService.thayDoiTrangThai(id);
        return "redirect:/admin/thuong-hieu";
    }

    @GetMapping("/xoaThuongHieu/{id}")
    public String deleteThuongHieu(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String note = thuongHieuService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/thuong-hieu";
    }


}

