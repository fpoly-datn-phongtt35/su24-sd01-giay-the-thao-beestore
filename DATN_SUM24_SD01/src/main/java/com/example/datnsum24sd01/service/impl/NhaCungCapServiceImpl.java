package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.controller.request.NhaCungCapRequest;
import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.repository.NhaCungCapRepository;
import com.example.datnsum24sd01.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;


    @Override
    public List<NhaCungCap> getAllNhaCungCap() {
        return nhaCungCapRepository.findAll();
    }

    @Override
    public NhaCungCap add(NhaCungCapRequest nhaCungCapRequest) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setMa(nhaCungCapRequest.getMa());
        nhaCungCap.setNgaySua(currentDateTime);
        nhaCungCap.setNgayTao(currentDateTime);
        nhaCungCap.setTen(nhaCungCapRequest.getTen());
        nhaCungCap.setTrangThai(nhaCungCapRequest.getTrangThai());

        NhaCungCap nhaCungCapAddLater = nhaCungCapRepository.save(nhaCungCap);
        String maNCC = "NCC" + nhaCungCapAddLater.getId().toString();
        nhaCungCapAddLater.setMa(maNCC);
        return nhaCungCapRepository.save(nhaCungCapAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            nhaCungCapRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete NhaCungCap with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete NhanVien with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public NhaCungCap getOne(Long id) {
        Optional<NhaCungCap> nhaCungCap = this.nhaCungCapRepository.findById(id);
        return nhaCungCap.get();
    }

    @Override
    public NhaCungCap findById(Long id) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapRepository.findById(id);
        if (nhaCungCap.isPresent()) {
            return nhaCungCap.get();
        }
        return null;
    }

    @Override
    public NhaCungCap update(NhaCungCap nhaCungCap) {
        nhaCungCap.setNgayTao(this.nhaCungCapRepository.findById(nhaCungCap.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        nhaCungCap.setNgaySua(currentDateTime);
        return this.nhaCungCapRepository.save(nhaCungCap);
    }

    @Override
    public void thayDoiTrangThai(Long id) {

        NhaCungCap nhaCungCap = this.nhaCungCapRepository.findById(id).get();

        if (nhaCungCap.getTrangThai() == 0) {
            nhaCungCap.setTrangThai(1);
        } else {
            nhaCungCap.setTrangThai(0);
        }
        this.nhaCungCapRepository.save(nhaCungCap);

    }
    @Override
    public boolean existByMa(String ma) {
        return nhaCungCapRepository.existsByMa(ma);
    }

    @Override
    public boolean existsByTen(String ten) {
        return nhaCungCapRepository.existsByTen(ten);
    }

    @Override
    public boolean existsByTenAndIdNot(String ten, Long id) {
        return nhaCungCapRepository.existsByTenAndIdNot(ten,id);
    }

}
