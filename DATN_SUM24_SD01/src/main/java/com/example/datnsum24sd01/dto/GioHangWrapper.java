package com.example.datnsum24sd01.dto;

import com.example.datnsum24sd01.entity.GioHangChiTiet;
import lombok.Data;

import java.util.List;

@Data
public class GioHangWrapper {
    private List<GioHangChiTiet> listGioHangChiTiet;
}
