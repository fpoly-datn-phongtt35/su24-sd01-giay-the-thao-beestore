package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.DiaChiGiaoHang;
import com.example.datnsum24sd01.entity.QuanHuyen;
import com.example.datnsum24sd01.entity.TinhThanh;
import com.example.datnsum24sd01.entity.XaPhuong;
import com.example.datnsum24sd01.repository.*;
import com.example.datnsum24sd01.request.DiaChiGiaoHangRequest;
import com.example.datnsum24sd01.service.DiaChiGiaoHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/khach-hang/dia-chi-giao-hang")
public class DiaChiGiaoHangController {

    private final DiaChiGiaoHangService diaChiGiaoHangService;
    private final DiaChiGiaoHangRepository diaChiGiaoHangRepository;
    private final TinhThanhRepository tinhThanhRepository;
    private final QuanHuyenRepository quanHuyenRepository;
    private final XaPhuongRepository xaPhuongRepository;

    @Autowired
    public DiaChiGiaoHangController(DiaChiGiaoHangService diaChiGiaoHangService, DiaChiGiaoHangRepository diaChiGiaoHangRepository,
                                    TinhThanhRepository tinhThanhRepository, QuanHuyenRepository quanHuyenRepository,
                                    XaPhuongRepository xaPhuongRepository) {
        this.diaChiGiaoHangService = diaChiGiaoHangService;
        this.diaChiGiaoHangRepository = diaChiGiaoHangRepository;
        this.tinhThanhRepository = tinhThanhRepository;
        this.quanHuyenRepository = quanHuyenRepository;
        this.xaPhuongRepository = xaPhuongRepository;
    }

    @GetMapping("/{idKH}")
    public String getAllDiaChiGiaoHang(
            @PathVariable Long idKH,
            @RequestParam(name = "status", required = false) Integer status,
            Model model) {
        List<DiaChiGiaoHang> danhSachDiaChi;
        if (status == null) {
            danhSachDiaChi = diaChiGiaoHangRepository.findAllDiaChiGiaoHangUsing(idKH);

        } else if (status == 1) {
            danhSachDiaChi = diaChiGiaoHangRepository.findAllByKhachHangId(idKH);
        } else {
            danhSachDiaChi = diaChiGiaoHangRepository.findAllDiaChiGiaoHangDeleted(idKH);
        }
        model.addAttribute("selectedStatus", status);
        model.addAttribute("danhSachDiaChi", danhSachDiaChi);
        return "admin-template/dia_chi_giao_hang/dia_chi_giao_hang";
    }


    @GetMapping("/view-add/{idKH}")
    public String viewAddDiaChiGiaoHang(@PathVariable Long idKH, Model model) {
        DiaChiGiaoHangRequest diaChiGiaoHangRequest = new DiaChiGiaoHangRequest();
        model.addAttribute("idKH", idKH);
        model.addAttribute("diaChiGiaoHang", diaChiGiaoHangRequest);

        List<TinhThanh> danhSachTinhThanh = tinhThanhRepository.findAll();
        model.addAttribute("danhSachTinhThanh", danhSachTinhThanh);

        if (!danhSachTinhThanh.isEmpty()) {
            List<QuanHuyen> danhSachQuanHuyen = quanHuyenRepository.findByTinhThanhId(danhSachTinhThanh.get(0).getId());
            model.addAttribute("danhSachQuanHuyen", danhSachQuanHuyen);

            if (!danhSachQuanHuyen.isEmpty()) {
                List<XaPhuong> danhSachXaPhuong = xaPhuongRepository.findByQuanHuyenId(danhSachQuanHuyen.get(0).getId());
                model.addAttribute("danhSachXaPhuong", danhSachXaPhuong);
            }
        }

        return "admin-template/dia_chi_giao_hang/them_dia_chi_giao_hang";
    }

