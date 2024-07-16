package com.example.datnsum24sd01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "xa_phuong")
public class XaPhuong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @ManyToOne
    @JoinColumn(name = "quan_huyen_id", nullable = false)
    private QuanHuyen quanHuyen;
}
