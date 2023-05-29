package com.example.doan2.Database;

import static com.example.doan2.GUI.MainActivity.database;
import static com.example.doan2.GUI.MainActivityHome.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan2.DTO.NhanVien;
import com.example.doan2.DTO.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public static Boolean Them_SanPham(SanPham sanPham) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MASP", sanPham.getMasp());
        contentValues.put("TENSP", sanPham.getTensp());
        contentValues.put("SOLUONG", sanPham.getSoluong());
        contentValues.put("DONGIA", sanPham.getDongia());
        contentValues.put("CAUHINHMAY", sanPham.getCauhinh());
        contentValues.put("HINHANH", sanPham.getHinhanh());
        long result = MyDB.insert("SanPham", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<SanPham> DS_SanPham() {
        List<SanPham> SanPhams = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From SanPham", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SanPham sanPham = new SanPham();
                sanPham.setMasp(cursor.getString(0));
                sanPham.setTensp(cursor.getString(1));
                sanPham.setSoluong(cursor.getInt(2));
                sanPham.setDongia(cursor.getInt(3));
                sanPham.setCauhinh(cursor.getString(4));
                sanPham.setHinhanh(cursor.getBlob(5));
                SanPhams.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return SanPhams;
    }

    public static SanPham TT_SanPham(String MASP) {
        SanPham sanPham = new SanPham();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from SanPham where MASP = ?", new String[]{MASP});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            sanPham.setMasp(cursor.getString(0));
            sanPham.setTensp(cursor.getString(1));
            sanPham.setSoluong(cursor.getInt(2));
            sanPham.setDongia(cursor.getInt(3));
            sanPham.setCauhinh(cursor.getString(4));
            sanPham.setHinhanh(cursor.getBlob(5));
            cursor.close();
            return sanPham;
        }
        return null;
    }

    public static List<SanPham> timkiem_(String MASP) {
        List<SanPham> SanPhams = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From SanPham where MASP LIKE ?", new String[]{"%" + MASP + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SanPham sanPham = new SanPham();
                sanPham.setMasp(cursor.getString(0));
                sanPham.setTensp(cursor.getString(1));
                sanPham.setSoluong(cursor.getInt(2));
                sanPham.setDongia(cursor.getInt(3));
                sanPham.setCauhinh(cursor.getString(4));
                sanPham.setHinhanh(cursor.getBlob(5));
                SanPhams.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return SanPhams;
    }

    public static boolean Sua_SanPham(SanPham sanPham) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENSP", sanPham.getTensp());
        contentValues.put("SOLUONG", sanPham.getSoluong());
        contentValues.put("DONGIA", sanPham.getDongia());
        contentValues.put("CAUHINHMAY", sanPham.getCauhinh());
        contentValues.put("HINHANH", sanPham.getHinhanh());
        long result = MyDB.update("SanPham", contentValues, "MASP=?", new String[]{sanPham.getMasp()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_sp(String MASP) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("SanPham", "MASP=?", new String[]{MASP});
        if (result == -1)
            return false;
        else
            return true;
    }
}
