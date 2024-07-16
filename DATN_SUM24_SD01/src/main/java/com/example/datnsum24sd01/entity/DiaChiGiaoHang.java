package com.example.datnsum24sd01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi_giao_hang")
public class DiaChiGiaoHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_sua")
    private java.util.Date ngaySua;

    @Column(name = "ngay_tao", nullable = false)
    private java.util.Date ngayTao;

    @Column(name = "sdt")
    private String sdtNguoiNhan;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @ManyToOne
    @JoinColumn(name = "id_xa_phuong", nullable = false)
    private XaPhuong xaPhuong;

    @ManyToOne
    @JoinColumn(name = "id_quan_huyen", nullable = false)
    private QuanHuyen quanHuyen;

    @ManyToOne
    @JoinColumn(name = "id_tinh_thanh", nullable = false)
    private TinhThanh tinhThanh;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang", nullable = false)
    private KhachHang khachHang;
}
