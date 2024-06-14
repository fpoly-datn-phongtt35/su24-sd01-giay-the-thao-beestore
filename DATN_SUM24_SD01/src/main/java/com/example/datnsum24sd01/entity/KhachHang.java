package com.example.datnsum24sd01.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "gioi_tinh", nullable = true)
    private Byte gioiTinh;

    @Column(name = "ma", nullable = true, unique = true)
    private String ma;

    @Column(name = "mat_khau", nullable = true)
    private String matKhau;

    @Column(name = "ngay_sua", nullable = true)
    private Date ngaySua;

    @Column(name = "ngay_tao", nullable = true)
    private Date ngayTao;

    @Column(name = "sdt", nullable = true, unique = true)
    private String sdt;

    @Column(name = "ten", nullable = true)
    private String ten;

    @Column(name = "trang_thai", nullable = true)
    private Byte trangThai;

    @Column(name = "tich_diem", precision = 65, scale = 2, nullable = true)
    private BigDecimal tichDiem;

    @Column(name = "ngay_sinh", nullable = true)
    private Date ngaySinh;
}
