package com.example.datnsum24sd01.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.datnsum24sd01.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.responsitory.KhachHangRepository;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang add(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setTen(khachHangRequest.getTen());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setGioiTinh(khachHangRequest.getGioiTinh());
        khachHang.setSdt(khachHangRequest.getSdt());

        String matKhau = UUID.randomUUID().toString().substring(0, 10);
        khachHang.setMatKhau(matKhau);

        khachHang.setTrangThai((byte) 1);
        khachHang.setTichDiem(BigDecimal.ZERO);
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        khachHang.setNgayTao(currentDate);
        khachHang.setNgaySua(currentDate);

        KhachHang khachHangMa = khachHangRepository.save(khachHang);
        String ma = "KH" + khachHangMa.getId().toString();
        khachHangMa.setMa(ma);

        return khachHangRepository.save(khachHang);

    }

    @Override
    public String delete(Integer id) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(id);
        if (khachHangOptional.isPresent()) {
            khachHangRepository.deleteById(id);
            return "Thành Công!";
        } else {
            return "Không thể xóa khách hàng với id = " + id + ". Không tìm thấy khách hàng.";
        }
    }

    @Override
    public boolean checkSdtDuplicate(String sdt) {
        return khachHangRepository.existsKhachHangBySdt(sdt);
    }



    @Override
    public boolean checkEmailDuplicate(String email) {
        return khachHangRepository.existsKhachHangByEmail(email);
    }



    @Override
    public KhachHang getOne(Integer id) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        return khachHang.orElse(null);
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        Optional<KhachHang> existingKhachHangOptional = khachHangRepository.findById(khachHang.getId());
        if (existingKhachHangOptional.isPresent()) {
            KhachHang existingKhachHang = existingKhachHangOptional.get();
            khachHang.setMa(existingKhachHang.getMa());
            khachHang.setNgayTao(existingKhachHang.getNgayTao());
            LocalDateTime currentTime = LocalDateTime.now();
            Date currentDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
            khachHang.setNgaySua(currentDate);
            return khachHangRepository.save(khachHang);
        } else {
            return null;
        }
    }
}