    @PostMapping("/add/{idKH}")
    public String addDiaChiGiaoHang(@PathVariable Long idKH, @Valid @ModelAttribute("diaChiGiaoHang") DiaChiGiaoHangRequest diaChiGiaoHangRequest,
                                    BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("idKH", idKH);
            List<TinhThanh> danhSachTinhThanh = tinhThanhRepository.findAll();
            model.addAttribute("danhSachTinhThanh", danhSachTinhThanh);

            if (!danhSachTinhThanh.isEmpty()) {
                List<QuanHuyen> danhSachQuanHuyen = quanHuyenRepository.findByTinhThanhId(danhSachTinhThanh.get(0).getId());
                model.addAttribute("danhSachQuanHuyen", danhSachQuanHuyen);

                if (!danhSachQuanHuyen.isEmpty()) {
                    List<XaPhuong> danhSachXaPhuong = xaPhuongRepository.findByQuanHuyenId(danhSachQuanHuyen.get(0).getId());
                    model.addAttribute("danhSachXaPhuong", danhSachXaPhuong);
                }
            }
            return "admin-template/dia_chi_giao_hang/them_dia_chi_giao_hang";
        }
        diaChiGiaoHangRequest.setIdKhachHang(idKH);
        diaChiGiaoHangService.add(diaChiGiaoHangRequest, idKH);
        return "redirect:/admin/khach-hang/dia-chi-giao-hang/" + idKH;
    }




    @GetMapping("/view-update/{id}")
    public String viewUpdateDiaChiGiaoHang(@PathVariable Long id, Model model) {
        Optional<DiaChiGiaoHang> diaChiOptional = diaChiGiaoHangRepository.findById(id);

        DiaChiGiaoHang diaChiGiaoHang = diaChiOptional.get();
        model.addAttribute("diaChiGiaoHang", diaChiGiaoHang);

        List<TinhThanh> danhSachTinhThanh = tinhThanhRepository.findAll();
        model.addAttribute("danhSachTinhThanh", danhSachTinhThanh);

        List<QuanHuyen> danhSachQuanHuyen = quanHuyenRepository.findByTinhThanhId(diaChiGiaoHang.getTinhThanh().getId());
        model.addAttribute("danhSachQuanHuyen", danhSachQuanHuyen);

        List<XaPhuong> danhSachXaPhuong = xaPhuongRepository.findByQuanHuyenId(diaChiGiaoHang.getQuanHuyen().getId());
        model.addAttribute("danhSachXaPhuong", danhSachXaPhuong);

        return "admin-template/dia_chi_giao_hang/sua_dia_chi_giao_hang";

    }


    @PostMapping("/update/{id}")
    public String updateDiaChiGiaoHang(@PathVariable Long id, @Valid @ModelAttribute("diaChiGiaoHang") DiaChiGiaoHangRequest diaChiGiaoHangRequest,
                                       BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("diaChiGiaoHang", diaChiGiaoHangRequest);

            List<TinhThanh> danhSachTinhThanh = tinhThanhRepository.findAll();
            model.addAttribute("danhSachTinhThanh", danhSachTinhThanh);

            if (!danhSachTinhThanh.isEmpty()) {
                List<QuanHuyen> danhSachQuanHuyen = quanHuyenRepository.findByTinhThanhId(diaChiGiaoHangRequest.getTinhThanhId());
                model.addAttribute("danhSachQuanHuyen", danhSachQuanHuyen);

                if (!danhSachQuanHuyen.isEmpty()) {
                    List<XaPhuong> danhSachXaPhuong = xaPhuongRepository.findByQuanHuyenId(diaChiGiaoHangRequest.getQuanHuyenId());
                    model.addAttribute("danhSachXaPhuong", danhSachXaPhuong);
                }
            }

            return "admin-template/dia_chi_giao_hang/sua_dia_chi_giao_hang";
        }

        DiaChiGiaoHang updatedDiaChiGiaoHang = diaChiGiaoHangService.update(diaChiGiaoHangRequest);
        updatedDiaChiGiaoHang.setId(diaChiGiaoHangRequest.getId());
        if (updatedDiaChiGiaoHang != null) {
            return "redirect:/admin/khach-hang/dia-chi-giao-hang/" + updatedDiaChiGiaoHang.getKhachHang().getId();
        } else {
            redirectAttributes.addFlashAttribute("error", "Địa chỉ không tồn tại.");
            return "redirect:/admin/khach-hang/dia-chi-giao-hang";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<DiaChiGiaoHang> diaChiOptional = diaChiGiaoHangRepository.findById(id);
        if (diaChiOptional.isPresent()) {
            DiaChiGiaoHang diaChiGiaoHang = diaChiOptional.get();
            diaChiGiaoHang.setTrangThai(2);
            diaChiGiaoHangRepository.save(diaChiGiaoHang);

            Long idKh = diaChiGiaoHang.getKhachHang().getId();
            redirectAttributes.addAttribute("idKh", idKh);
            return "redirect:/admin/khach-hang/dia-chi-giao-hang/{idKh}";
        } else {
            return "Không thể cập nhật trạng thái với id = " + id + ". Không tìm thấy đối tượng.";
        }
    }


    @GetMapping("/api/quan-huyen/{tinhThanhId}")
    @ResponseBody
    public List<QuanHuyen> getQuanHuyenByTinhThanhId(@PathVariable Long tinhThanhId) {
        return quanHuyenRepository.findByTinhThanhId(tinhThanhId);
    }

    @GetMapping("/api/xa-phuong/{quanHuyenId}")
    @ResponseBody
    public List<XaPhuong> getXaPhuongByQuanHuyenId(@PathVariable Long quanHuyenId) {
        return xaPhuongRepository.findByQuanHuyenId(quanHuyenId);
    }
}
