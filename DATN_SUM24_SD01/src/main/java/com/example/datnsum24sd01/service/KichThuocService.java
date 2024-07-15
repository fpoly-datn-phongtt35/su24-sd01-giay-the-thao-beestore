package com.example.datnsum24sd01.service;


import com.example.datnsum24sd01.controller.request.KichThuocRequest;
import com.example.datnsum24sd01.entity.KichThuoc;

import java.util.List;

public interface KichThuocService {
    List<KichThuoc> getAllKichThuoc();

    KichThuoc add(KichThuocRequest kichThuocRequest);

    String delete(Long id);

    KichThuoc getOne(Long id);

    KichThuoc findById(Long id);

    KichThuoc update(KichThuoc kichThuoc);
}
