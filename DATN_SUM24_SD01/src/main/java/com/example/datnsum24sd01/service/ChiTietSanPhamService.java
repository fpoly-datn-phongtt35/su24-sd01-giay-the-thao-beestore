package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.ChiTietSanPham;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();
    ChiTietSanPham getById(Long id);
}
