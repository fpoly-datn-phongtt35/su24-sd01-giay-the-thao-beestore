package com.example.datnsum24sd01.service.Impl;

import com.example.datnsum24sd01.entity.DiaChiGiaoHang;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.QuanHuyen;
import com.example.datnsum24sd01.entity.TinhThanh;
import com.example.datnsum24sd01.entity.XaPhuong;
import com.example.datnsum24sd01.repository.DiaChiGiaoHangRepository;
import com.example.datnsum24sd01.repository.KhachHangRepository;
import com.example.datnsum24sd01.repository.QuanHuyenRepository;
import com.example.datnsum24sd01.repository.TinhThanhRepository;
import com.example.datnsum24sd01.repository.XaPhuongRepository;
import com.example.datnsum24sd01.request.DiaChiGiaoHangRequest;
import com.example.datnsum24sd01.service.DiaChiGiaoHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiaChiGiaoHangServiceImpl implements DiaChiGiaoHangService {

    private final DiaChiGiaoHangRepository diaChiGiaoHangRepository;
    private final XaPhuongRepository xaPhuongRepository;
    private final QuanHuyenRepository quanHuyenRepository;
    private final TinhThanhRepository tinhThanhRepository;
    private final KhachHangRepository khachHangRepository;

    @Autowired
    public DiaChiGiaoHangServiceImpl(DiaChiGiaoHangRepository diaChiGiaoHangRepository, XaPhuongRepository xaPhuongRepository,
                                     QuanHuyenRepository quanHuyenRepository, TinhThanhRepository tinhThanhRepository,
                                     KhachHangRepository khachHangRepository) {
        this.diaChiGiaoHangRepository = diaChiGiaoHangRepository;
        this.xaPhuongRepository = xaPhuongRepository;
        this.quanHuyenRepository = quanHuyenRepository;
        this.tinhThanhRepository = tinhThanhRepository;
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    public List<DiaChiGiaoHang> getAll() {
        return diaChiGiaoHangRepository.findAll();
    }

    @Override
    public DiaChiGiaoHang add(DiaChiGiaoHangRequest diaChiGiaoHangRequest, Long idKhachHang) {
        DiaChiGiaoHang diaChi = new DiaChiGiaoHang();
        diaChi.setDiaChi(diaChiGiaoHangRequest.getDiaChi());
        diaChi.setGhiChu(diaChiGiaoHangRequest.getGhiChu());
        diaChi.setSdtNguoiNhan(diaChiGiaoHangRequest.getSdtNguoiNhan());
        diaChi.setTen(diaChiGiaoHangRequest.getTen());
        diaChi.setTenNguoiNhan(diaChiGiaoHangRequest.getTenNguoiNhan());

        XaPhuong xaPhuong = xaPhuongRepository.findById(diaChiGiaoHangRequest.getXaPhuongId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy xã/phường với id: " + diaChiGiaoHangRequest.getXaPhuongId()));
        diaChi.setXaPhuong(xaPhuong);

        QuanHuyen quanHuyen = quanHuyenRepository.findById(diaChiGiaoHangRequest.getQuanHuyenId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy quận/huyện với id: " + diaChiGiaoHangRequest.getQuanHuyenId()));
        diaChi.setQuanHuyen(quanHuyen);

        TinhThanh tinhThanh = tinhThanhRepository.findById(diaChiGiaoHangRequest.getTinhThanhId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tỉnh/thành với id: " + diaChiGiaoHangRequest.getTinhThanhId()));
        diaChi.setTinhThanh(tinhThanh);

        Integer trangThai = diaChiGiaoHangRequest.getTrangThai();
        if (trangThai != null && trangThai == 1) {
            diaChiGiaoHangRepository.updateDiaChiGiaoHangStatusToZero(idKhachHang);
        }
        diaChi.setTrangThai(trangThai != null ? trangThai : 0);

        KhachHang khachHang = khachHangRepository.findById(idKhachHang)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng với id: " + idKhachHang));
        diaChi.setKhachHang(khachHang);

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        diaChi.setNgayTao(currentDate);
        diaChi.setNgaySua(currentDate);

        return diaChiGiaoHangRepository.save(diaChi);
    }


    @Override
    public String delete(Long id) {
        Optional<DiaChiGiaoHang> diaChiOptional = diaChiGiaoHangRepository.findById(id);
        if (diaChiOptional.isPresent()) {
            DiaChiGiaoHang diaChiGiaoHang = diaChiOptional.get();
            diaChiGiaoHang.setTrangThai(2);
            diaChiGiaoHangRepository.save(diaChiGiaoHang);

            return "Xóa thành công và đã cập nhật trạng thái!";
        } else {
            return "Không thể xóa địa chỉ với id = " + id + ". Không tìm thấy địa chỉ.";
        }
    }

    @Override
    public DiaChiGiaoHang getById(Long id) {
        return diaChiGiaoHangRepository.findById(id).orElse(null);
    }

    @Override
    public DiaChiGiaoHang update(DiaChiGiaoHangRequest diaChiGiaoHangRequest) {
        Long id = diaChiGiaoHangRequest.getId();
        System.out.println("ID: " + id);
        if (id == null) {
            throw new IllegalArgumentException("Id của địa chỉ giao hàng không được null");
        }

        Optional<DiaChiGiaoHang> optionalDiaChiGiaoHang = diaChiGiaoHangRepository.findById(id);
        if (!optionalDiaChiGiaoHang.isPresent()) {
            throw new IllegalArgumentException("Không tìm thấy địa chỉ giao hàng với id: " + id);
        }

        DiaChiGiaoHang existingDiaChi = optionalDiaChiGiaoHang.get();
        existingDiaChi.setDiaChi(diaChiGiaoHangRequest.getDiaChi());
        existingDiaChi.setGhiChu(diaChiGiaoHangRequest.getGhiChu());
        existingDiaChi.setNgaySua(new Date());
        existingDiaChi.setSdtNguoiNhan(diaChiGiaoHangRequest.getSdtNguoiNhan());
        existingDiaChi.setTen(diaChiGiaoHangRequest.getTen());
        existingDiaChi.setTenNguoiNhan(diaChiGiaoHangRequest.getTenNguoiNhan());
        existingDiaChi.setTrangThai(diaChiGiaoHangRequest.getTrangThai());

        TinhThanh tinhThanh = tinhThanhRepository.findById(diaChiGiaoHangRequest.getTinhThanhId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tỉnh/thành với id: " + diaChiGiaoHangRequest.getTinhThanhId()));
        existingDiaChi.setTinhThanh(tinhThanh);
        QuanHuyen quanHuyen = quanHuyenRepository.findById(diaChiGiaoHangRequest.getQuanHuyenId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy quận/huyện với id: " + diaChiGiaoHangRequest.getQuanHuyenId()));
        existingDiaChi.setQuanHuyen(quanHuyen);
        // Retrieve and set XaPhuong, QuanHuyen, TinhThanh entities based on IDs
        XaPhuong xaPhuong = xaPhuongRepository.findById(diaChiGiaoHangRequest.getXaPhuongId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy xã/phường với id: " + diaChiGiaoHangRequest.getXaPhuongId()));
        existingDiaChi.setXaPhuong(xaPhuong);


        // Save the updated DiaChiGiaoHang entity
        return diaChiGiaoHangRepository.save(existingDiaChi);
    }


    @Override
    public List<TinhThanh> getAllTinhThanh() {
        return tinhThanhRepository.findAll();
    }

    @Override
    public List<QuanHuyen> getQuanHuyenByTinhThanhId(Long idTinhThanh) {
        return quanHuyenRepository.findByTinhThanhId(idTinhThanh);
    }

    @Override
    public List<XaPhuong> getXaPhuongByQuanHuyenId(Long idQuanHuyen) {
        return xaPhuongRepository.findByQuanHuyenId(idQuanHuyen);
    }


}
