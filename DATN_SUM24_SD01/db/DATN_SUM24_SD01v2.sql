Create Database DATN_SUM24_SD01;
use DATN_SUM24_SD01;

-- 1. Nhà Cung Cấp
create table nha_cung_cap
(
    id         bigint auto_increment
        primary key,
    ma         varchar(200) not null,
    ngay_sua   date null,
    ngay_tao   date null,
    ten        varchar(200) not null,
    trang_thai tinyint null,
    constraint UK_28w4x0up9146ir61vu5nj6gs4
        unique (ma),
    constraint UK_ti2brqn4tp0othp3iwu97jwtp
        unique (ten)
);

-- 2.Thương Hiệu
create table thuong_hieu
(
    id         bigint auto_increment
        primary key,
    ma         varchar(200) not null,
    ngay_sua   date null,
    ngay_tao   date null,
    ten         varchar(200) not null,
    trang_thai tinyint null,
    constraint UK_123sssxf1x7ax6jyo4wdcvk36
        unique (ma),
    constraint UK_53w31rjwmistgvxj1lk2gnus9
        unique (ten)
);

-- 3.Sản Phẩm
create table san_pham
(
    id               bigint auto_increment
        primary key,
    anh_sanpham       varchar(200) not null,
    ma           varchar(200) not null,
    mo_ta           varchar(200) not null,
    ngay_sua         date null,
    ngay_tao         date null,
    ten             varchar(200) not null,
    trang_thai       tinyint null,
    id_nha_cung_cap bigint null,
    id_thuong_hieu   bigint null,
    constraint UK_131y1wu003wtcyqns9naaabhr
        unique (ma),
    constraint FK5266y3xb83fch2ygdg6wf58qu
        foreign key (id_thuong_hieu) references thuong_hieu (id),
    constraint FKlds313o255x6s8a6cj58lo5hj
        foreign key (id_nha_cung_cap) references nha_cung_cap (id)
);

-- 4.Ảnh
create table anh
(
    id          bigint auto_increment
        primary key,
    ten        varchar(200)  null,
    url         varchar(200) null,
    ngay_sua   date null,
    ngay_tao   date null,
    id_san_pham bigint null,
    constraint FK2dp9xg8gpf8t1jfw4wxb35o7y
        foreign key (id_san_pham) references san_pham (id)
);

-- 5.Đế Giày
create table de_giay
(
    id  bigint auto_increment
        primary key,
    ma  varchar(200) not null,
    ten varchar(200) not null,
    ngay_sua   date null,
    ngay_tao   date null,
    constraint UK_511l83jif9dfpiiqfkaa4ofyi
        unique (ten),
    constraint UK_bse8cvun3iodgds58ox9h8yhw
        unique (ma)
);

-- 6.Kích Thước
create table kich_thuoc
(
    id  bigint auto_increment
        primary key,
    ma  varchar(200) not null,
    ten varchar(200) not null,
    ngay_sua   date null,
    ngay_tao   date null,
    constraint UK_gkywxpxndarpnx0eo0efvw653
        unique (ten),
    constraint UK_qtq2dqyq8ut9lu7xir4c01y0u
        unique (ma)
);
-- 7.Màu Sắc
create table mau_sac
(
    id  bigint auto_increment
        primary key,
    ma  varchar(200) not null,
    ten varchar(200) not null,
    ngay_sua   date null,
    ngay_tao   date null,
    constraint UK_i8g6p3221tdj7b0i007uyx4uo
        unique (ma),
    constraint UK_obo04bgmvelnnhd3k64hgixj4
        unique (ten)
);


-- 8.Nhân Viên
create table nhan_vien
(
    id         bigint auto_increment
        primary key,
    email      varchar(200) null,
    gioi_tinh  tinyint null,
    ma         varchar(200) null,
    mat_khau   varchar(200) null,
    ngay_sua   date null,
    ngay_tao   date null,
    sdt        varchar(200) null,
    ten       varchar(200) null,
    cccd        varchar(200) null,
    trang_thai tinyint null,
    constraint UK_9etpn19qmeos5n3dqc87qild3
        unique (ma),
    constraint UK_mafuwxhl2bcv6obb9fkouokec
        unique (sdt)
);

