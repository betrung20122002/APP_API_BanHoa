package com.example.doan2.GUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2.Database.Database;
import com.example.doan2.GUI.QLHoaDonNhap.MainActivityQLHoaDonNhap;
import com.example.doan2.GUI.QLHoaDonXuat.MainActivityQLHoaDonXuat;
import com.example.doan2.GUI.QLKhanhHang.MainActivityQLKhachHang;
import com.example.doan2.GUI.QLNhaCungCap.MainActivityQLNhaCungCap;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.R;

public class MainActivityHome extends AppCompatActivity {
    private CardView qlnv, qlsp, qlh, qlkh, qln, qlx;
    private Button dx;
    public static Database database_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        qlnv = findViewById(R.id.qlnv);
        qlsp = findViewById(R.id.qlsp);
        qlh = findViewById(R.id.qlh);
        qlkh = findViewById(R.id.qlkh);
        qln = findViewById(R.id.qln);
        qlx = findViewById(R.id.qlx);
        dx = findViewById(R.id.dx);
        database_ = new Database(MainActivityHome.this);

        qlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLNhanVien.class));
                finish();
            }
        });
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivity.class));
                finish();
            }
        });
        qlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLSanPham.class));
                finish();
            }
        });
        qlh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLNhaCungCap.class));
                finish();
            }
        });
        qlkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLKhachHang.class));
                finish();
            }
        });
        qln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLHoaDonNhap.class));
                finish();
            }
        });
        qlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLHoaDonXuat.class));
                finish();
            }
        });
    }
    private long time;
    private Toast mtoast;

    @Override
    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            mtoast.cancel();
            finish();
            return;
        } else {
            mtoast = Toast.makeText(MainActivityHome.this, "Nhấn 2 lần để đồng ý thoát", Toast.LENGTH_SHORT);
            mtoast.show();
        }
        time = System.currentTimeMillis();
    }
}