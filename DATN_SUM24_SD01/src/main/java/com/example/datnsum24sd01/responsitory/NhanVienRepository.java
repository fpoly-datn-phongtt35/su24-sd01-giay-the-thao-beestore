package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    boolean existsNhanVienByCanCuocCongDan(String cccd);

    boolean existsNhanVienByCanCuocCongDanAndIdNot(String cccd, long id);

    boolean existsNhanVienBySdtAndIdNot(String cccd, long id);

    boolean existsNhanVienByEmailAndIdNot(String cccd, long id);

    boolean existsNhanVienBySdt(String sdt);

    boolean existsNhanVienByEmail(String email);

    List<NhanVien> findAllByTenContains(String ten);
    Optional<NhanVien> findByEmail(String email);


}
