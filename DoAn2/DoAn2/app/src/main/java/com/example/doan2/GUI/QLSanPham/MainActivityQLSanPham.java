package com.example.doan2.GUI.QLSanPham;

import static com.example.doan2.Database.NhanVienDAO.DS_NhanVien;
import static com.example.doan2.Database.NhanVienDAO.timkiem_mnv;
import static com.example.doan2.Database.SanPhamDAO.DS_SanPham;
import static com.example.doan2.Database.SanPhamDAO.timkiem_;
import static com.example.doan2.GUI.MainActivity.CV_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.BUS.NhanVienAdapter;
import com.example.doan2.BUS.SanPhamAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.R;

public class MainActivityQLSanPham extends AppCompatActivity {
    private RecyclerView dssp;
    private EditText timkiem;
    private ImageButton them,cn;
    public static String MASP="";
    private SanPhamAdapter sanPhamAdapter;
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlsan_pham);
        dssp=findViewById(R.id.dssp);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
        dssp.setLayoutManager(new LinearLayoutManager(this));
        sanPhamAdapter=new SanPhamAdapter(DS_SanPham(),this);
        dssp.setAdapter(sanPhamAdapter);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLSanPham.this, MainActivityHome.class));
                finish();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")){
                    startActivity(new Intent(MainActivityQLSanPham.this, MainActivityThemSanPham.class));
                }else {
                    Toast.makeText(MainActivityQLSanPham.this, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamAdapter=new SanPhamAdapter(DS_SanPham(),MainActivityQLSanPham.this);
                dssp.setAdapter(sanPhamAdapter);
            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (timkiem.getText().toString().trim().isEmpty()){
                    sanPhamAdapter=new SanPhamAdapter(DS_SanPham(),MainActivityQLSanPham.this);
                    dssp.setAdapter(sanPhamAdapter);
                }else {
                    sanPhamAdapter=new SanPhamAdapter(timkiem_(timkiem.getText().toString().trim()),MainActivityQLSanPham.this);
                    dssp.setAdapter(sanPhamAdapter);
                }
            }
        });
    }
}