package com.example.doan2.GUI.QLNhanVien;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2.BUS.NhanVienAdapter;
import com.example.doan2.GUI.MainActivityHome;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityQLNhanVien extends AppCompatActivity {

    private NhanVienAdapter nhanVienAdapter;
    private RecyclerView dsnv;
    private EditText timkiem;
    private ImageButton them,cn;
    public static String MANV="";
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlnhan_vien);
        dsnv=findViewById(R.id.dsnv);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
        dsnv.setLayoutManager(new LinearLayoutManager(this));
        nhanVienAdapter=new NhanVienAdapter(DS_NhanVien(),this);
        dsnv.setAdapter(nhanVienAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLNhanVien.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")){
                    startActivity(new Intent(MainActivityQLNhanVien.this,MainActivityThemNhanVien.class));
                }else {
                    Toast.makeText(MainActivityQLNhanVien.this, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienAdapter=new NhanVienAdapter(DS_NhanVien(),MainActivityQLNhanVien.this);
                dsnv.setAdapter(nhanVienAdapter);

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
                    nhanVienAdapter=new NhanVienAdapter(DS_NhanVien(),MainActivityQLNhanVien.this);
                    dsnv.setAdapter(nhanVienAdapter);
                }else {
                    nhanVienAdapter=new NhanVienAdapter(timkiem_mnv(timkiem.getText().toString().trim()),MainActivityQLNhanVien.this);
                    dsnv.setAdapter(nhanVienAdapter);
                }
            }
        });
    }
}