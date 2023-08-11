package com.nht.apktestapp.Model;

import java.time.LocalDateTime;

public class Ve {
    private int maVe;
    private int maPhim;

    private int maUser;
    private int maRap;
    private int maGhe;
    private double giaVe;


    private LocalDateTime ngayDat;
    private LocalDateTime ngayXem;
    private String thanhToan = "false";

    public Ve() {
    }

    public Ve(int maVe, int maPhim, int maUser, int maRap, int maGhe, LocalDateTime ngayDat, LocalDateTime ngayXem,double giaVe, String thanhToan) {
        this.maVe = maVe;
        this.maPhim = maPhim;
        this.maUser = maUser;
        this.maRap = maRap;
        this.maGhe = maGhe;
        this.ngayDat = ngayDat;
        this.ngayXem = ngayXem;
        this.giaVe = giaVe;
        this.thanhToan = thanhToan;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }

    public LocalDateTime getNgayXem() {
        return ngayXem;
    }

    public void setNgayXem(LocalDateTime ngayXem) {
        this.ngayXem = ngayXem;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public int getMaRap() {
        return maRap;
    }

    public void setMaRap(int maRap) {
        this.maRap = maRap;
    }


    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public int getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(int maGhe) {
        this.maGhe = maGhe;
    }


}
