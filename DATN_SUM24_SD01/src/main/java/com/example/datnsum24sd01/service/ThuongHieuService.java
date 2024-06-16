package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.ThuongHieuRequest;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.entity.ThuongHieu;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {
    List<ThuongHieu> getAllThuongHieu();

    ThuongHieu add(ThuongHieuRequest thuongHieuRequest);

    String delete(Long id);

    ThuongHieu getOne(Long id);

    ThuongHieu update(ThuongHieu thuongHieu);

    void thayDoiTrangThai(Long id);


}
