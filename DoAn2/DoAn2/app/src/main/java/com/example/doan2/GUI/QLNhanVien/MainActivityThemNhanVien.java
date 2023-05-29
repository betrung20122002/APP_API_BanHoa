package com.example.doan2.GUI.QLNhanVien;

import static com.example.doan2.Database.NhanVienDAO.Them_NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.DTO.NhanVien;
import com.example.doan2.R;

public class MainActivityThemNhanVien extends AppCompatActivity {
    private EditText editTextmnv,editTexttnv,editTextdc,editTextsdt;
    private Button btnthem;
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_nhan_vien);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        editTextmnv=findViewById(R.id.editTextmnv);
        editTexttnv=findViewById(R.id.editTexttnv);
        editTextdc=findViewById(R.id.editTextdc);
        editTextsdt=findViewById(R.id.editTextsdt);
        btnthem=findViewById(R.id.btnthem);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemNhanVien.this,MainActivityQLNhanVien.class));
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mnv=editTextmnv.getText().toString().trim();
                String tnv=editTexttnv.getText().toString().trim();
                String dc=editTextdc.getText().toString().trim();
                String sdt=editTextsdt.getText().toString().trim();

                Log.e("manv",mnv+tnv+dc+sdt);
                if (mnv.isEmpty()||tnv.isEmpty()||dc.isEmpty()||sdt.isEmpty()){
                    Toast.makeText(MainActivityThemNhanVien.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    NhanVien nhanVien=new NhanVien(mnv,tnv,dc,sdt);
                    if (Them_NhanVien(nhanVien)){
                        editTextmnv.setText("");
                        editTexttnv.setText("");
                        editTextdc.setText("");
                        editTextsdt.setText("");
                        Toast.makeText(MainActivityThemNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivityThemNhanVien.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}