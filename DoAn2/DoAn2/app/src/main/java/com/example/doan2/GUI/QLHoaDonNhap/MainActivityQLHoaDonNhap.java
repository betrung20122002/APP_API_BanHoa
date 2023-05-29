package com.example.doan2.GUI.QLHoaDonNhap;

import static com.example.doan2.Database.HoaDonNhapDAO.DS_HDN;
import static com.example.doan2.Database.HoaDonNhapDAO.timkiem_mhd;
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

import com.example.doan2.BUS.HoaDonNhapAdapter;
import com.example.doan2.BUS.NhanVienAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLNhaCungCap.MainActivityQLNhaCungCap;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityQLHoaDonNhap extends AppCompatActivity {
    private HoaDonNhapAdapter hoaDonNhapAdapter;
    private RecyclerView dshdn;
    private EditText timkiem;
    private ImageButton them,cn;
    public static String MAHDN="";
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlhoa_don_nhap);
        dshdn=findViewById(R.id.dshdn);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
        dshdn.setLayoutManager(new LinearLayoutManager(this));
        hoaDonNhapAdapter=new HoaDonNhapAdapter(DS_HDN(),this);
        dshdn.setAdapter(hoaDonNhapAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLHoaDonNhap.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")){
                    startActivity(new Intent(MainActivityQLHoaDonNhap.this, MainActivityThemHDN.class));
                }else {
                    Toast.makeText(MainActivityQLHoaDonNhap.this, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonNhapAdapter=new HoaDonNhapAdapter(DS_HDN(),MainActivityQLHoaDonNhap.this);
                dshdn.setAdapter(hoaDonNhapAdapter);

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
                    hoaDonNhapAdapter=new HoaDonNhapAdapter(DS_HDN(),MainActivityQLHoaDonNhap.this);
                    dshdn.setAdapter(hoaDonNhapAdapter);
                }else {
                    hoaDonNhapAdapter=new HoaDonNhapAdapter(timkiem_mhd(timkiem.getText().toString().trim()),MainActivityQLHoaDonNhap.this);
                    dshdn.setAdapter(hoaDonNhapAdapter);
                }
            }
        });
    }
}