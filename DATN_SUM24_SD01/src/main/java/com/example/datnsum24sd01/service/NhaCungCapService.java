package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.NhaCungCapRequest;
import com.example.datnsum24sd01.entity.NhaCungCap;

import java.util.List;
import java.util.Optional;

public interface NhaCungCapService {
    List<NhaCungCap> getAllNhaCungCap();

    NhaCungCap add(NhaCungCapRequest nhaCungCapRequest);

    String delete(Long id);

    NhaCungCap getOne(Long id);

    NhaCungCap findById(Long id);

    NhaCungCap update(NhaCungCap nhaCungCap);

    void thayDoiTrangThai(Long id);

    boolean existByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);
}
