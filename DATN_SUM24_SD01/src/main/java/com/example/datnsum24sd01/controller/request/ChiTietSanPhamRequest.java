package com.example.datnsum24sd01.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChiTietSanPhamRequest {

    private Long id;

    private BigDecimal giaBan;

    private BigDecimal giaMacDinh;

    private LocalDateTime ngaySua;

    private LocalDateTime ngayTao;

    private int soLuongTon;

    private int trangThai;

    private Long deGiay;

    private Long kichThuoc;

    private Long mauSac;

    private Long sanPham;
}
