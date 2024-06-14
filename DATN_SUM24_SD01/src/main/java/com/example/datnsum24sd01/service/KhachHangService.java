package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.request.KhachHangRequest;

import java.util.List;
public interface KhachHangService {
    List<KhachHang> getAll();

    KhachHang add(KhachHangRequest khachHangRequest);

    String delete(Integer id);

    boolean checkSdtDuplicate(String sdt);

    boolean checkSdtDuplicate(String sdt, Integer id);

    boolean checkEmailDuplicate(String email);

    boolean checkEmailDuplicate(String email, Integer id);

    KhachHang getOne(Integer id);

    KhachHang update(KhachHang khachHang);
}
