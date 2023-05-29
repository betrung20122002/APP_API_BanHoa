package com.example.doan2.DTO;

public class NhaCungCap {
    private String mancc, tenncc;

    public NhaCungCap() {
    }

    public NhaCungCap(String mancc, String tenncc) {
        this.mancc = mancc;
        this.tenncc = tenncc;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }
}
