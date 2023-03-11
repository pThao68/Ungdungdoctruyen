package com.example.ungdungdoctruyen.model;

public class Truyen {
    private int iD;
    private String tenTruyen;
    private String noiDung;
    private String Anh;
    private int id_TK;


    public Truyen() {
    }

    public Truyen(String tenTruyen, String noiDung, String anh, int id_TK) {
        this.tenTruyen = tenTruyen;
        this.noiDung = noiDung;
        Anh = anh;
        this.id_TK = id_TK;
    }

    public Truyen(int iD, String tenTruyen, String noiDung, String anh, int id_TK) {
        this.iD = iD;
        this.tenTruyen = tenTruyen;
        this.noiDung = noiDung;
        Anh = anh;
        this.id_TK = id_TK;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getId_TK() {
        return id_TK;
    }

    public void setId_TK(int id_TK) {
        this.id_TK = id_TK;
    }
}
