package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.SanPhamRequest;
import com.example.datnsum24sd01.entity.SanPham;

import java.util.List;


public interface SanPhamService {

    List<SanPham> getAllSanPham();

    SanPham add(SanPhamRequest sanPhamRequest);

    String delete(Long id);

    SanPham getOne(Long id);

    SanPham update(SanPham sanPham);

    void thayDoiTrangThai(Long id);
}

