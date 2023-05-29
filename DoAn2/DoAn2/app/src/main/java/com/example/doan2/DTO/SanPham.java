package com.example.doan2.DTO;

public class SanPham {

    private String masp, tensp, cauhinh;
    private int soluong, dongia;
    private byte[] hinhanh;

    public SanPham() {
    }

    public SanPham(String masp, String tensp, String cauhinh, int soluong, int dongia, byte[] hinhanh) {
        this.masp = masp;
        this.tensp = tensp;
        this.cauhinh = cauhinh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.hinhanh = hinhanh;
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

    public String getCauhinh() {
        return cauhinh;
    }

    public void setCauhinh(String cauhinh) {
        this.cauhinh = cauhinh;
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

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Override
    public String toString() {
        return getMasp();
    }
}
