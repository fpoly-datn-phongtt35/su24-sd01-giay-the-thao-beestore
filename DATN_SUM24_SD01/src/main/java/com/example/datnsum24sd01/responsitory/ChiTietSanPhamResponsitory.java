package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChiTietSanPhamResponsitory extends JpaRepository<ChiTietSanPham,Long> {
    @Query(value = "select * from chi_tiet_san_pham where id = :idChiTietSanPham", nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamById(@Param("idChiTietSanPham") Long idChiTietSanPham);
}
