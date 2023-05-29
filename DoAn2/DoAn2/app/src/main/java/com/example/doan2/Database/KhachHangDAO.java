package com.example.doan2.Database;

import static com.example.doan2.GUI.MainActivity.database;
import static com.example.doan2.GUI.MainActivityHome.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan2.DTO.KhachHang;
import com.example.doan2.DTO.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    public static Boolean Them_KhachHang(KhachHang khachHang) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAKH", khachHang.getMakh());
        contentValues.put("TENKH", khachHang.getTenkh());
        contentValues.put("DIACHI", khachHang.getDiachi());
        contentValues.put("SDT", khachHang.getSdt());
        long result = MyDB.insert("KhachHang", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<KhachHang> DS_KhachHang() {
        List<KhachHang> KhachHangs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From KhachHang", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                KhachHang KhachHang = new KhachHang();
                KhachHang.setMakh(cursor.getString(0));
                KhachHang.setTenkh(cursor.getString(1));
                KhachHang.setDiachi(cursor.getString(2));
                KhachHang.setSdt(cursor.getString(3));
                KhachHangs.add(KhachHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return KhachHangs;
    }

    public static KhachHang TT_KhachHang(String MAKH) {
        KhachHang KhachHang = new KhachHang();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from KhachHang where MAKH = ?", new String[]{MAKH});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            KhachHang.setMakh(cursor.getString(0));
            KhachHang.setTenkh(cursor.getString(1));
            KhachHang.setDiachi(cursor.getString(2));
            KhachHang.setSdt(cursor.getString(3));
            cursor.close();
            return KhachHang;
        }
        return null;
    }

    public static List<KhachHang> timkiem_kh(String MAKH) {
        List<KhachHang> KhachHangs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From KhachHang where MAKH LIKE ?", new String[]{"%" + MAKH + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                KhachHang KhachHang = new KhachHang();
                KhachHang.setMakh(cursor.getString(0));
                KhachHang.setTenkh(cursor.getString(1));
                KhachHang.setDiachi(cursor.getString(2));
                KhachHang.setSdt(cursor.getString(3));
                KhachHangs.add(KhachHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return KhachHangs;
    }

    public static boolean Sua_khachHang(KhachHang khachHang) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENKH", khachHang.getTenkh());
        contentValues.put("DIACHI", khachHang.getDiachi());
        contentValues.put("SDT", khachHang.getSdt());
        long result = MyDB.update("KhachHang", contentValues, "MAKH=?", new String[]{khachHang.getMakh()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_kh(String MAKH) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("KhachHang", "MAKH=?", new String[]{MAKH});
        if (result == -1)
            return false;
        else
            return true;
    }
}
