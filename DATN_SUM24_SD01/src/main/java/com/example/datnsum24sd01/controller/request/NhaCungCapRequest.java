package com.example.datnsum24sd01.controller.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class NhaCungCapRequest {

    private Long id;
    private String ma;
    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;
    private String ten;
    private int trangThai;
}
