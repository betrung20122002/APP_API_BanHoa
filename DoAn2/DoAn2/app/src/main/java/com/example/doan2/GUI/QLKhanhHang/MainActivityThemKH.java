package com.example.doan2.GUI.QLKhanhHang;

import static com.example.doan2.Database.KhachHangDAO.Them_KhachHang;
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

import com.example.doan2.DTO.KhachHang;
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.R;

public class MainActivityThemKH extends AppCompatActivity {
    private EditText editTextmkh,editTexttkh,editTextdc,editTextsdt;
    private Button btnthem;
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_kh);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        editTextmkh=findViewById(R.id.editTextmkh);
        editTexttkh=findViewById(R.id.editTexttkh);
        editTextdc=findViewById(R.id.editTextdc);
        editTextsdt=findViewById(R.id.editTextsdt);
        btnthem=findViewById(R.id.btnthem);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemKH.this, MainActivityQLKhachHang.class));
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkh=editTextmkh.getText().toString().trim();
                String tkh=editTexttkh.getText().toString().trim();
                String dc=editTextdc.getText().toString().trim();
                String sdt=editTextsdt.getText().toString().trim();
                if (mkh.isEmpty()||tkh.isEmpty()||dc.isEmpty()||sdt.isEmpty()){
                    Toast.makeText(MainActivityThemKH.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    KhachHang khachHang=new KhachHang(mkh,tkh,dc,sdt);
                    if (Them_KhachHang(khachHang)){
                        editTextmkh.setText("");
                        editTexttkh.setText("");
                        editTextdc.setText("");
                        editTextsdt.setText("");
                        Toast.makeText(MainActivityThemKH.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivityThemKH.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}