-- 9.Phiếu Giảm Giá
create table phieu_giam_gia
(
    id               bigint auto_increment
        primary key,
    gia_tri_don_hang decimal(65, 2) null,
    ma               varchar(200) null,
    mo_ta           varchar(200) null,
    muc_giam_gia     int null,
    ngay_bat_dau     date null,
    ngay_ket_thuc    date null,
    ngay_sua         date null,
    ngay_tao         date null,
    so_luong         int null,
    ten              varchar(200) null,
    trang_thai       tinyint null,
    muc_giam_toi_da  decimal(65, 2) null,
    constraint UK_acuss6gxp9w56b8rg3mmg3yux
        unique (ma)
);

-- 10.Chi tiết sản phẩm
create table chi_tiet_san_pham
(
    id            bigint auto_increment
        primary key,
    gia_ban       decimal(65, 2) null,
    gia_mac_dinh  decimal(65, 2) null,
    ngay_sua      date null,
    ngay_tao      date null,
    so_luong_ton  int null,
    trang_thai    tinyint null,
    id_de_giay    bigint null,
    id_kich_thuoc bigint null,
    id_mau_sac    bigint null,
    id_san_pham   bigint null,
    constraint FK5hacuc63k9pfld0eomf5vnrlj
        foreign key (id_kich_thuoc) references kich_thuoc (id),
    constraint FKhrc41nqmp3jsh42ikergp7qsd
        foreign key (id_mau_sac) references mau_sac (id),
    constraint FKhry1oewlwwhwhuqhr1tinw6l6
        foreign key (id_san_pham) references san_pham (id),
    constraint FKtj2f11b2f5l0l8rh9wfnyena2
        foreign key (id_de_giay) references de_giay (id)
);

-- 11.Khách hàng
create table khach_hang
(
    id         bigint auto_increment
        primary key,
    email      varchar(200) null,
    gioi_tinh  tinyint null,
    ma         varchar(200) null,
    mat_khau   varchar(200) null,
    ngay_sua   date null,
    ngay_tao   date null,
    sdt        varchar(200) null,
    ten      varchar(200) null,
    trang_thai tinyint null,
    tich_diem  decimal(65, 2) null,
    constraint UK_1lmmis0qdveete6l4prc9xlad
        unique (ma),
    constraint UK_6gn74xxiy11yxkbb2xmldnlld
        unique (sdt)
);

-- 12.Địa Chỉ
create table dia_chi
(
    id             bigint auto_increment
        primary key,
    dia_chi       varchar(200) null,
    ghi_chu       varchar(200) null,
    ngay_sua       date null,
    ngay_tao       date null,
    sdt           varchar(200) null,
    ten           varchar(200) null,
    ten_nguoi_nhan varchar(200) null,
    phuong_xa      varchar(200) null,
    quan_huyen     varchar(200) null,
    thanh_pho     varchar(200) null,
    trang_thai     tinyint null,
    id_khach_hang  bigint null,
    constraint FKrk60uo19d67v1wpbp5a1rmao5
        foreign key (id_khach_hang) references khach_hang (id)
);

-- 13.Giỏ Hàng
create table gio_hang
(
    id            bigint auto_increment
        primary key,
    ma           varchar(200) null,
    ngay_sua      date null,
    ngay_tao      date null,
    trang_thai    tinyint null,
    id_khach_hang bigint null,
    constraint UK_q7fhxt1ya2dvmjcrrkxisvpl5
        unique (id_khach_hang),
    constraint UK_s20okvj8cy9ux6taewckmju9
        unique (ma),
    constraint FK6c8iirgeg8qcwpq1oa9noxshw
        foreign key (id_khach_hang) references khach_hang (id)
);

