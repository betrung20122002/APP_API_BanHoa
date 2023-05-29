package com.example.doan2.GUI.QLKhanhHang;

import static com.example.doan2.Database.KhachHangDAO.DS_KhachHang;
import static com.example.doan2.Database.KhachHangDAO.timkiem_kh;
import static com.example.doan2.Database.NhanVienDAO.DS_NhanVien;
import static com.example.doan2.Database.NhanVienDAO.timkiem_mnv;
import static com.example.doan2.GUI.MainActivity.CV_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.BUS.KhanhHangAdapter;
import com.example.doan2.BUS.NhanVienAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityQLKhachHang extends AppCompatActivity {
    private KhanhHangAdapter khanhHangAdapter;
    private RecyclerView dskh;
    private EditText timkiem;
    private ImageButton them, cn;
    public static String MAKH = "";
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlkhach_hang);
        dskh = findViewById(R.id.dskh);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
        dskh.setLayoutManager(new LinearLayoutManager(this));
        khanhHangAdapter = new KhanhHangAdapter(DS_KhachHang(), this);
        dskh.setAdapter(khanhHangAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLKhachHang.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivityQLKhachHang.this, MainActivityThemKH.class));

            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khanhHangAdapter = new KhanhHangAdapter(DS_KhachHang(), MainActivityQLKhachHang.this);
                dskh.setAdapter(khanhHangAdapter);

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
                if (timkiem.getText().toString().trim().isEmpty()) {
                    khanhHangAdapter = new KhanhHangAdapter(DS_KhachHang(), MainActivityQLKhachHang.this);
                    dskh.setAdapter(khanhHangAdapter);
                } else {
                    khanhHangAdapter = new KhanhHangAdapter(timkiem_kh(timkiem.getText().toString().trim()), MainActivityQLKhachHang.this);
                    dskh.setAdapter(khanhHangAdapter);
                }
            }
        });
    }
}