package com.example.doan2.Database;

import static com.example.doan2.GUI.MainActivity.database;
import static com.example.doan2.GUI.MainActivityHome.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan2.DTO.NhaCungCap;
import com.example.doan2.DTO.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public static Boolean Them_NhaCungCap(NhaCungCap nhaCungCap) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MANCC", nhaCungCap.getMancc());
        contentValues.put("TENNCC", nhaCungCap.getTenncc());

        long result = MyDB.insert("NhaCungCap", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<NhaCungCap> DS_NhaCungCap() {
        List<NhaCungCap> NhaCungCaps = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From NhaCungCap", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhaCungCap NhaCungCap = new NhaCungCap();
                NhaCungCap.setMancc(cursor.getString(0));
                NhaCungCap.setTenncc(cursor.getString(1));
                NhaCungCaps.add(NhaCungCap);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return NhaCungCaps;
    }

    public static NhaCungCap TT_NhaCungCap(String MANCC) {
        NhaCungCap NhaCungCap = new NhaCungCap();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from NhaCungCap where MANCC = ?", new String[]{MANCC});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            NhaCungCap.setMancc(cursor.getString(0));
            NhaCungCap.setTenncc(cursor.getString(1));
            cursor.close();
            return NhaCungCap;
        }
        return null;
    }

    public static List<NhaCungCap> timkiem_ncc(String MANCC) {
        List<NhaCungCap> NhaCungCaps = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From NhaCungCap where MANCC LIKE ?", new String[]{"%" + MANCC + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhaCungCap NhaCungCap = new NhaCungCap();
                NhaCungCap.setMancc(cursor.getString(0));
                NhaCungCap.setTenncc(cursor.getString(1));
                NhaCungCaps.add(NhaCungCap);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return NhaCungCaps;
    }

    public static boolean Sua_NhaCungCap(NhaCungCap nhaCungCap) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MANCC", nhaCungCap.getMancc());
        contentValues.put("TENNCC", nhaCungCap.getTenncc());
        long result = MyDB.update("NhaCungCap", contentValues, "MANCC=?", new String[]{nhaCungCap.getMancc()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_NhaCungCap(String MANCC) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("NhaCungCap", "MANCC=?", new String[]{MANCC});
        if (result == -1)
            return false;
        else
            return true;
    }
}
