package com.nht.apktestapp.Model;

public class Phim {

    private int maPhim;
    private String tenPhim;
    private String moTa;
    private byte[] imgPhim;
    private double giaPhim;


    public double getGiaPhim() {
        return giaPhim;
    }

    public void setGiaPhim(double giaPhim) {
        this.giaPhim = giaPhim;
    }

    public Phim(int maPhim, String tenPhim, String moTa, byte[] imgPhim, double giaPhim) {
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.imgPhim = imgPhim;
        this.maPhim = maPhim;
        this.giaPhim= giaPhim;
    }


    public byte[] getImgPhim() {
        return imgPhim;
    }

    public void setImgPhim(byte[] imgPhim) {
        this.imgPhim = imgPhim;
    }


    public Phim() {
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }


}
