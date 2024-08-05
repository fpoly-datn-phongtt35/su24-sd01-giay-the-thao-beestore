package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.dto.GioHangWrapper;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.DiaChi;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.request.DiaChiRequest;
import com.example.datnsum24sd01.service.BanHangonlinectservice;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.DiaChiService;
import com.example.datnsum24sd01.service.GioHangChiTietService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.service.PhieuGiamGiaService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/beestore/cart")
public class GioHangController {

    @Autowired
    private BanHangonlinectservice banHangCustomerService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private PhieuGiamGiaService maGiamGiaService;

    @Autowired
    private DiaChiService diaChiService;
    private GioHangWrapper gioHangWrapper;
    private Spingsecurity spingsecurity = new Spingsecurity();
    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @GetMapping
    public String cart(Model model) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        if(idKhachHang==null){
            return "redirect:/login";
        }


        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        int totalQuantity = listGioHangChiTiet.stream()
                .mapToInt(GioHangChiTiet::getSoLuong)
                .sum();
        model.addAttribute("totalQuantity", totalQuantity);


        return "customer-template/cart";
    }

    @PostMapping("/add/{id}")
    public String addCart(@PathVariable("id") Long idChiTietSanPham,
                          @ModelAttribute("gioHangChiTiet") GioHangChiTiet gioHangChiTiet,
                          @RequestParam("soLuong") Integer soLuong,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        if(idKhachHang==null){
            return "redirect:/login";
        }
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(idChiTietSanPham);
        banHangCustomerService.themVaoGioHang(idKhachHang,idChiTietSanPham, soLuong);
        model.addAttribute("success", "Thêm thành công");
        return "redirect:/beestore/detaisp/" + idChiTietSanPham;
    }

    @GetMapping("/addOne/{id}")
    public String addOne(@PathVariable("id") Long idChiTietSanPham,
                         @ModelAttribute("gioHangChiTiet") GioHangChiTiet gioHangChiTiet,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        if(idKhachHang==null){
            return "redirect:/login";
        }
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(idChiTietSanPham);
        banHangCustomerService.themVaoGioHang(idKhachHang, idChiTietSanPham, 1);
//        thongBao(redirectAttributes, "Thêm thành công", 1);
//        thongBao(redirectAttributes, "Thành công", 1);
        return "redirect:/beestore/detaisp/" + idChiTietSanPham;
    }
    @GetMapping("/xoa/{id}")
    public String xoaKhoiGio(@PathVariable("id") Long id) {
        banHangCustomerService.xoaKhoiGioHang(id);
        return "redirect:/beestore/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model,
                           @ModelAttribute("khachHang") KhachHang khachHang,
                           @RequestParam String options) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        KhachHang khachHang1 = khachHangService.getById(idKhachHang);
        if (khachHangService.getDiaChiByIdKhachHang(idKhachHang).isEmpty()) {
            model.addAttribute("diaChi2", new DiaChi());
            model.addAttribute("diaChi", khachHangService.getDiaChiByIdKhachHang(khachHang1.getId()));
            model.addAttribute("newDiaChi", new DiaChiRequest());

            String[] optionArray = options.split(",");
            List<String> listIdString = Arrays.asList(optionArray);
            gioHangWrapper = banHangCustomerService.findAllItemsById(listIdString);
            model.addAttribute("gioHangWrapper", gioHangWrapper);
            model.addAttribute("options", options);
            model.addAttribute("idKhachHang", idKhachHang);
            BigDecimal diemTichLuy = khachHang1.getTichDiem();
            model.addAttribute("diemTichLuy", diemTichLuy);
            System.out.println(diemTichLuy);
            long total = 0;
            for (GioHangChiTiet gh : gioHangWrapper.getListGioHangChiTiet()) {
                total += (long) gh.getDonGia().intValue() * gh.getSoLuong();
            }
            List<PhieuGiamGia> vouchers = maGiamGiaService.layList(total);
            model.addAttribute("vouchers", vouchers);
            return "customer-template/checkout";
        }

        model.addAttribute("diaChi2", khachHangService.getDiaChiByIdKhachHang(idKhachHang).get(0));
        model.addAttribute("diaChi", khachHangService.getDiaChiByIdKhachHang(khachHang1.getId()));
        model.addAttribute("newDiaChi", new DiaChiRequest());

        String[] optionArray = options.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        gioHangWrapper = banHangCustomerService.findAllItemsById(listIdString);
        model.addAttribute("gioHangWrapper", gioHangWrapper);
        model.addAttribute("options", options);
        model.addAttribute("idKhachHang", idKhachHang);
        BigDecimal diemTichLuy = khachHang1.getTichDiem();
        model.addAttribute("diemTichLuy", diemTichLuy);
        System.out.println(diemTichLuy);
        long total = 0;
        for (GioHangChiTiet gh : gioHangWrapper.getListGioHangChiTiet()) {
            total += (long) gh.getDonGia().intValue() * gh.getSoLuong();
        }
        model.addAttribute("tongTienCheck", total);
        List<PhieuGiamGia> vouchers = maGiamGiaService.getListHoatDong();
        model.addAttribute("vouchers", vouchers);
        return "customer-template/checkout";
    }
    @PostMapping("/dat-hang")
    public String datHang(
            @ModelAttribute("gioHangWrapper") GioHangWrapper gioHangWrapper,
            @RequestParam("diaChi2") String diaChi,
            @RequestParam("xaPhuong") String xa,
            @RequestParam("quanHuyen") String huyen,
            @RequestParam("thanhPho") String thanhPho,
            @RequestParam("sdt2") String sdt,
            @RequestParam("ghiChu") String ghiChu,
            @RequestParam("ten2") String ten,
            @RequestParam(name = "shippingFee") BigDecimal shippingFee,
            @RequestParam("tongTienHang") String tongTien,
            @RequestParam(name = "originAmount") BigDecimal totalAmount,
            @RequestParam(name = "voucherId", required = false, defaultValue = "0") Long selectedVoucherId,
            @RequestParam(name = "xuTichDiem", required = false, defaultValue = "false") String useAllPointsHidden,
            @RequestParam(name = "origin") BigDecimal diemTichLuy,
            @RequestParam(name = "tienGiamGia") BigDecimal tienGiamGia,
            Model model) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        String diaChiCuThe = diaChi + "," + xa + "," + huyen + "," + thanhPho;
        banHangCustomerService.datHangItems(gioHangWrapper, idKhachHang, ten, diaChiCuThe, sdt, ghiChu, shippingFee, BigDecimal.valueOf(Double.valueOf(tongTien)), totalAmount, tienGiamGia, selectedVoucherId, diemTichLuy, useAllPointsHidden);

        return "redirect:/beestore/cart/thankyou";
    }

    @PostMapping("/checkout/add-dia-chi/{idKhachHang}")
    public String suaDiaChi(@PathVariable("idKhachHang") String idKhachHang,
                            @ModelAttribute("newDiaChi") DiaChiRequest diaChiRequest,
                            @RequestParam("phuongXaID") String phuongXa,
                            @RequestParam("quanHuyenID") String quanHuyen,
                            @RequestParam("thanhPhoID") String thanhPho,
                            @RequestParam("options") String options,
                            Model model) {
        diaChiService.add(diaChiRequest, Long.valueOf(idKhachHang), thanhPho, quanHuyen, phuongXa);
        KhachHang khachHang = khachHangService.getById(Long.valueOf(idKhachHang));
        model.addAttribute("diaChi2", khachHangService.getDiaChiByIdKhachHang(Long.valueOf(idKhachHang)).get(0));
        model.addAttribute("diaChi", khachHangService.getDiaChiByIdKhachHang(khachHang.getId()));
        model.addAttribute("gioHangWrapper", gioHangWrapper);
        model.addAttribute("idKhachHang", idKhachHang);
        BigDecimal diemTichLuy = khachHang.getTichDiem();
        model.addAttribute("diemTichLuy", diemTichLuy);
        long total = 0;
        for (GioHangChiTiet gh : gioHangWrapper.getListGioHangChiTiet()) {
            total += (long) gh.getDonGia().intValue() * gh.getSoLuong();
        }
        List<PhieuGiamGia> vouchers = maGiamGiaService.layList(total);
        model.addAttribute("vouchers", vouchers);
        return "redirect:/beestore/cart/checkout?options=" + options;
    }

    @PostMapping("/checkout/sua-dia-chi/{idKhachHang}")
    public String addDiaChi(@Valid
                            @ModelAttribute("newDiaChi") DiaChiRequest diaChiRequest,
                            @RequestParam("phuongXa") String phuongXa,
                            @RequestParam("quanHuyen") String quanHuyen,
                            @RequestParam("thanhPho") String thanhPho,
                            @RequestParam("options") String options
    ) {
        diaChiService.update(diaChiRequest, thanhPho, quanHuyen, phuongXa);
        return "redirect:/beestore/cart/checkout?options=" + options;
    }

    @PostMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<String> updateQuantity(@RequestParam Long idGioHangChiTiet, @RequestParam Integer soLuong) {
        try {
            // Update quantity in the database using your service
            banHangCustomerService.updateGioHangChiTiet(idGioHangChiTiet, soLuong);
            return ResponseEntity.ok("Update successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating quantity: " + e.getMessage());
        }
    }
    @GetMapping("/thankyou")
    public String b() {
        return "customer-template/camon";
    }




}
