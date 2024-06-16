package com.example.datnsum24sd01.service.impl;


import com.example.datnsum24sd01.controller.request.SanPhamRequest;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.repository.SanPhamRepository;
import com.example.datnsum24sd01.repository.SanPhamRepository;
import com.example.datnsum24sd01.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham add(SanPhamRequest sanPhamRequest) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        SanPham sanPham = new SanPham();
//        sanPham.setAnhChinh(sanPhamRequest.getAnhChinh());
        sanPham.setMa(sanPhamRequest.getMa());
        sanPham.setMoTa(sanPhamRequest.getMoTa());
        sanPham.setNgaySua(currentDateTime);
        sanPham.setNgayTao(currentDateTime);
        sanPham.setTen(sanPhamRequest.getTen());
        sanPham.setTrangThai(sanPhamRequest.getTrangThai());
//        sanPham.setNhaCungCap(sanPhamRequest.getNhaCungCap());
//        sanPham.setThuongHieu(sanPhamRequest.getThuongHieu());

        SanPham sanPhamAddLater = sanPhamRepository.save(sanPham);
        String maSP = "SP" + sanPhamAddLater.getId().toString();
        sanPhamAddLater.setMa(maSP);
        return sanPhamRepository.save(sanPhamAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            sanPhamRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete SanPham with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete San Pham with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public SanPham getOne(Long id) {
        Optional<SanPham> sanPham = this.sanPhamRepository.findById(id);
        return sanPham.get();
    }

    @Override
    public SanPham update(SanPham sanPham) {
        sanPham.setNgayTao(this.sanPhamRepository.findById(sanPham.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        sanPham.setNgaySua(currentDateTime);
        return this.sanPhamRepository.save(sanPham);
    }

    @Override
    public void thayDoiTrangThai(Long id) {

        SanPham sanPham = this.sanPhamRepository.findById(id).get();

        if (sanPham.getTrangThai() == 0) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }
        this.sanPhamRepository.save(sanPham);


    }
}