-- 14.Hóa Đơn
create table hoa_don
(
    id              bigint auto_increment
        primary key,
    dia_chi         varchar(200) null,
    ghi_chu         varchar(200) null,
    loai_hoa_don    tinyint null,
    ma             varchar(200) null,
    ngay_giao       date null,
    ngay_sua        date null,
    ngay_tao        date null,
    ngay_thanh_toan date null,
    phi_van_chuyen  decimal(65, 2) null,
    sdt             varchar(200) null,
    ten_khach_hang varchar(200) null,
    thanh_toan      decimal(65, 2) null,
    tong_tien       decimal(65, 2) null,
    trang_thai      tinyint null,
    id_khach_hang   bigint null,
    id_nhan_vien    bigint null,
    id_phieu_giam_gia  bigint null,
    xu              decimal(65, 2) null,
    tien_giam_gia   decimal(65, 2) null,
    constraint UK_qc99tpq7eqghgm22o8e06gqyf
        unique (ma),
    constraint FK8t4ha4ehtva0djgtpn7ljexce
        foreign key (id_phieu_giam_gia) references phieu_giam_gia (id),
    constraint FKkuxkrkgq8yftm4d8d7o0w6nbv
        foreign key (id_nhan_vien) references nhan_vien (id),
    constraint FKrygimdf5nr1g2t6u03gvtr1te
        foreign key (id_khach_hang) references khach_hang (id)
);

-- 15.Hóa đơn chi tiết
create table hoa_don_chi_tiet
(
    id                   bigint auto_increment
        primary key,
    de_giay            varchar(200) null,
    gia_ban              decimal(65, 2) null,
    kich_thuoc           varchar(200) null,
    mau_sac            varchar(200) null,
    ngay_sua             date null,
    ngay_tao             date null,
    so_luong             int null,
    ten_san_pham         varchar(200) null,
    thuong_hieu        varchar(200) null,
    trang_thai           tinyint null,
    id_chi_tiet_san_pham bigint null,
    id_hoa_don           bigint null,
    constraint FK4672qsis193xo4polycwrcwb8
        foreign key (id_chi_tiet_san_pham) references chi_tiet_san_pham (id),
    constraint FK5adopt864mjisuy5xmgmy8iun
        foreign key (id_hoa_don) references hoa_don (id)
);

-- 16.Giỏ hàng chi tiết
create table gio_hang_chi_tiet
(
    id                   bigint auto_increment
        primary key,
    don_gia              decimal(65, 2) null,
    ghi_chu              varchar(200) null,
    ngay_sua             date null,
    ngay_tao             date null,
    so_luong             int null,
    trang_thai           tinyint null,
    id_chi_tiet_san_pham bigint null,
    id_gio_hang          bigint null,
    id_hoa_don           bigint null,
    constraint FKhkle2qtnnet5fq60x6tdhekql
        foreign key (id_gio_hang) references gio_hang (id),
    constraint FKlcvoteetgysdpfavfevmyh3en
        foreign key (id_chi_tiet_san_pham) references chi_tiet_san_pham (id),
    constraint FKr3utb9x7j00p1ghfj4mkhjyct
        foreign key (id_hoa_don) references hoa_don (id)
);

select *from nha_cung_cap;
select *from thuong_hieu;
select *from san_pham;
select *from anh;
select *from de_giay;
select *from kich_thuoc;
select *from mau_sac;
select *from nhan_vien;
select *from phieu_giam_gia;
select *from chi_tiet_san_pham;
select *from khach_hang;
select *from gio_hang;
select *from gio_hang_chi_tiet;
select *from hoa_don;
select *from hoa_don_chi_tiet;


INSERT INTO  nha_cung_cap (ma, ngay_sua, ngay_tao, ten, trang_thai)
VALUES ('NCC1', '2024-05-30', '2024-05-30', 'CTTNHH ', 0),
       ('NCC2', '2024-05-30', '2024-05-30', ' BEESTORE', 0),
       ('NCC3', '2024-05-30', '2024-05-30', 'STORE', 0),
       ('NCC4', '2024-05-30', '2024-05-30', 'CT E', 0),
       ('NCC5', '2024-05-30', '2024-05-30', 'CTTRE', 0),
       ('NCC6', '2024-05-30', '2024-05-30', 'CEESTORE', 0);


INSERT INTO thuong_hieu (ma, ngay_sua, ngay_tao, ten, trang_thai)
VALUES ('TH01', '2024-05-30', '2024-05-30', 'Nike', 0),
       ('TH02', '2024-05-30','2024-05-30', 'Adidas', 0),
       ('TH03', '2024-05-30', '2024-05-30', 'Vans', 0),
       ('TH04', '2024-05-30', '2024-05-30', 'Converse', 0);



