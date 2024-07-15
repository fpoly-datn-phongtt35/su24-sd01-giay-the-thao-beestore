package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.DeGiayRequest;
import com.example.datnsum24sd01.entity.DeGiay;

import java.util.List;

public interface DeGiayService {
    List<DeGiay> getAllDeGiay();

    DeGiay add(DeGiayRequest deGiayRequest);

    String delete(Long id);

    DeGiay getOne(Long id);

    DeGiay findById(Long id);

    DeGiay update(DeGiay deGiay);
}
