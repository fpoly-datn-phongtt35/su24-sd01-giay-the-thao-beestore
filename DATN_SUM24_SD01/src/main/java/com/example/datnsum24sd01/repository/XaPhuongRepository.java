package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.XaPhuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XaPhuongRepository extends JpaRepository<XaPhuong, Long> {
    List<XaPhuong> findByQuanHuyenId(Long quanHuyenId); // Add the parameter here
}
