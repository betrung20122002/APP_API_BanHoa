package com.example.doan2.GUI;

import static com.example.doan2.Database.TaiKhoanDAO.KiemTra_DN;
import static com.example.doan2.Database.TaiKhoanDAO.Them_TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan2.DTO.TaiKhoan;
import com.example.doan2.Database.Database;
import com.example.doan2.R;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    public static String CV_ = "";

    private EditText editTexttdn, editTextmk;
    private ImageView checkoff, checkon;
    private Button buttondangnhap;
    private TextView textViewdangky, textViewqmk;
    private int checkmk = 1;
    private Spinner spinner;
    private String[] chucvu = new String[]{"Nhân viên", "Quản lý"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //khỏi tạo Database
        database = new Database(MainActivity.this);



        if (KiemTra_DN("admin", "admin", "Quản lý")==false) {
            TaiKhoan Tk = new TaiKhoan("admin@gmail.com", "admin", "admin", "Quản lý");
            Them_TaiKhoan(Tk);
        }

        editTexttdn = findViewById(R.id.editTexttdn);
        editTextmk = findViewById(R.id.editTextmk);
        checkoff = findViewById(R.id.checkoff);
        checkon = findViewById(R.id.checkon);
        buttondangnhap = findViewById(R.id.buttondangnhap);
        textViewdangky = findViewById(R.id.textViewdangky);
        textViewqmk = findViewById(R.id.textViewqmk);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter arrayChucVu = new ArrayAdapter(MainActivity.this, R.layout.dropdown_item, chucvu);
        spinner.setAdapter(arrayChucVu);
        spinner.setSelection(0);


        textViewqmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, quenmk.class));

            }
        });
        textViewdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivityDangKy.class));
            }
        });
        checkoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkmk == 1) {
                    editTextmk.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkmk = 0;
                    checkon.setVisibility(View.VISIBLE);
                    checkoff.setVisibility(View.GONE);
                }
            }
        });
        checkon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkmk == 0) {
                    editTextmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkmk = 1;
                    checkon.setVisibility(View.GONE);
                    checkoff.setVisibility(View.VISIBLE);
                }
            }
        });
        buttondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TDN = editTexttdn.getText().toString().trim();
                String MK = editTextmk.getText().toString().trim();
                String CV = (String) spinner.getSelectedItem();
                if (TDN.isEmpty() || MK.isEmpty() || CV.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin tài khoản", Toast.LENGTH_SHORT).show();
                }
                if (KiemTra_DN(TDN, MK, CV)) {
                    CV_=CV;
                    startActivity(new Intent(getApplicationContext(), MainActivityHome.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}