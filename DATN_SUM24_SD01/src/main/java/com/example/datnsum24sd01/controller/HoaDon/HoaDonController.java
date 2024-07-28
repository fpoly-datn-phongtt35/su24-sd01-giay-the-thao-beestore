package com.example.datnsum24sd01.controller.HoaDon;


import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.service.BanHangService;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.HoaDonChiTietService;
import com.example.datnsum24sd01.service.HoaDonService;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {
    List<TrangThaiDonHang> list = new ArrayList<>(Arrays.asList(TrangThaiDonHang.CHO_XAC_NHAN, TrangThaiDonHang.HOAN_THANH.DANG_CHUAN_BI,
            TrangThaiDonHang.DANG_GIAO, TrangThaiDonHang.DA_GIAO, TrangThaiDonHang.HOAN_THANH, TrangThaiDonHang.DA_HUY, TrangThaiDonHang.XAC_NHAN_TRA_HANG));
    private final HoaDonService hoaDonService;
    private final HoaDonChiTietService hoaDonChiTietService;
//    private final GioHangChiTietService gioHangChiTietService;
//    private final ChiTietSanPhamService chiTietSanPhamService;
    private  final BanHangService banHangService;


    public HoaDonController(HoaDonService hoaDonService, HoaDonChiTietService hoaDonChiTietService,  BanHangService banHangService) {
        this.hoaDonService = hoaDonService;
        this.hoaDonChiTietService = hoaDonChiTietService;
//        this.gioHangChiTietService = gioHangChiTietService;
//        this.chiTietSanPhamService = chiTietSanPhamService1;
        this.banHangService = banHangService;

    }


    /**
     * Get All HoaDon
     *
     * @param model
     * @return
     */
    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("hoadons", hoaDonService.getAll());
        model.addAttribute("trangThais", list);
        model.addAttribute("endDate", LocalDate.now());
//        model.addAttribute("tenNhanVien", principalKhachHang.getCurrentNhanVienTen());
        return "admin-template/hoa_don/hoa_don";
    }

    /**
     * Get all HoaDon by trangThai
     *
     * @param model
     * @param trangThai
     * @return
     */
    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model,
                                 @PathVariable("trangThai") TrangThaiDonHang trangThai) {


        model.addAttribute("trangThais", list);
        model.addAttribute("endDate", LocalDate.now());
        model.addAttribute("hoadons", hoaDonService.getByTrangThai(trangThai));
        return "admin-template/hoa_don/hoa_don";
    }

    /**
     * Filter by Ngay Tao
     *
     * @param model
     * @param trangThai
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/filter")
    public String filterNgayTao(Model model,
                                @Param("trangThai") TrangThaiDonHang trangThai,
                                @Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        startDate = (startDate != null) ? startDate : LocalDate.of(2000, 1, 1);
        endDate = (endDate != null) ? endDate : LocalDate.now();

        model.addAttribute("trangThais", list);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("hoadons", hoaDonService.findHoaDonsByNgayTao(startDate, endDate));
        return "admin-template/hoa_don/hoa_don";
    }

    /**
     * Clear form
     *
     * @return
     */
    @GetMapping("/clear")
    public String clear() {
        return "redirect:/admin/hoa-don";
    }


    /**
     * get HoaDon offline
     *
     * @param model
     * @param idHd
     * @param trangThai
     * @return
     */
    @GetMapping("/chi-tiet-hoa-don/{id}")
    public String detaiOff(Model model,
                           @PathVariable("id") Long idHd,
                           @Param("trangThai") TrangThai trangThai) {

        model.addAttribute("hoaDon", hoaDonService.findById(idHd));
        model.addAttribute("hdcts", hoaDonChiTietService.getCtspById(idHd));
        model.addAttribute("trangThai", trangThai);
        return "admin-template/hoa_don/chi_tiet_hoa_don";
    }

    /**
     * get HoaDon Online
     *
     * @param model
     * @param idHd
     * @param trangThai
     * @return
     */
    @GetMapping("/chi-tiet-gio-hang/{id}")
    public String detailOn(Model model,
                           @PathVariable("id") Long idHd,
                           @Param("trangThai") TrangThai trangThai) {

        model.addAttribute("hoaDon", hoaDonService.findById(idHd));
//        model.addAttribute("ghcts", gioHangChiTietService.findGioHangChiTietById(idHd));
        model.addAttribute("giamGia",hoaDonService.maGiamGia(idHd));
        model.addAttribute("trangThai", trangThai);
        return "admin-template/hoa_don/chi_tiet_hd_online";
    }



}
