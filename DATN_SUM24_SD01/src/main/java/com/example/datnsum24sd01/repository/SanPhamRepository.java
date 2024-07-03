package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.controller.request.SanPhamRequest;
import com.example.datnsum24sd01.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query(value = "select p from SanPham p where p.id=?1", nativeQuery = false)
    Optional<SanPhamRequest> findById1(Long id);
}
