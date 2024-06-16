package com.example.datnsum24sd01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.ORDINAL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "thuong_hieu")
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã Thương Hiệu không được để trống!")
    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Tên Thương Hiệu Không được để trống!")
    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_tao")
    private LocalDate ngaytao;

    @Column(name = "ngay_sua")
    private LocalDate ngaysua;

    @Column(name = "trang_thai")

    private Integer trangThai;

}
