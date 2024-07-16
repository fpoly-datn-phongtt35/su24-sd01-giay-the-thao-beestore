package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.QuanHuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuanHuyenRepository extends JpaRepository<QuanHuyen, Long> {
    List<QuanHuyen> findByTinhThanhId(Long tinhThanhId);

}
