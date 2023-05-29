package com.example.doan2.GUI.QLNhaCungCap;

import static com.example.doan2.Database.NhaCungCapDAO.DS_NhaCungCap;
import static com.example.doan2.Database.NhaCungCapDAO.timkiem_ncc;
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

import com.example.doan2.BUS.NhaCungCapAdapter;
import com.example.doan2.BUS.NhanVienAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityQLNhaCungCap extends AppCompatActivity {
    private NhaCungCapAdapter nhaCungCapAdapter;
    private RecyclerView dsncc;
    private EditText timkiem;
    private ImageButton them,cn;
    public static String MANCC="";
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlnha_cung_cap);
        dsncc=findViewById(R.id.dsncc);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
        dsncc.setLayoutManager(new LinearLayoutManager(this));
        nhaCungCapAdapter=new NhaCungCapAdapter(DS_NhaCungCap(),this);
        dsncc.setAdapter(nhaCungCapAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLNhaCungCap.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")){
                    startActivity(new Intent(MainActivityQLNhaCungCap.this, MainActivityThemNCC.class));
                }else {
                    Toast.makeText(MainActivityQLNhaCungCap.this, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhaCungCapAdapter=new NhaCungCapAdapter(DS_NhaCungCap(),MainActivityQLNhaCungCap.this);
                dsncc.setAdapter(nhaCungCapAdapter);

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
                    nhaCungCapAdapter=new NhaCungCapAdapter(DS_NhaCungCap(),MainActivityQLNhaCungCap.this);
                    dsncc.setAdapter(nhaCungCapAdapter);
                }else {
                    nhaCungCapAdapter=new NhaCungCapAdapter(timkiem_ncc(timkiem.getText().toString().trim()),MainActivityQLNhaCungCap.this);
                    dsncc.setAdapter(nhaCungCapAdapter);
                }
            }
        });
    }
}