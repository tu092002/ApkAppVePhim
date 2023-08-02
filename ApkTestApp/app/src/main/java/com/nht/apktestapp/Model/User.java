package com.nht.apktestapp.Model;

public class User {
    private static int  maUser;
    private static String hoTen;
    private static String username;

    private static String password;



    private static String role;
    private static byte[]  avt;
    private static String online;

    public static String getOnline() {
        return online;
    }

    public static void setOnline(String online) {
        User.online = online;
    }

    public static String getUsername() {
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




    public static byte[] getAvt() {
        return avt;
    }

    public static void setAvt(byte[] avt) {
        User.avt = avt;
    }



    public static int getMaUser() {
        return maUser;
    }

    public static void setMaUser(int maUser) {
        User.maUser = maUser;
    }

    public static String getHoTen() {
        return hoTen;
    }

    public static void setHoTen(String hoTen) {
        User.hoTen = hoTen;
    }

    public static String getUserName() {
        return username;
    }


    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        User.role = role;
    }


}
