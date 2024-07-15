package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.controller.request.MauSacRequest;
import com.example.datnsum24sd01.entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAllMauSac();

    MauSac add(MauSacRequest mauSacRequest);

    String delete(Long id);

    MauSac getOne(Long id);

    MauSac findById(Long id);

    MauSac update(MauSac mauSac);
}
