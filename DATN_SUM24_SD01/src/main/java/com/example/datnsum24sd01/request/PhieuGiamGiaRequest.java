package com.example.datnsum24sd01.request;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PhieuGiamGiaRequest {
    private Long id;

    private String ma;

    @NotBlank(message = "Tên Phiếu Không Được Để Trống !")
    private String ten;

    @NotBlank(message = "Bạn Chưa Nhập Mô Tả !")
    private String moTa;


    @NotNull(message = "Mức Giảm Giá Không Được Để Trống !")
    @Positive(message = "Mức Giảm Giá Phải Lớn Hơn 0 !")
    @Max(value = 100, message = "Mức Giảm Giá Không Được Vượt Quá 100 !")
    private Integer mucGiamGia;

    @NotNull(message = "Tiền Giảm Tối Đa Không Được Để Trống !")
    @Min(value = 1, message = "Mức Giảm Giá Tối Đa Thấp Nhất Là 1 !")
    private BigDecimal mucGiamToiDa;

    @NotNull(message = "Số Lượng Không Được Để Trống !")
    @Positive(message = "Số lượng Phải Lớn Hơn 0 !")
    private Integer soLuong;

    @NotNull(message = "Giá Trị áp Dụng Tối Thiểu Không Được Để Trống !")
    @Min(value = 0, message = "Giá Trị Áp Dụng Tối Thiểu Là 0 !")
    private BigDecimal giaTriDonHang;

    @NotNull(message = "Ngày Bắt Đầu Không Được Để Trống !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Ngày Bắt Đầu Phải Là Ngày Hiện Tại Hoặc Tương Lai !")
    private LocalDate ngayBatDau;

    @NotNull(message = "Ngày Kết Thúc Không Được Để Trống !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Ngày Kết Thúc Phải ở Hiện Tại Hoặc Tương Lai !")
    private LocalDate ngayKetThuc;

    private TrangThaiPhieuKhuyenMai trangThai;

    public  TrangThaiPhieuKhuyenMai htTrangThai() {
        LocalDate DaysAgo = this.ngayBatDau.minusDays(4);
        if (LocalDate.now().isEqual(ngayBatDau)) {
            return  TrangThaiPhieuKhuyenMai.DANG_DIEN_RA;
        } else if (LocalDate.now().isAfter(DaysAgo) && LocalDate.now().isBefore(ngayBatDau)) {
            return  TrangThaiPhieuKhuyenMai.SAP_DIEN_RA;
        } else if (ngayBatDau.isAfter(LocalDate.now())) {
            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
        } else if (ngayKetThuc.isBefore(LocalDate.now())) {
            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
        } else if(soLuong == 0){
            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
        } else {
            return  TrangThaiPhieuKhuyenMai.DANG_DIEN_RA;
        }
    }
}
