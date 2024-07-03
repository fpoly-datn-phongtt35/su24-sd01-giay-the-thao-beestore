package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.responsitory.SanPhamRepository;
import com.example.datnsum24sd01.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Override
    public List<SanPham> getList() {
        return sanPhamRepository.findAll();
    }
}
