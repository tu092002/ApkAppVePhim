package com.nht.apktestapp.Model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Ve {
    private int maVe;
    private int maPhim;

    private int maUser;
    private int maRap;
    private int maGhe;


    private String ngayDat;
    private String ngayXem;
    private  String thanhToan;

    public Ve() {
    }

    public Ve(int maVe, int maPhim, int maUser, int maRap, int maGhe, String ngayDat, String ngayXem, String thanhToan) {
        this.maVe = maVe;
        this.maPhim = maPhim;
        this.maUser = maUser;
        this.maRap = maRap;
        this.maGhe = maGhe;
        this.ngayDat = ngayDat;
        this.ngayXem = ngayXem;
        this.thanhToan = thanhToan;
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

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayXem() {
        return ngayXem;
    }

    public void setNgayXem(String ngayXem) {
        this.ngayXem = ngayXem;
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



    public String getNgayDat() {
        return ngayDat;
    }

}
