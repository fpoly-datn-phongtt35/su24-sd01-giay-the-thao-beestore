package com.example.datnsum24sd01.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaChiRequest {
    private Long id;
    private String diaChi;
    private String ghiChu;
    private Date ngaySua;
    private Date ngayTao;
    private String sdtNguoiNhan;
    private String ten;
    private String tenNguoiNhan;
    private String phuongXa;
    private String quanHuyen;
    private String thanhPho;
    private Integer trangThai;
    private Long idKhachHang;
}
