package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import com.example.datnsum24sd01.entity.PhieuGiamGia;

import java.math.BigDecimal;
import java.util.List;

public interface  BanHangService {
    List<HoaDon> getHoaDonCho();


    List<ChiTietSanPham> getChiTietSanPham();

    HoaDon themHoaDon(HoaDon hoaDon,Long idNhanVien);





}
