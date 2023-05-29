package com.example.doan2.GUI.QLNhanVien;

import static com.example.doan2.Database.NhanVienDAO.Sua_Nhanvien;
import static com.example.doan2.Database.NhanVienDAO.TT_NhanVien;
import static com.example.doan2.Database.NhanVienDAO.Them_NhanVien;
import static com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien.MANV;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.DTO.NhanVien;
import com.example.doan2.R;

public class MainActivitySuaNhanvien extends AppCompatActivity {
    private EditText editTextmnv,editTexttnv,editTextdc,editTextsdt;
    private Button btnsua;
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_nhanvien);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        editTextmnv=findViewById(R.id.editTextmnv);
        editTexttnv=findViewById(R.id.editTexttnv);
        editTextdc=findViewById(R.id.editTextdc);
        editTextsdt=findViewById(R.id.editTextsdt);
        btnsua=findViewById(R.id.btnsua);

        editTextmnv.setEnabled(false);

        NhanVien nhanVien=TT_NhanVien(MANV);
        editTextmnv.setText(nhanVien.getManv());
        editTexttnv.setText(nhanVien.getTennv());
        editTextdc.setText(nhanVien.getDiachi());
        editTextsdt.setText(nhanVien.getSdt());
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaNhanvien.this,MainActivityQLNhanVien.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mnv=editTextmnv.getText().toString().trim();
                String tnv=editTexttnv.getText().toString().trim();
                String dc=editTextdc.getText().toString().trim();
                String sdt=editTextsdt.getText().toString().trim();
                if (mnv.isEmpty()||tnv.isEmpty()||dc.isEmpty()||sdt.isEmpty()){
                    Toast.makeText(MainActivitySuaNhanvien.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    NhanVien nhanVien=new NhanVien(mnv,tnv,dc,sdt);
                    if (Sua_Nhanvien(nhanVien)){
                        startActivity(new Intent(MainActivitySuaNhanvien.this,MainActivityQLNhanVien.class));
                        finish();
                        Toast.makeText(MainActivitySuaNhanvien.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivitySuaNhanvien.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}