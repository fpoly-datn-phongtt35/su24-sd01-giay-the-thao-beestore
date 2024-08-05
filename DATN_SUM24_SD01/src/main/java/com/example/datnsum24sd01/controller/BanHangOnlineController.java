package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.custom.ChiTietSanPhamCustomerCt;
import com.example.datnsum24sd01.dto.Anhspcustom;
import com.example.datnsum24sd01.dto.ChiTietSanPhamDTO;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.service.BanHangOnlineCustomService;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.Degiayserviec;
import com.example.datnsum24sd01.service.DongSanPhamService;
import com.example.datnsum24sd01.service.GioHangChiTietService;
import com.example.datnsum24sd01.service.KhichThuocService;
import com.example.datnsum24sd01.service.MauSacService;
import com.example.datnsum24sd01.service.ThuongHieuSerivice;
import com.example.datnsum24sd01.worker.Spingsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/beestore")
public class BanHangOnlineController {



    @Autowired
    private GioHangChiTietService gioHangChiTietServicee;
    @Autowired
    private BanHangOnlineCustomService chiTietSanPhamService;

    @Autowired
    private ChiTietSanPhamResponsitory chiTietSanPhamRepository;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private Degiayserviec deGiayService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private KhichThuocService kichThuocService;


    @Autowired
    private DongSanPhamService dongSanPhamService;
    @Autowired
    private ThuongHieuSerivice thuongHieuService;
    Integer pageNo = 0;


