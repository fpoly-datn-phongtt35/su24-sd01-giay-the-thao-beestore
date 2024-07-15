package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.controller.request.ChiTietSanPhamRequest;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.DeGiay;
import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.repository.ChiTietSanPhamRepository;
import com.example.datnsum24sd01.repository.DeGiayRepository;
import com.example.datnsum24sd01.repository.KichThuocRepository;
import com.example.datnsum24sd01.repository.MauSacRepository;
import com.example.datnsum24sd01.repository.SanPhamRepository;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    private final ChiTietSanPhamRepository repository;
    private final DeGiayRepository deGiayRepository;
    private final KichThuocRepository kichThuocRepository;
    private final MauSacRepository mauSacRepository;
    private final SanPhamRepository sanPhamRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public ChiTietSanPhamServiceImpl(ChiTietSanPhamRepository repository, DeGiayRepository deGiayRepository, KichThuocRepository kichThuocRepository, MauSacRepository mauSacRepository, SanPhamRepository sanPhamRepository) {
        this.repository = repository;
        this.deGiayRepository = deGiayRepository;
        this.kichThuocRepository = kichThuocRepository;
        this.mauSacRepository = mauSacRepository;
        this.sanPhamRepository = sanPhamRepository;
    }


    @Override
    public List<ChiTietSanPham> getAllChiTietSanPham() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham add(ChiTietSanPhamRequest chiTietSanPhamRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setGiaBan(chiTietSanPhamRequest.getGiaBan());
        chiTietSanPham.setGiaMacDinh(chiTietSanPhamRequest.getGiaMacDinh());
        chiTietSanPham.setSoLuongTon(chiTietSanPhamRequest.getSoLuongTon());
        SanPham sanPham = sanPhamRepository.findById(chiTietSanPhamRequest.getSanPham()).orElse(null);
        chiTietSanPham.setSanPham(sanPham);
        DeGiay deGiay = deGiayRepository.findById(chiTietSanPhamRequest.getDeGiay()).orElse(null);
        chiTietSanPham.setDeGiay(deGiay);
        KichThuoc kichThuoc = kichThuocRepository.findById(chiTietSanPhamRequest.getKichThuoc()).orElse(null);
        chiTietSanPham.setKichThuoc(kichThuoc);
        MauSac mauSac = mauSacRepository.findById(chiTietSanPhamRequest.getMauSac()).orElse(null);
        chiTietSanPham.setMauSac(mauSac);
        chiTietSanPham.setNgaySua(currentDateTime);
        chiTietSanPham.setNgayTao(currentDateTime);
        chiTietSanPham.setTrangThai(chiTietSanPhamRequest.getTrangThai());
        return repository.save(chiTietSanPham);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            chiTietSanPhamRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete ChiTietSanPham with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete San Pham with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public ChiTietSanPham getOne(Long id) {
        Optional<ChiTietSanPham> chiTietSanPham = this.chiTietSanPhamRepository.findById(id);
        return chiTietSanPham.get();
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham) {
        chiTietSanPham.setNgayTao(this.chiTietSanPhamRepository.findById(chiTietSanPham.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        chiTietSanPham.setNgaySua(currentDateTime);
        return this.chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void thayDoiTrangThai(Long id) {

        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamRepository.findById(id).get();

        if (chiTietSanPham.getTrangThai() == 0) {
            chiTietSanPham.setTrangThai(1);
        } else {
            chiTietSanPham.setTrangThai(0);
        }
        this.chiTietSanPhamRepository.save(chiTietSanPham);


    }
}
