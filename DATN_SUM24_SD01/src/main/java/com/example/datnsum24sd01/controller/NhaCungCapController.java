package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.controller.request.NhaCungCapRequest;
import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.service.NhaCungCapService;
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
@RequestMapping("/admin/nha-cung-cap")
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nhaCungCapService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("nhaCungCap", nhaCungCapService.getAllNhaCungCap());
        return "admin-template/nha_cung_cap/nha_cung_cap";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nhaCungCapRequest", new NhaCungCapRequest());
        return "admin-template/nha_cung_cap/them_nha_cung_cap";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") long id, Model model) {
        NhaCungCap nhaCungCap = this.nhaCungCapService.getOne(id);
        model.addAttribute("nhaCungCapUpdate", nhaCungCap);
        return "admin-template/nha_cung_cap/sua_nha_cung_cap";
    }

    @PostMapping("/themNhaCungCap")
    public String addNhaCungCap(@ModelAttribute("nhaCungCapRequest") NhaCungCapRequest nhaCungCapRequest) {
        nhaCungCapService.add(nhaCungCapRequest);
        return "redirect:/admin/nha-cung-cap";
    }

    @PostMapping("/suaNhaCungCap")
    public String update(@ModelAttribute("nhaCungCapRequest") NhaCungCap nhaCungCapRequest) {
        this.nhaCungCapService.update(nhaCungCapRequest);
        return "redirect:/admin/nha-cung-cap";
    }

    @GetMapping("/thay-doi-trang/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        nhaCungCapService.thayDoiTrangThai(id);
        return "redirect:/admin/nha-cung-cap";
    }

    @GetMapping("/xoaNhaCungCap/{id}")
    public String deleteNhaCungCap(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String note = nhaCungCapService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/nha-cung-cap";
    }

}