INSERT INTO san_pham (anh_sanpham, ma, mo_ta, ngay_sua, ngay_tao, ten, trang_thai, id_nha_cung_cap, id_thuong_hieu)
VALUES


('sp1.png', 'SP01', 'Thể thao năng động', '2024-05-30', '2024-05-30', 'SanPham1', 0, 19, 1),
('sp2.png', 'SP02', 'Thể thao năng độg', '2024-05-30', '2024-05-30', 'SanPham2', 0, 20, 2),
('sp3.png', 'SP03', 'Thể thao năng ộng', '2024-05-30', '2024-05-30', 'SanPham3', 0, 21, 3),
('sp4.png', 'SP04', 'Thể thao năn động', '2024-05-30', '2024-05-30', 'SanPham4', 0, 22, 4);



INSERT INTO anh (url,ngay_sua,ngay_tao, id_san_pham)
VALUES
('sp1.png','2024-05-30', '2024-05-30',44),
('sp1.png','2024-05-30', '2024-05-30',44),
('sp1.png','2024-05-30', '2024-05-30',44),

('sp2.png','2024-05-30', '2024-05-30',45),
('sp2.png','2024-05-30', '2024-05-30',45),
('sp2.png','2024-05-30', '2024-05-30',45),

('sp3.png','2024-05-30', '2024-05-30',46),
('sp3.png','2024-05-30', '2024-05-30',46),
('sp3.png','2024-05-30', '2024-05-30',46),

('sp4.png','2024-05-30', '2024-05-30',47),
('sp4.png','2024-05-30', '2024-05-30',47),
('sp4.png','2024-05-30', '2024-05-30',47);



INSERT INTO de_giay (ma,ngay_tao,ngay_sua, ten)
VALUES
('DG01','2024-05-30', '2024-05-30', 'Đế Cao Su'),
('DG02','2024-05-30', '2024-05-30', 'Đế Hơi'),
('DG03','2024-05-30', '2024-05-30', 'Đế PU');




INSERT INTO kich_thuoc (ma,ngay_tao,ngay_sua, ten)
VALUES ('KT01','2024-05-30', '2024-05-30', '35'),
       ('KT02','2024-05-30', '2024-05-30', '36'),
       ('KT03','2024-05-30', '2024-05-30', '37'),
       ('KT04','2024-05-30', '2024-05-30', '38'),
       ('KT05','2024-05-30', '2024-05-30', '39'),
       ('KT06','2024-05-30', '2024-05-30', '40'),
       ('KT07','2024-05-30', '2024-05-30', '41'),
       ('KT08','2024-05-30', '2024-05-30', '42'),
       ('KT09','2024-05-30', '2024-05-30', '43'),
       ('KT010','2024-05-30', '2024-05-30', '44'),
       ('KT011','2024-05-30', '2024-05-30', '45');


INSERT INTO mau_sac (ma,ngay_tao,ngay_sua, ten)
VALUES ('MS01','2024-05-30', '2024-05-30','Xanh'),
       ('MS02','2024-05-30', '2024-05-30', 'Cam'),
       ('MS03','2024-05-30', '2024-05-30', 'Vàng'),
       ('MS04','2024-05-30', '2024-05-30', 'Lục'),
       ('MS05','2024-05-30', '2024-05-30', 'Lam'),
       ('MS06','2024-05-30', '2024-05-30', 'Đen-Trắng'),
       ('MS07','2024-05-30', '2024-05-30', 'Đen'),
       ('MS08','2024-05-30', '2024-05-30', 'Trắng'),
       ('MS09','2024-05-30', '2024-05-30', 'Xám');



INSERT INTO chi_tiet_san_pham (gia_ban, gia_mac_dinh, ngay_sua, ngay_tao, so_luong_ton, trang_thai,
                               id_de_giay, id_kich_thuoc, id_mau_sac, id_san_pham)
