package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    boolean existsKhachHangByEmail(String email);

    boolean existsKhachHangBySdt(String sdt);

    List<KhachHang> findAllByTenContains(String ten);
    @Query("SELECT kh FROM KhachHang kh WHERE kh.ten LIKE :kw OR kh.sdt = :kw OR kh.email LIKE :kw")
    List<KhachHang> findByStr(@Param("kw") String keyWord);
    @Query("SELECT kh FROM KhachHang kh WHERE kh.trangThai = :status")
    List<KhachHang> findByStatus(@Param("status") Integer status);

    @Query("SELECT kh FROM KhachHang kh WHERE (kh.ten LIKE :kw OR kh.sdt LIKE :kw OR kh.email LIKE :kw) AND kh.trangThai = :status")
    List<KhachHang> findByStrAndStatus(@Param("kw") String keyWord, @Param("status") Integer status);


}
