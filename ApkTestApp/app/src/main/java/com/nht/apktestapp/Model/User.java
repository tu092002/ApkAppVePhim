package com.nht.apktestapp.Model;

public class User {
    private  int  maUser;
    private  String hoTen;
    private String username;

    private  String password;



    private  String role;
    private  byte[]  avt;
    private  String online;

    public  String getOnline() {
        return online;
    }

    public  void setOnline(String online) {
        this.online = online;
    }

    public  String getUsername() {
        return username;
    }

    public User (){

    }

    public User(int maUser, String hoTen, String username, String password, String role, byte[] avt, String online) {
        this.maUser = maUser;
        this.hoTen = hoTen;
        this.username = username;
        this.password = password;
        this.role =role;
        this.avt = avt;
        this.online = online;
    }




    public byte[] getAvt() {
        return avt;
    }

    public  void setAvt(byte[] avt) {
        this.avt = avt;
    }



    public  int getMaUser() {
        return maUser;
    }

    public  void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public  String getHoTen() {
        return hoTen;
    }

    public  void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public  String getUserName() {
        return username;
    }


    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  String getRole() {
        return role;
    }

    public  void setRole(String role) {
        this.role = role;
    }


}
