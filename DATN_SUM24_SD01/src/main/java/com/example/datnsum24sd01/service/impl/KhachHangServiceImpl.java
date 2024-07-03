package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.GioHang;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.request.RegisterRequest;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.worker.AutoGenCodeRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangResponsitory khachHangResponsitory;
    @Autowired
    private EmailService emailService;

    @Override
    public List<KhachHang> getList() {
        return khachHangResponsitory.getListKhachHang();
    }

    @Override
    public boolean existsBySdt(String sdt) {
        return khachHangResponsitory.existsBySdt(sdt);
    }

    @Override
    public boolean existsByEmail(String email) {
        return khachHangResponsitory.existsByEmail(email);
    }

    @Override
    public boolean existsBySdtAndIdNot(String sdt, Long id) {
        return khachHangResponsitory.existsBySdtAndIdNot(sdt, id);
    }

    @Override
    public KhachHang registration(RegisterRequest request) {

        KhachHang khachHang = khachHangResponsitory.save(RegisterRequest.convertToEntity(request));
        khachHang.setMatKhau(AutoGenCodeRandom.genUUID());
        khachHangResponsitory.save(khachHang);
//        sendMailService.sendDangKy(khachHang.getEmail());
//        sendMailService.sendNewPassWord(khachHang.getEmail());
        emailService.sendNewAccountKHEmail(khachHang.getEmail(), khachHang.getEmail(),AutoGenCodeRandom.genUUID());

        return khachHang;
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangResponsitory.findAll();
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

        KhachHang khachHangMa = khachHangResponsitory.save(khachHang);
        String ma = "KH" + khachHangMa.getId().toString();
        khachHangMa.setMa(ma);

        return khachHangResponsitory.save(khachHang);
    }

    @Override
    public String delete(Long id) {
        Optional<KhachHang> khachHangOptional = khachHangResponsitory.findById(id);
        if (khachHangOptional.isPresent()) {
            khachHangResponsitory.deleteById(id);
            return "Thành Công!";
        } else {
            return "Không thể xóa khách hàng với id = " + id + ". Không tìm thấy khách hàng.";
        }
    }

    @Override
    public boolean checkSdtDuplicate(String sdt) {
      return   khachHangResponsitory.existsKhachHangBySdt(sdt);
    }

    @Override
    public boolean checkEmailDuplicate(String email) {
        return khachHangResponsitory.existsKhachHangByEmail(email);
    }

    @Override
    public KhachHang getOne(Long id) {
        Optional<KhachHang> khachHang = khachHangResponsitory.findById(id);
        return khachHang.orElse(null);
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        Optional<KhachHang> existingKhachHangOptional = khachHangResponsitory.findById(khachHang.getId());
        if (existingKhachHangOptional.isPresent()) {
            KhachHang existingKhachHang = existingKhachHangOptional.get();
            khachHang.setMa(existingKhachHang.getMa());
            khachHang.setNgayTao(existingKhachHang.getNgayTao());
            LocalDateTime currentTime = LocalDateTime.now();
            Date currentDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
            khachHang.setNgaySua(currentDate);
            return khachHangResponsitory.save(khachHang);
        } else {
            return null;
        }
    }
    }



