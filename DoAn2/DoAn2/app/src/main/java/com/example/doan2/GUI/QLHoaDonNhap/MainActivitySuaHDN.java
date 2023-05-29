package com.example.doan2.GUI.QLHoaDonNhap;

import static com.example.doan2.Database.HoaDonNhapDAO.Sua_HoaDonNhap;
import static com.example.doan2.Database.HoaDonNhapDAO.TT_HoaDonNhap;
import static com.example.doan2.Database.HoaDonNhapDAO.Them_HDN;
import static com.example.doan2.Database.SanPhamDAO.DS_SanPham;
import static com.example.doan2.GUI.QLHoaDonNhap.MainActivityQLHoaDonNhap.MAHDN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan2.DTO.HoaDonNhap;
import com.example.doan2.DTO.SanPham;
import com.example.doan2.R;

public class MainActivitySuaHDN extends AppCompatActivity {
    private EditText editTextmhdn,editTexttsp,editTextsl,editTextdg,editTexttt;
    private Button btntsua;
    private ImageView ImageView_OnBack;
    private Spinner spinner;
    private SanPham sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_hdn);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        editTextmhdn=findViewById(R.id.editTextmhdn);
        editTexttsp=findViewById(R.id.editTexttsp);
        editTextsl=findViewById(R.id.editTextsl);
        editTextdg=findViewById(R.id.editTextdg);
        editTexttt=findViewById(R.id.editTexttt);
        btntsua=findViewById(R.id.btntsua);
        spinner=findViewById(R.id.spinner);
        editTexttsp.setEnabled(false);
        editTexttt.setEnabled(false);
        editTextmhdn.setEnabled(false);

        HoaDonNhap hoaDonNhap=TT_HoaDonNhap(MAHDN);
        editTextmhdn.setText(hoaDonNhap.getMahdn());
        editTexttsp.setText(hoaDonNhap.getTensp());
        editTextsl.setText(hoaDonNhap.getSoluong()+"");
        editTextdg.setText(hoaDonNhap.getDongia()+"");
        editTexttt.setText(hoaDonNhap.getTongtien()+"");


        ArrayAdapter arrayChucVu = new ArrayAdapter(MainActivitySuaHDN.this, R.layout.dropdown_item, DS_SanPham());
        spinner.setAdapter(arrayChucVu);
        int giatri = -1;
        for (int i = 0; i < DS_SanPham().size(); i++) {
            if (DS_SanPham().get(i).toString().equalsIgnoreCase(hoaDonNhap.getMasp())) {
                giatri = i;
                break;
            }
        }
        spinner.setSelection(giatri);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sanPham = (SanPham) arrayChucVu.getItem(position);
                editTexttsp.setText(sanPham.getTensp());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextsl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editTextdg.getText().toString().isEmpty()&&!editTextsl.getText().toString().isEmpty()){

                    int sl=Integer.parseInt(editTextsl.getText().toString());
                    int dg=Integer.parseInt(editTextdg.getText().toString());
                    editTexttt.setText(sl*dg+"");
                }else {
                    editTexttt.setText(0+"");
                }
            }
        });

        editTextdg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editTextdg.getText().toString().isEmpty()&&!editTextsl.getText().toString().isEmpty()){
                    int sl=Integer.parseInt(editTextsl.getText().toString());
                    int dg=Integer.parseInt(editTextdg.getText().toString());
                    editTexttt.setText(sl*dg+"");
                }else {
                    editTexttt.setText(0+"");
                }
            }
        });

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaHDN.this, MainActivityQLHoaDonNhap.class));
                finish();
            }
        });

        btntsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mhdn=editTextmhdn.getText().toString().trim();
                String tensp=editTexttsp.getText().toString().trim();
                String sl=editTextsl.getText().toString().trim();
                String dg=editTextdg.getText().toString().trim();
                String tt=editTexttt.getText().toString().trim();
                sanPham = (SanPham) spinner.getSelectedItem();
                String masp=sanPham.getMasp();
                if (mhdn.isEmpty()||tensp.isEmpty()||sl.isEmpty()||dg.isEmpty()||tt.isEmpty()||masp.isEmpty()){
                    Toast.makeText(MainActivitySuaHDN.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    HoaDonNhap HoaDonNhap=new HoaDonNhap(mhdn,masp,tensp,Integer.parseInt(sl),Integer.parseInt(dg),Integer.parseInt(tt));
                    if (Sua_HoaDonNhap(HoaDonNhap)){
                        startActivity(new Intent(MainActivitySuaHDN.this, MainActivityQLHoaDonNhap.class));
                        finish();
                        Toast.makeText(MainActivitySuaHDN.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivitySuaHDN.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}