package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
  @Autowired
    ChiTietSanPhamResponsitory chiTietSanPhamResponsitory;

    @Override
    public List<ChiTietSanPham> getAll() {
        List<ChiTietSanPham> sortedList = chiTietSanPhamResponsitory.findAll().stream()
                .sorted(Comparator.comparing(ChiTietSanPham::getId).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }


    @Override
    public ChiTietSanPham getById(Long id) {
        return null;
    }



}
