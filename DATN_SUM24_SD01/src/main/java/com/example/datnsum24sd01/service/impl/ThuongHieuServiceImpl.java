package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.controller.request.ThuongHieuRequest;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.repository.ThuongHieuRepository;
import com.example.datnsum24sd01.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAllThuongHieu() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu add(ThuongHieuRequest thuongHieuRequest) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        ThuongHieu thuongHieu = new ThuongHieu();
        thuongHieu.setMa(thuongHieuRequest.getMa());
        thuongHieu.setNgaySua(currentDateTime);
        thuongHieu.setNgayTao(currentDateTime);
        thuongHieu.setTen(thuongHieuRequest.getTen());
        thuongHieu.setTrangThai(thuongHieuRequest.getTrangThai());

        ThuongHieu thuongHieuAddLater = thuongHieuRepository.save(thuongHieu);
        String maThuongHieu = "TH" + thuongHieuAddLater.getId().toString();
        thuongHieuAddLater.setMa(maThuongHieu);
        return thuongHieuRepository.save(thuongHieuAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            thuongHieuRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete ThuongHieu with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete NhanVien with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public ThuongHieu getOne(Long id) {
        Optional<ThuongHieu> thuongHieu = this.thuongHieuRepository.findById(id);
        return thuongHieu.get();
    }

    @Override
    public ThuongHieu update(ThuongHieu thuongHieu) {
        thuongHieu.setNgayTao(this.thuongHieuRepository.findById(thuongHieu.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        thuongHieu.setNgaySua(currentDateTime);

        return this.thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public void thayDoiTrangThai(Long id) {

        ThuongHieu thuongHieu = this.thuongHieuRepository.findById(id).get();

        if (thuongHieu.getTrangThai() == 0) {
            thuongHieu.setTrangThai(1);
        } else {
            thuongHieu.setTrangThai(0);
        }
        this.thuongHieuRepository.save(thuongHieu);


    }

}
