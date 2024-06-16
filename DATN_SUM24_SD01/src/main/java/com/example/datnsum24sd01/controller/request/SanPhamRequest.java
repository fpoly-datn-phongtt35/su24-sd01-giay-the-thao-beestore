package com.example.datnsum24sd01.controller.request;

import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.entity.ThuongHieu;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class SanPhamRequest {

    private Long id;
    private String anhChinh;

    @NotBlank(message = "Mã không được để trống!")
    private String ma;
    private String moTa;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;

    @NotBlank(message = "Tên không được để trống!")
    private String ten;

    private int trangThai;
    private Long nhaCungCap;
    private Long thuongHieu;
}
