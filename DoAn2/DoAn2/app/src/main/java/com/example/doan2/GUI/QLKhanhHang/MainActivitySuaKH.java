package com.example.doan2.GUI.QLKhanhHang;

import static com.example.doan2.Database.KhachHangDAO.Sua_khachHang;
import static com.example.doan2.Database.KhachHangDAO.TT_KhachHang;
import static com.example.doan2.Database.NhanVienDAO.Sua_Nhanvien;
import static com.example.doan2.Database.NhanVienDAO.TT_NhanVien;
import static com.example.doan2.GUI.QLKhanhHang.MainActivityQLKhachHang.MAKH;
import static com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien.MANV;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.DTO.KhachHang;
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivitySuaNhanvien;
import com.example.doan2.R;

public class MainActivitySuaKH extends AppCompatActivity {
    private EditText editTextmkh, editTexttkh, editTextdc, editTextsdt;
    private Button btnsua;
    private ImageView ImageView_OnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_kh);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        editTextmkh = findViewById(R.id.editTextmkh);
        editTexttkh = findViewById(R.id.editTexttkh);
        editTextdc = findViewById(R.id.editTextdc);
        editTextsdt = findViewById(R.id.editTextsdt);
        btnsua = findViewById(R.id.btnsua);

        editTextmkh.setEnabled(false);

        KhachHang KhachHang = TT_KhachHang(MAKH);
        editTextmkh.setText(KhachHang.getMakh());
        editTexttkh.setText(KhachHang.getTenkh());
        editTextdc.setText(KhachHang.getDiachi());
        editTextsdt.setText(KhachHang.getSdt());
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaKH.this, MainActivityQLKhachHang.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkh = editTextmkh.getText().toString().trim();
                String tkh = editTexttkh.getText().toString().trim();
                String dc = editTextdc.getText().toString().trim();
                String sdt = editTextsdt.getText().toString().trim();
                if (mkh.isEmpty() || tkh.isEmpty() || dc.isEmpty() || sdt.isEmpty()) {
                    Toast.makeText(MainActivitySuaKH.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    KhachHang KhachHang = new KhachHang(mkh, tkh, dc, sdt);
                    if (Sua_khachHang(KhachHang)) {
                        startActivity(new Intent(MainActivitySuaKH.this, MainActivityQLKhachHang.class));
                        finish();
                        Toast.makeText(MainActivitySuaKH.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivitySuaKH.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}