package com.example.doan2.GUI.QLNhaCungCap;

import static com.example.doan2.Database.NhaCungCapDAO.Them_NhaCungCap;
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

import com.example.doan2.DTO.NhaCungCap;
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.R;

public class MainActivityThemNCC extends AppCompatActivity {
    private EditText editTextm, editTextt;
    private Button btnthem;
    private ImageView ImageView_OnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_ncc);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        editTextm = findViewById(R.id.editTextm);
        editTextt = findViewById(R.id.editTextt);
        btnthem = findViewById(R.id.btnthem);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemNCC.this, MainActivityQLNhaCungCap.class));
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = editTextm.getText().toString().trim();
                String ten = editTextt.getText().toString().trim();
                if (ma.isEmpty() || ma.isEmpty()) {
                    Toast.makeText(MainActivityThemNCC.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    NhaCungCap nhaCungCap = new NhaCungCap(ma, ten);
                    if (Them_NhaCungCap(nhaCungCap)) {
                        editTextm.setText("");
                        editTextt.setText("");
                        Toast.makeText(MainActivityThemNCC.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivityThemNCC.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}