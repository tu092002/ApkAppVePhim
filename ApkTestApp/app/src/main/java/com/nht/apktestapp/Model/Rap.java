package com.nht.apktestapp.Model;

public class Rap {
    private  int maRap;
    private String tenRap;

    private String moTaRap;
    private byte[] imgRap;

    public Rap(int maRap, String tenRap, String moTaRap, byte[] imgRap) {
        this.maRap = maRap;
        this.tenRap = tenRap;
        this.moTaRap = moTaRap;
        this.imgRap = imgRap;
    }

    public String getMoTaRap() {
        return moTaRap;
    }

    public void setMoTaRap(String moTaRap) {
        this.moTaRap = moTaRap;
    }

    public byte[] getImgRap() {
        return imgRap;
    }

    public void setImgRap(byte[] imgRap) {
        this.imgRap = imgRap;
    }

    public Rap() {
    }

    public int getMaRap() {
        return maRap;
    }

    public void setMaRap(int maRap) {
        this.maRap = maRap;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }
}
