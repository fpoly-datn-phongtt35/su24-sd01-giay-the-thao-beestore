package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.TinhThanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TinhThanhRepository extends JpaRepository<TinhThanh, Long> {
    List<TinhThanh> findAll();


}
