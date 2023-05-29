package com.example.doan2.DTO;

public class TaiKhoan {
    String EMAIL, TENDANGNHAP, MATKHAU, CHUCVU;

    public TaiKhoan() {
    }

    public TaiKhoan(String EMAIL, String TENDANGNHAP, String MATKHAU, String CHUCVU) {
        this.EMAIL = EMAIL;
        this.TENDANGNHAP = TENDANGNHAP;
        this.MATKHAU = MATKHAU;
        this.CHUCVU = CHUCVU;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getTENDANGNHAP() {
        return TENDANGNHAP;
    }

    public void setTENDANGNHAP(String TENDANGNHAP) {
        this.TENDANGNHAP = TENDANGNHAP;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(String CHUCVU) {
        this.CHUCVU = CHUCVU;
    }
}
