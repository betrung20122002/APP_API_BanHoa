package com.example.doan2.Database;

import static com.example.doan2.GUI.MainActivity.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan2.DTO.TaiKhoan;

public class TaiKhoanDAO {
    public static Boolean Them_TaiKhoan(TaiKhoan tk) {
        SQLiteDatabase MyDB = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", tk.getEMAIL());
        contentValues.put("TENDANGNHAP", tk.getTENDANGNHAP());
        contentValues.put("MATKHAU", tk.getMATKHAU());
        contentValues.put("CHUCVU", tk.getCHUCVU());
        long result = MyDB.insert("TaiKhoan", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static Boolean kiem_tra_EMAIL_(String EMAIL) {
        SQLiteDatabase MyDB = database.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where EMAIL = ?", new String[]{EMAIL});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public static Boolean kiem_tra_TDN_(String TDN) {
        SQLiteDatabase MyDB = database.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where TENDANGNHAP = ?", new String[]{TDN});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public static Boolean KiemTra_DN(String TENDANGNHAP, String MATKHAU, String CHUCVU) {
        SQLiteDatabase MyDB = database.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from taikhoan where TENDANGNHAP = ? and MATKHAU = ? and CHUCVU = ?", new String[]{TENDANGNHAP, MATKHAU,CHUCVU});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public static TaiKhoan get_thongtin(String email) {
        TaiKhoan studen = new TaiKhoan();
        SQLiteDatabase MyDB = database.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from taikhoan where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
//            studen.setIdtk(cursor.getInt(0));
//            studen.setEmail(cursor.getString(1));
//            studen.setHoten(cursor.getString(2));
//            studen.setMk(cursor.getString(3));
//            studen.setSdt(cursor.getString(4));
//            studen.setDiachi(cursor.getString(5));
            cursor.close();
            return studen;
        }
        return null;
    }

}
