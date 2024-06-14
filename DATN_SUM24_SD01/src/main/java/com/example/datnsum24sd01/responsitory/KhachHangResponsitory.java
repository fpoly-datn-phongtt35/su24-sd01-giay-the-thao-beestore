package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangResponsitory extends JpaRepository<KhachHang, Integer> {
    boolean existsKhachHangByEmail(String email);

    boolean existsKhachHangBySdt(String sdt);

    boolean existsKhachHangBySdtAndIdNot(String sdt, Integer id);

    boolean existsKhachHangByEmailAndIdNot(String email, Integer id);
}
