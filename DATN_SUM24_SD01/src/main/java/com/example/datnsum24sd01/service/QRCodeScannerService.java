package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.NhanVien;

public interface QRCodeScannerService {
    void startScanner(); // Bắt đầu quét mã QR bằng webcam

//    void stopScanner(); // Dừng quét mã QR

    NhanVien processAndSaveQRCode(String qrCodeData);

    String getLatestQRCodeData();

//    void processQRCode(String qrCodeData); // Xử lý dữ liệu mã QR
//
//    void handleQRCodeError(Exception e); // Xử lý lỗi khi quét mã QR
}
