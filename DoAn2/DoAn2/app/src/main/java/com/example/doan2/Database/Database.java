package com.example.doan2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "Quanlycuahang.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TaiKhoan(EMAIL TEXT PRIMARY KEY, TENDANGNHAP TEXT, MATKHAU TEXT,CHUCVU TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Nhanvien(MANV TEXT PRIMARY KEY, TENNV TEXT, DIACHI TEXT,SDT TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SanPham(MASP TEXT PRIMARY KEY, TENSP TEXT, SOLUONG DOUBLE, DONGIA DOUBLE, CAUHINHMAY TEXT,HINHANH BLOB)");
        db.execSQL("CREATE TABLE IF NOT EXISTS NhaCungCap(MANCC TEXT PRIMARY KEY, TENNCC TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS KhachHang(MAKH TEXT PRIMARY KEY, TENKH TEXT, DIACHI TEXT,SDT TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS HoaDonNhap(MAHDN TEXT PRIMARY KEY, MASP TEXT, TENSP TEXT, SOLUONG DOUBLE, DONGIA DOUBLE, TONGTIEN DOUBLE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS HoaDonXuat(MAHDX TEXT PRIMARY KEY, MASP TEXT, TENSP TEXT,MAKH TEXT, SOLUONG DOUBLE, DONGIA DOUBLE, TONGTIEN DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
