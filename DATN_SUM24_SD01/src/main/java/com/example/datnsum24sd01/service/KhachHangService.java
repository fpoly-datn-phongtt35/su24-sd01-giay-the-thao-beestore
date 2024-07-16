package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.request.KhachHangRequest;

import java.util.List;
public interface KhachHangService {
    List<KhachHang> getAll();

    KhachHang add(KhachHangRequest khachHangRequest);

    String delete(Long id);

    boolean checkSdtDuplicate(String sdt);


    boolean checkEmailDuplicate(String email);


    KhachHang getOne(Long id);

    KhachHang update(KhachHang khachHang);
}
