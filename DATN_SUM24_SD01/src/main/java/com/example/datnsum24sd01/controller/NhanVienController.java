package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.repository.NhanVienRepository;
import com.example.datnsum24sd01.request.NhanVienRequest;
import com.example.datnsum24sd01.service.NhanVienService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private  NhanVienRepository nhanVienRepository;


    @GetMapping()
    public String getAll(Model model) {
        List<NhanVien> nhanVienList = nhanVienService.getAll();
        model.addAttribute("nhanVien", nhanVienList);
        return "admin-template/nhan_vien/nhan_vien";
    }
    @GetMapping("/search")
    public String seach(Model model, @RequestParam("tSearch") String tSearch ) {

        List<NhanVien> nhanVienList = this.nhanVienRepository.findAllByTenContains(tSearch);
        model.addAttribute("nhanVien", nhanVienList);
        return "admin-template/nhan_vien/nhan_vien";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id
    ) {

        NhanVien nhanVien = this.nhanVienService.getOne(id);
        model.addAttribute("nhanVienForUpdating", nhanVien);

        return "admin-template/nhan_vien/sua_nhan_vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("nhanVienRequest", new NhanVienRequest());
        return "admin-template/nhan_vien/them_nhan_vien";
    }

    @PostMapping("/add")
    public String addd(@ModelAttribute("nhanVienRequest") NhanVienRequest nhanVienRequest) {
        nhanVienService.add(nhanVienRequest);
        return "redirect:/admin/nhan-vien"; //
    }
    @GetMapping("/thay-doi-trang/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        nhanVienService.thayDoiTrangThai(id);
        return "redirect:/admin/nhan-vien"; //
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("nhanVienRequest") NhanVien nhanVienRequest) {

        this.nhanVienService.update(nhanVienRequest);
        return "redirect:/admin/nhan-vien"; //
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String noti =
                nhanVienService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", noti);
        return "redirect:/admin/nhan-vien";
    }

    //    kiểm tra trùng lặp
    @GetMapping("/phone/{sdt}")
    public ResponseEntity<Boolean> checkPhoneDuplicate(@PathVariable String sdt) {
        boolean exists = nhanVienService.checkPhoneDuplicate(sdt);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Boolean> checkCccdDuplicate(@PathVariable String cccd) {
        boolean exists = nhanVienService.checkCccdDuplicate(cccd);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/cccd-update")
    public ResponseEntity<Boolean> checkCccdDuplicateUpdate(@RequestParam String cccd, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienByCanCuocCongDanAndIdNot(cccd, id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/sdt-update")
    public ResponseEntity<Boolean> checkSdtDuplicateUpdate(@RequestParam String sdt, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienBySdtAndIdNot(sdt, id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/email-update")
    public ResponseEntity<Boolean> checkEmailDuplicateUpdate(@RequestParam String email, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienByEmailAndIdNot(email, id);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean exists = nhanVienService.checkEmailDuplicate(email);
        return ResponseEntity.ok(exists);
    }


}
