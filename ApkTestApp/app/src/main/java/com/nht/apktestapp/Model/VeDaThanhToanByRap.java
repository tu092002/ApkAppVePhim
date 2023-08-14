package com.nht.apktestapp.Model;

public class VeDaThanhToanByRap {
    private String tenRap;
    private int soVeDaThanhToan;

    public VeDaThanhToanByRap(){

    }
    public VeDaThanhToanByRap(String tenRap, int soVeDaThanhToan) {
        this.tenRap = tenRap;
        this.soVeDaThanhToan = soVeDaThanhToan;

    }

    public String getTenRap() {
        return tenRap;
    }

    public int getSoVeDaThanhToan() {
        return soVeDaThanhToan;
    }
    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public void setSoVeDaThanhToan(int soVe) {
        this.soVeDaThanhToan = soVe;
    }



}