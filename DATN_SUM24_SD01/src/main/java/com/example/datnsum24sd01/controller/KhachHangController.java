package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.DiaChiGiaoHang;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.repository.DiaChiGiaoHangRepository;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.repository.KhachHangRepository;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiGiaoHangRepository diaChiGiaoHangRepository;

    @Autowired
    private EmailService emailService;


    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(name = "keyWord", required = false) String keyWord,
                         @RequestParam(name = "status", required = false) String status) {
        List<KhachHang> kh;

        if (keyWord == null || keyWord.isEmpty()) {
            if (status == null || status.isEmpty()) {
                kh = khachHangService.getAll();
            } else {
                kh = khachHangRepository.findByStatus(Integer.valueOf(status));
            }
        } else {
            String k = "%" + keyWord + "%";
            if (status == null || status.isEmpty()) {
                kh = khachHangRepository.findByStr(k);
            } else {
                kh = khachHangRepository.findByStrAndStatus(k, Integer.valueOf(status));
            }
        }

        model.addAttribute("list", kh);
        model.addAttribute("keyWord", keyWord);
        model.addAttribute("selectedStatus", status);
        return "admin-template/khach_hang/khach_hang";
    }


    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable Long id) {
        DiaChiGiaoHang diaChi = diaChiGiaoHangRepository.findDiaChiGiaoHangMacDinh(id);
        KhachHang khachHang = khachHangService.getOne(id);
        if (khachHang == null) {
            return "redirect:/admin/khach-hang";
        }
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("diaChi", diaChi);
        return "admin-template/khach_hang/sua_khach_hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("newKhachHang", new KhachHangRequest());
        return "admin-template/khach_hang/them_khach_hang";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("newKhachHang") KhachHangRequest khachHangRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin-template/khach_hang/them_khach_hang";
        }
        khachHangService.add(khachHangRequest);
        emailService.sendNewAccountKHEmail(khachHangRequest.getEmail(), khachHangRequest.getEmail(), khachHangRequest.getMatKhau());

        return "redirect:/admin/khach-hang";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model) {
        KhachHang updatedKhachHang = khachHangService.update(khachHang);
        if (updatedKhachHang != null) {
            return "redirect:/admin/khach-hang";
        } else {
            model.addAttribute("error", "Khách hàng không tồn tại.");
            return "admin-template/khach_hang/sua_khach_hang";
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String noti = khachHangService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", noti);
        return "redirect:/admin/khach-hang";
    }

    @GetMapping("/sdt/{sdt}")
    public ResponseEntity<Boolean> checkSdtDuplicate(@PathVariable String sdt) {
        boolean exists = khachHangService.checkSdtDuplicate(sdt);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean exists = khachHangService.checkEmailDuplicate(email);
        return ResponseEntity.ok(exists);
    }
}
