package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.HoaDonRepo;
import com.example.datnsum24sd01.service.BanHangService;
import com.example.datnsum24sd01.service.HoaDonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepo repository;
//    private final GioHangChiTietRepository gioHangChiTietRepository;
//
//    private final ChiTietSanPhamRepository chiTietSanPhamRepository;

//    private final SendMailService sendMailService;

    private final BanHangService banHangService;

    public HoaDonServiceImpl(HoaDonRepo repository, BanHangService banHangService) {
        this.repository = repository;

        this.banHangService = banHangService;
    }

    public List<HoaDon> getAll() {
        List<HoaDon> sortedList = repository.findAll().stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }





    @Override
    public BigDecimal phieuGiamGia(Long idHd){
        HoaDon hoaDon= repository.findById(idHd).get();

        return null;
    }

}
