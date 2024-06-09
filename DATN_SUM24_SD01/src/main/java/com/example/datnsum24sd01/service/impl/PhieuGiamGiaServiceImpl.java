package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import com.example.datnsum24sd01.request.PhieuGiamGiaRequest;
import com.example.datnsum24sd01.responsitory.PhieuGiamGiaResponsitory;
import com.example.datnsum24sd01.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {
    @Autowired
    PhieuGiamGiaResponsitory responsitory;

    @Override
    public List<PhieuGiamGia> getAll() {
        return responsitory.findAll().stream()
                .sorted(Comparator.comparing(PhieuGiamGia::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<PhieuGiamGia> getListHoatDong() {
        return responsitory.listphieuGiamGiaHoatDong();
    }

    @Override
    public List<PhieuGiamGia> getByTrangThai(TrangThaiPhieuKhuyenMai trangThaiKhuyenMai) {
        return responsitory.getAllByTrangThai(trangThaiKhuyenMai).stream()
                .sorted(Comparator.comparing(PhieuGiamGia::getId).reversed())
                .collect(Collectors.toList());
    }


    @Override
    public void updateTrangThai() {
        responsitory.updateTrangThaiDangHoatDong();
        responsitory.updateTrangThaiDungHoatDong1();
        responsitory.updateTrangThaiDungHoatDong2();
        responsitory.updateTrangThaiSapDienRa();
    }

    @Override
    public PhieuGiamGia add(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia pgg = new PhieuGiamGia();
        LocalDateTime time = LocalDateTime.now();
        String ma = "PGG" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue() + time.getDayOfMonth() ;
        pgg.setMa(ma);
        pgg.setTen(phieuGiamGiaRequest.getTen());
        pgg.setMoTa(phieuGiamGiaRequest.getMoTa());
        pgg.setMucGiamGia(phieuGiamGiaRequest.getMucGiamGia());
        pgg.setMucGiamToiDa(phieuGiamGiaRequest.getMucGiamToiDa());
        pgg.setNgayTao(LocalDate.now());
        pgg.setNgaySua(LocalDate.now());
        pgg.setSoLuong(phieuGiamGiaRequest.getSoLuong());
        pgg.setGiaTriDonHang(phieuGiamGiaRequest.getGiaTriDonHang());
        pgg.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        pgg.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        pgg.setTrangThai(phieuGiamGiaRequest.htTrangThai());
        return responsitory.save(pgg);
    }

    @Override
    public PhieuGiamGia getById(Long id) {

        Optional<PhieuGiamGia> optional = responsitory.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public PhieuGiamGia update(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia pgg = responsitory.findById(phieuGiamGiaRequest.getId()).orElse(null);
        pgg.setTen(phieuGiamGiaRequest.getTen());
        pgg.setMoTa(phieuGiamGiaRequest.getMoTa());
        pgg.setMucGiamGia(phieuGiamGiaRequest.getMucGiamGia());
        pgg.setMucGiamToiDa(phieuGiamGiaRequest.getMucGiamToiDa());
        pgg.setNgaySua(LocalDate.now());
        pgg.setSoLuong(phieuGiamGiaRequest.getSoLuong());
        pgg.setGiaTriDonHang(phieuGiamGiaRequest.getGiaTriDonHang());
        pgg.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        pgg.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        pgg.setTrangThai(phieuGiamGiaRequest.htTrangThai());
        return responsitory.save(pgg);
    }

    @Override
    public void huy(Long id) {
        PhieuGiamGia phieuGiamGia = responsitory.findById(id).orElse(null);
        if (phieuGiamGia != null) {
            phieuGiamGia.setTrangThai(TrangThaiPhieuKhuyenMai.DA_KET_THUC);
            responsitory.save(phieuGiamGia);
        }
    }

    @Override
    public void bat(Long id) {
        PhieuGiamGia phieuGiamGia = responsitory.findById(id).orElse(null);
        if (phieuGiamGia != null) {
            phieuGiamGia.setTrangThai(TrangThaiPhieuKhuyenMai.DANG_DIEN_RA);
            responsitory.save(phieuGiamGia);
        }
    }

    @Override
    public boolean existsByTen(String ten) {

        return responsitory.existsByTen(ten);
    }

    @Override
    public boolean existsByTenAndIdNot(String ten, Long id) {
      return   responsitory.existsByTenAndIdNot(ten, id);
    }

    @Override
    public List<PhieuGiamGia> layList(Long tongGiaTri) {
        return responsitory.getAllByGiaTriDonHang(tongGiaTri);
    }
}
