package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.ChiTietSanPhamRequest;
import com.example.datnsum24sd01.entity.ChiTietSanPham;

import java.util.List;

public interface ChiTietSanPhamService {

    List<ChiTietSanPham> getAllChiTietSanPham();

    ChiTietSanPham add(ChiTietSanPhamRequest chiTietSanPhamRequest);

    String delete(Long id);

    ChiTietSanPham getOne(Long id);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void thayDoiTrangThai(Long id);
}
