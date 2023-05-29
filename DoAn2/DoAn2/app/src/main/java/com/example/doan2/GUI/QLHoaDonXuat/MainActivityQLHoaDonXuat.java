package com.example.doan2.GUI.QLHoaDonXuat;

import static com.example.doan2.Database.HoaDonNhapDAO.DS_HDN;
import static com.example.doan2.Database.HoaDonNhapDAO.timkiem_mhd;
import static com.example.doan2.Database.HoaDonXuatDAO.DS_HDX;
import static com.example.doan2.Database.HoaDonXuatDAO.timkiem_mhx;
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
import com.example.doan2.BUS.HoaDonXuatAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLHoaDonNhap.MainActivityQLHoaDonNhap;
import com.example.doan2.GUI.QLHoaDonNhap.MainActivityThemHDN;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityQLHoaDonXuat extends AppCompatActivity {
    private HoaDonXuatAdapter HoaDonXuatAdapter;
    private RecyclerView dshdn;
    private EditText timkiem;
    private ImageButton them,cn;
    public static String MAHDX="";
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlhoa_don_xuat);
        dshdn=findViewById(R.id.dshdn);
        timkiem=findViewById(R.id.timkiem);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
        dshdn.setLayoutManager(new LinearLayoutManager(this));
        HoaDonXuatAdapter=new HoaDonXuatAdapter(DS_HDX(),this);
        dshdn.setAdapter(HoaDonXuatAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLHoaDonXuat.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")){
                    startActivity(new Intent(MainActivityQLHoaDonXuat.this, MainActivityThemHDX.class));
                }else {
                    Toast.makeText(MainActivityQLHoaDonXuat.this, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonXuatAdapter=new HoaDonXuatAdapter(DS_HDX(),MainActivityQLHoaDonXuat.this);
                dshdn.setAdapter(HoaDonXuatAdapter);

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
                    HoaDonXuatAdapter=new HoaDonXuatAdapter(DS_HDX(),MainActivityQLHoaDonXuat.this);
                    dshdn.setAdapter(HoaDonXuatAdapter);
                }else {
                    HoaDonXuatAdapter=new HoaDonXuatAdapter(timkiem_mhx(timkiem.getText().toString().trim()),MainActivityQLHoaDonXuat.this);
                    dshdn.setAdapter(HoaDonXuatAdapter);
                }
            }
        });
    }
}