package com.example.doan2.GUI.QLNhaCungCap;

import static com.example.doan2.Database.NhaCungCapDAO.Sua_NhaCungCap;
import static com.example.doan2.Database.NhaCungCapDAO.TT_NhaCungCap;
import static com.example.doan2.Database.NhaCungCapDAO.Them_NhaCungCap;
import static com.example.doan2.GUI.QLNhaCungCap.MainActivityQLNhaCungCap.MANCC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.DTO.NhaCungCap;
import com.example.doan2.R;

public class MainActivitySuaNCC extends AppCompatActivity {
    private EditText editTextm, editTextt;
    private Button btnsua;
    private ImageView ImageView_OnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_ncc);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        editTextm = findViewById(R.id.editTextm);
        editTextt = findViewById(R.id.editTextt);
        btnsua = findViewById(R.id.btnsua);
        editTextm.setEnabled(false);

        NhaCungCap nhaCungCap=TT_NhaCungCap(MANCC);
        editTextm.setText(nhaCungCap.getMancc());
        editTextt.setText(nhaCungCap.getTenncc());

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaNCC.this, MainActivityQLNhaCungCap.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = editTextm.getText().toString().trim();
                String ten = editTextt.getText().toString().trim();
                if (ma.isEmpty() || ma.isEmpty()) {
                    Toast.makeText(MainActivitySuaNCC.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    NhaCungCap nhaCungCap = new NhaCungCap(ma, ten);
                    if (Sua_NhaCungCap(nhaCungCap)) {
                        startActivity(new Intent(MainActivitySuaNCC.this, MainActivityQLNhaCungCap.class));
                        finish();
                        Toast.makeText(MainActivitySuaNCC.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivitySuaNCC.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}