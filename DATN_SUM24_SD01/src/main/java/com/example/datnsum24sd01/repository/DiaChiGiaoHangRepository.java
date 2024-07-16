package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.DiaChiGiaoHang;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaChiGiaoHangRepository extends JpaRepository<DiaChiGiaoHang, Long> {
    @Query("SELECT dc FROM DiaChiGiaoHang dc WHERE dc.khachHang.id = :idKhachHang AND (dc.trangThai = 1 or dc.trangThai = 0)")
    List<DiaChiGiaoHang> findAllDiaChiGiaoHangUsing(Long idKhachHang);

    @Query("SELECT dc FROM DiaChiGiaoHang dc WHERE dc.khachHang.id = :idKhachHang AND dc.trangThai = 1")
    DiaChiGiaoHang findDiaChiGiaoHangMacDinh(@Param("idKhachHang") Long idKhachHang);

    @Query("SELECT dc FROM DiaChiGiaoHang dc WHERE dc.khachHang.id = :idKhachHang AND dc.trangThai = 2")
    List<DiaChiGiaoHang> findAllDiaChiGiaoHangDeleted(@Param("idKhachHang") Long idKhachHang);

    @Modifying
    @Transactional
    @Query("UPDATE DiaChiGiaoHang dc SET dc.trangThai = 0 WHERE dc.khachHang.id = :idKhachHang AND dc.trangThai = 1")
    void updateDiaChiGiaoHangStatusToZero(@Param("idKhachHang") Long idKhachHang);


    List<DiaChiGiaoHang> findAllByKhachHangId(Long idKH);
}