VALUES
(200000.00, 2000000.00, '2024-05-30', '2024-05-30', 100, 0, 1, 1, 3, 44),
(2500000.00, 2500000.00, '2024-05-30', '2024-05-30', 500, 0, 1, 1, 3, 45),
(1500000.00, 1500000.00, '2024-05-30', '2024-05-30', 50, 0, 1, 1, 3, 46),
(100000.00, 100000.00, '2024-05-30', '2024-05-30', 200, 0, 1, 1, 3, 47),
(1800000.00, 1800000.00, '2024-05-30', '2024-05-30', 300, 0, 1, 1, 3, 45),
(1400000.00, 1400000.00, '2024-05-30', '2024-05-30', 500, 0, 1, 1, 3, 44);


INSERT INTO nhan_vien (email, gioi_tinh, ma, mat_khau, ngay_sua, ngay_tao, sdt, ten, trang_thai)
VALUES ('duongpvph20350@fpt.edu.vn', 0, 'NV01', 'duong123@@', '2024-05-30', '2024-05-30', '0398194211', 'Phạm Văn Dương',0),
       ('andt20350@fpt.edu.vn', 0, 'NV02', 'an123@@','2024-05-30', '2024-05-30', '039819211', 'Đinh tuấn an',0);



INSERT INTO phieu_giam_gia (gia_tri_don_hang, ma, ten, mo_ta, muc_giam_gia, muc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc,
                            ngay_sua, ngay_tao, so_luong, trang_thai)
VALUES
(100000, 'PGG240530', 'PGG1', 'SALE HÈ VỀ', 15, 100000, '2024-05-30', '2024-05-31', '2024-05-30', '2024-05-30', 20, 1),
(200000, 'PGG240531', 'PGG2', 'SALE HÈ VỀ', 20, 100000, '2024-05-30', '2024-05-31', '2024-05-30', '2024-05-30', 30, 1),
(300000, 'PGG240529', 'PGG3', 'SALE HÈ VỀ', 30, 100000, '2024-05-30', '2024-05-31', '2024-05-30', '2024-05-30', 100, 1),
(400000, 'PGG240528', 'PGG3', 'SALE HÈ VỀ', 30, 100000, '2024-05-30', '2024-05-31', '2024-05-30', '2024-05-30', 100, 1);



INSERT INTO khach_hang (email, gioi_tinh, ma, mat_khau, ngay_sua, ngay_tao, sdt, ten,  trang_thai)
VALUES
('khach1@gmail.com', 1, 'KH01', '00000000',  '2024-05-30', '2024-05-30','0398194211',  'Khách 1',  0),
('khach2@gmail.com', 0, 'KH02', '00000000',  '2024-05-30', '2024-05-30', '0398194212', 'Khách 2',  0),
('khach3@gmail.com', 1, 'KH03', '00000000',  '2024-05-30', '2024-05-30', '0398194213', 'Khách 3',  0),
('khach4@gmail.com', 0, 'KH04', '00000000',  '2024-05-30', '2024-05-30', '0398194214', 'Khách 4',  0);




INSERT INTO gio_hang (ma, ngay_sua, ngay_tao, trang_thai, id_khach_hang)
VALUES ('GH01', '2024-05-30', '2024-05-30', 0, 13),
       ('GH02', '2024-05-30', '2024-05-30', 0, 14),
       ('GH03', '2024-05-30', '2024-05-30', 0, 15),
       ('GH04', '2024-05-30', '2024-05-30', 0, 16);



INSERT INTO hoa_don (dia_chi, ghi_chu, loai_hoa_don, ma, ngay_giao, ngay_sua, ngay_tao, ngay_thanh_toan, phi_van_chuyen,
                     sdt, ten_khach_hang, thanh_toan, tong_tien, trang_thai, id_khach_hang, id_phieu_giam_gia,
                     id_nhan_vien, tien_giam_gia)
VALUES
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000001', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 13, 12,1, 0.00),
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000002', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 14, 11,2, 0.00),
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000003', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 15, 10,1, 0.00),
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000004', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 16, 12,2, 0.00),
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000005', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 14, 11,1, 0.00),
('doi6, Xã Ngoc lu, Huyện Binh luc, Hà Nam', 'done', 1, 'HD0000006', '2024-05-31','2024-05-30', '2024-05-30', '2024-05-30', 0000, 0398194211, 'Phạm Văn Dương', 1773000.00, 1773000,  0, 15, 10,2, 0.00);







