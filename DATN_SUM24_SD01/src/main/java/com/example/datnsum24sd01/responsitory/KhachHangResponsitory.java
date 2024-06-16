package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhachHangResponsitory extends JpaRepository<KhachHang, Long> {
    Optional<KhachHang> findByEmail(String email);
    Optional<KhachHang> findByEmailAndIdNot(String email, Long id);
    boolean existsBySdt(String sdt);
    boolean existsBySdtAndIdNot(String sdt, Long id);
    boolean existsByEmail(String email);

}
