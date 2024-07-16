package com.example.datnsum24sd01.request;

import lombok.Data;

import java.util.Date;

@Data
public class DiaChiGiaoHangRequest {
    private Long id;
    private String diaChi;
    private String ghiChu;
    private Date ngaySua;
    private Date ngayTao;
    private String sdtNguoiNhan;
    private String ten;
    private String tenNguoiNhan;
    private Long xaPhuongId;
    private Long quanHuyenId;
    private Long tinhThanhId;
    private Long idKhachHang;
    private Integer trangThai;

}
