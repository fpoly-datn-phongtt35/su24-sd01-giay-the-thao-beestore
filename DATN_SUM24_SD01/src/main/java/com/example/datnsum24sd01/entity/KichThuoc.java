package com.example.datnsum24sd01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "kich_thuoc")
public class KichThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    @NotBlank(message = "Mã không được để trống")
    private String ma;

    @Column(name = "ten")
    @NotBlank(message = "Tên không được để trống")
    private String ten;
    @Column(name = "ngay_tao")
    private String ngaytao;
    @Column(name = "ngay_sua")
    private String ngaysua;

}