    @GetMapping("/cua-hang")
    public String getAllShopCustomer(Model model, @RequestParam(name = "tenThuongHieu", defaultValue = "", required = false) List<String> tenThuongHieu,
                                     @RequestParam(name = "tenDongSanPham", defaultValue = "", required = false) List<String> tenDongSanPham,
                                     @RequestParam(name = "tenKichThuoc", defaultValue = "", required = false) List<String> tenKichThuoc,
                                     @RequestParam(name = "tenDeGiay", defaultValue = "", required = false) List<String> tenDeGiay,
                                     @RequestParam(name = "tenMauSac", defaultValue = "", required = false) List<String> tenMauSac,
                                     @RequestParam(name = "minGia", defaultValue = "0", required = false) Double minGia,
                                     @RequestParam(name = "maxGia", defaultValue = "", required = false) Double maxGia,
                                     @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                     @RequestParam(name = "tenSanPham", defaultValue = "") String tenSanPham,
                                     @RequestParam(name = "sortField", defaultValue = "0", required = false) String sortField) {
        Long id = spingsecurity.getCurrentUserId();
        Boolean checkSecurity=false;
        if (id != null) {
            checkSecurity= true;
        }
        if (maxGia == null) {
            maxGia = 1000000000000.0;
        }
//        model.addAttribute("checkSecurity",checkSecurity);
        Long idKhachHang = spingsecurity.getCurrentUserId();
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        int totalQuantity = listGioHangChiTiet.stream()
                .mapToInt(GioHangChiTiet::getSoLuong)
                .sum();
        model.addAttribute("totalQuantity", totalQuantity);
        Page<ChiTietSanPhamDTO> chiTietSanPhamPage = chiTietSanPhamService.findAllByCondition(tenThuongHieu, tenDongSanPham, tenKichThuoc, tenDeGiay, tenMauSac, minGia, maxGia, page, pageSize, sortField, tenSanPham);
        int totalPages = chiTietSanPhamPage.getTotalPages();
        long totalCount = chiTietSanPhamPage.getTotalElements();

        if (minGia > maxGia) {
            model.addAttribute("listThuongHieu", tenThuongHieu);
            model.addAttribute("listDongSanPham", tenDongSanPham);

            model.addAttribute("listDeGiay", tenDeGiay);
            model.addAttribute("listMauSac", tenMauSac);
            model.addAttribute("tenSanPham", tenSanPham);
            model.addAttribute("listKichThuoc", tenKichThuoc);
            model.addAttribute("minGia", minGia);
            model.addAttribute("maxGia", maxGia);
            model.addAttribute("sortField", sortField);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("dongSps", dongSanPhamService.getList());
            model.addAttribute("thuongHieus", thuongHieuService.getList());
            model.addAttribute("deGiays", deGiayService.getAllDeGiay());
            model.addAttribute("mauSacs", mauSacService.getAllMauSac());

            model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
            model.addAttribute("err", "mời bạn nhập đúng khoảng giá!");
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("listCTSP", chiTietSanPhamService.findAllByCondition(null, null, null, null, null, null, null,  page, pageSize, sortField, tenSanPham).stream().toList());
            List<ChiTietSanPhamCustomerCt> list3custom = chiTietSanPhamService.list3Custom();
            model.addAttribute("list3Custom", list3custom.stream().toList());
            List<ChiTietSanPhamCustomerCt> list3limited = chiTietSanPhamService.list3Limited();
            model.addAttribute("list3Limited", list3limited.stream().toList());
            model.addAttribute("page", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalCount", totalCount);
            return "customer-template/product";
        }


        model.addAttribute("tenSanPham", tenSanPham);
        model.addAttribute("listThuongHieu", tenThuongHieu);
        model.addAttribute("listDongSanPham", tenDongSanPham);

        model.addAttribute("listDeGiay", tenDeGiay);
        model.addAttribute("listMauSac", tenMauSac);
        model.addAttribute("listKichThuoc", tenKichThuoc);
        model.addAttribute("minGia", minGia);
        model.addAttribute("maxGia", maxGia);
        model.addAttribute("sortField", sortField);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", totalCount);

        model.addAttribute("index", pageNo + 1);
        model.addAttribute("listCTSP", chiTietSanPhamPage.getContent());
        List<ChiTietSanPhamCustomerCt> list3custom = chiTietSanPhamService.list3Custom();
        model.addAttribute("list3Custom", list3custom.stream().toList());
        List<ChiTietSanPhamCustomerCt> list3limited = chiTietSanPhamService.list3Limited();
        model.addAttribute("list3Limited", list3limited.stream().toList());

//        truyền vào filter
        model.addAttribute("dongSps", dongSanPhamService.getList());
        model.addAttribute("thuongHieus", thuongHieuService.getList());
        model.addAttribute("deGiays", deGiayService.getAllDeGiay());
        model.addAttribute("mauSacs", mauSacService.getAllMauSac());

        model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
        return "customer-template/product";
    }

    @GetMapping("/reset")
    private String preCustome() {
        return "redirect:/wingman/cua-hang";
    }


    private Spingsecurity spingsecurity = new Spingsecurity();

    @GetMapping("/trang-chu")
    public String gettrangchu(Model model) {
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
        return "customer-template/trangchu";
    }

    @GetMapping("/detaisp/{id}")
    public String detailCustomerSanPham(@PathVariable("id") Long id, Model model) {
        Long idKh=spingsecurity.getCurrentUserId();
        Long idKhachHang = spingsecurity.getCurrentUserId();
        Boolean checkSecurity=false;
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        int totalQuantity = listGioHangChiTiet.stream()
                .mapToInt(GioHangChiTiet::getSoLuong)
                .sum();
        model.addAttribute("totalQuantity", totalQuantity);
        if (idKh == null) {
            model.addAttribute("checkSecurity",checkSecurity);
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(id);
            model.addAttribute("spDetail", chiTietSanPham);
            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
        }else {
            checkSecurity= true;
            model.addAttribute("checkSecurity",checkSecurity);
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getById(id);
            model.addAttribute("spDetail", chiTietSanPham);
            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
            List<GioHangChiTiet> cartItems = gioHangChiTietService.getAll(idKh);

            // Tìm mục trong giỏ hàng dựa trên ID sản phẩm
            GioHangChiTiet gioHangChiTiet = cartItems.stream()
                    .filter(item -> item.getChiTietSanPham().getId().equals(id))
                    .findFirst()
                    .orElse(null);

            int soLuongTrongGioHang = (gioHangChiTiet != null) ? gioHangChiTiet.getSoLuong() : 0;
            model.addAttribute("soLuongTrongGioHang", soLuongTrongGioHang);
        }
        return "customer-template/detailproduct";
    }

    @GetMapping("/checkout")
    public String getthanhtoan(Model model) {

        return "customer-template/checkout";
    }
//    @GetMapping
//    public String hienthicarttrangchu(Model model) {
//        Long idKhachHang = spingsecurity.getCurrentUserId();
//        if(idKhachHang==null){
//            return "redirect:/login";
//        }
//
//        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietServicee.getAll();
//        int totalQuantity =listGioHangChiTiet.stream()
//                .mapToInt(GioHangChiTiet::getSoLuong) // Assuming getSoLuong() returns the quantity
//                .sum();
//        model.addAttribute("totalQuantity", totalQuantity);
//        return "customer-template/detailproduct";
//    }
}
