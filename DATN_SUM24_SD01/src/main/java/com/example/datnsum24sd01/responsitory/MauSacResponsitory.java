package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MauSacResponsitory extends JpaRepository<MauSac,Long> {


}
