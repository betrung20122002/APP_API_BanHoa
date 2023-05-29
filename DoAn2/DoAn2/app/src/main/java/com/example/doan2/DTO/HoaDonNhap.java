package com.example.doan2.DTO;

public class HoaDonNhap {
    private String mahdn, masp, tensp;
    private int soluong, dongia, tongtien;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String mahdn, String masp, String tensp, int soluong, int dongia, int tongtien) {
        this.mahdn = mahdn;
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtien = tongtien;
    }

    public String getMahdn() {
        return mahdn;
    }

    public void setMahdn(String mahdn) {
        this.mahdn = mahdn;
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
