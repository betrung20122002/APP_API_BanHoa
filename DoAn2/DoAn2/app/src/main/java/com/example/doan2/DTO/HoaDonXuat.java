package com.example.doan2.DTO;

public class HoaDonXuat {
    private String mahdx, masp, tensp,makh;
    private int soluong, dongia, tongtien;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String mahdx, String masp, String tensp, String makh, int soluong, int dongia, int tongtien) {
        this.mahdx = mahdx;
        this.masp = masp;
        this.tensp = tensp;
        this.makh = makh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtien = tongtien;
    }

    public String getMahdx() {
        return mahdx;
    }

    public void setMahdx(String mahdx) {
        this.mahdx = mahdx;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
