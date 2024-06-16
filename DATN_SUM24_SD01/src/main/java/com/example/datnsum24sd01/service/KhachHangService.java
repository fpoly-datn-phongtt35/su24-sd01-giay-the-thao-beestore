package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.request.RegisterRequest;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KhachHangService {
    boolean existsBySdt(String sdt);

    boolean existsByEmail(String email);

    boolean existsBySdtAndIdNot(String sdt,Long id);
    KhachHang registration(RegisterRequest khachHang);

}
