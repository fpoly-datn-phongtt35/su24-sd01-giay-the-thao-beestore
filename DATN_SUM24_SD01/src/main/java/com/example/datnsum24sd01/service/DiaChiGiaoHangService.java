package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.DiaChiGiaoHang;
import com.example.datnsum24sd01.entity.QuanHuyen;
import com.example.datnsum24sd01.entity.TinhThanh;
import com.example.datnsum24sd01.entity.XaPhuong;
import com.example.datnsum24sd01.request.DiaChiGiaoHangRequest;

import java.util.List;

public interface DiaChiGiaoHangService {

    List<DiaChiGiaoHang> getAll();

    DiaChiGiaoHang add(DiaChiGiaoHangRequest diaChiGiaoHangRequest, Long idKhachHang);

    String delete(Long id);

    DiaChiGiaoHang getById(Long id);

    DiaChiGiaoHang update(DiaChiGiaoHangRequest diaChiGiaoHangRequest);

    List<TinhThanh> getAllTinhThanh();

    List<QuanHuyen> getQuanHuyenByTinhThanhId(Long idTinhThanh);

    List<XaPhuong> getXaPhuongByQuanHuyenId(Long idQuanHuyen);
}
