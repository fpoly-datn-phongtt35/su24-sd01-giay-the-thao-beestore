package com.example.datnsum24sd01.repository;

import com.example.datnsum24sd01.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay, Long> {
}
