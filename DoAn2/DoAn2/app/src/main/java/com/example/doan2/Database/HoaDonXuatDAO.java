package com.example.doan2.Database;

import static com.example.doan2.GUI.MainActivity.database;
import static com.example.doan2.GUI.MainActivityHome.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan2.DTO.HoaDonNhap;
import com.example.doan2.DTO.HoaDonXuat;

import java.util.ArrayList;
import java.util.List;

public class HoaDonXuatDAO {

    public static Boolean Them_HDX(HoaDonXuat HoaDonXuat) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAHDX", HoaDonXuat.getMahdx());
        contentValues.put("MASP", HoaDonXuat.getMasp());
        contentValues.put("TENSP", HoaDonXuat.getTensp());
        contentValues.put("MAKH", HoaDonXuat.getMakh());
        contentValues.put("SOLUONG", HoaDonXuat.getSoluong());
        contentValues.put("DONGIA", HoaDonXuat.getDongia());
        contentValues.put("TONGTIEN", HoaDonXuat.getTongtien());
        long result = MyDB.insert("HoaDonXuat", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<HoaDonXuat> DS_HDX() {
        List<HoaDonXuat> HoaDonXuats = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonXuat", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonXuat HoaDonXuat = new HoaDonXuat();
                HoaDonXuat.setMahdx(cursor.getString(0));
                HoaDonXuat.setMasp(cursor.getString(1));
                HoaDonXuat.setTensp(cursor.getString(2));
                HoaDonXuat.setMakh(cursor.getString(3));
                HoaDonXuat.setSoluong(cursor.getInt(4));
                HoaDonXuat.setDongia(cursor.getInt(5));
                HoaDonXuat.setTongtien(cursor.getInt(6));
                HoaDonXuats.add(HoaDonXuat);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return HoaDonXuats;
    }

    public static HoaDonXuat TT_HoaDonXuat(String MAHDN) {
        HoaDonXuat HoaDonXuat = new HoaDonXuat();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from HoaDonXuat where MAHDX = ?", new String[]{MAHDN});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            HoaDonXuat.setMahdx(cursor.getString(0));
            HoaDonXuat.setMasp(cursor.getString(1));
            HoaDonXuat.setTensp(cursor.getString(2));
            HoaDonXuat.setMakh(cursor.getString(3));
            HoaDonXuat.setSoluong(cursor.getInt(4));
            HoaDonXuat.setDongia(cursor.getInt(5));
            HoaDonXuat.setTongtien(cursor.getInt(6));
            cursor.close();
            return HoaDonXuat;
        }
        return null;
    }

    public static List<HoaDonXuat> timkiem_mhx(String MAHDX) {
        List<HoaDonXuat> HoaDonXuats = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonXuat where MAHDX LIKE ?", new String[]{"%" + MAHDX + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonXuat HoaDonXuat = new HoaDonXuat();
                HoaDonXuat.setMahdx(cursor.getString(0));
                HoaDonXuat.setMasp(cursor.getString(1));
                HoaDonXuat.setTensp(cursor.getString(2));
                HoaDonXuat.setMakh(cursor.getString(3));
                HoaDonXuat.setSoluong(cursor.getInt(4));
                HoaDonXuat.setDongia(cursor.getInt(5));
                HoaDonXuat.setTongtien(cursor.getInt(6));
                HoaDonXuats.add(HoaDonXuat);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return HoaDonXuats;
    }

    public static boolean Sua_HoaDonXuat(HoaDonXuat HoaDonXuat) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MASP", HoaDonXuat.getMasp());
        contentValues.put("TENSP", HoaDonXuat.getTensp());
        contentValues.put("MAKH", HoaDonXuat.getMakh());
        contentValues.put("SOLUONG", HoaDonXuat.getSoluong());
        contentValues.put("DONGIA", HoaDonXuat.getDongia());
        contentValues.put("TONGTIEN", HoaDonXuat.getTongtien());
        long result = MyDB.update("HoaDonXuat", contentValues, "MAHDX=?", new String[]{HoaDonXuat.getMahdx()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_hdx(String MAHDX) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("HoaDonXuat", "MAHDX=?", new String[]{MAHDX});
        if (result == -1)
            return false;
        else
            return true;
    }
}
