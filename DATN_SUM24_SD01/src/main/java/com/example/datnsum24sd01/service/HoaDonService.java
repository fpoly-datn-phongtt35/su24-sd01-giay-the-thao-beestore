package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();


    BigDecimal phieuGiamGia(Long idHd);

}
