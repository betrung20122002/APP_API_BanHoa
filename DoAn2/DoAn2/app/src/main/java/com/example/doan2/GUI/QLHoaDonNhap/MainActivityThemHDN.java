package com.example.doan2.GUI.QLHoaDonNhap;

import static com.example.doan2.Database.HoaDonNhapDAO.DS_HDN;
import static com.example.doan2.Database.HoaDonNhapDAO.Them_HDN;
import static com.example.doan2.Database.HoaDonNhapDAO.timkiem_mhd;
import static com.example.doan2.Database.NhanVienDAO.Them_NhanVien;
import static com.example.doan2.Database.SanPhamDAO.DS_SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan2.BUS.HoaDonNhapAdapter;
import com.example.doan2.DTO.HoaDonNhap;
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.DTO.SanPham;
import com.example.doan2.GUI.MainActivity;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.R;

public class MainActivityThemHDN extends AppCompatActivity {
    private EditText editTextmhdn,editTexttsp,editTextsl,editTextdg,editTexttt;
    private Button btnthem;
    private ImageView ImageView_OnBack;
    private Spinner spinner;
    private SanPham sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_hdn);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        editTextmhdn=findViewById(R.id.editTextmhdn);
        editTexttsp=findViewById(R.id.editTexttsp);
        editTextsl=findViewById(R.id.editTextsl);
        editTextdg=findViewById(R.id.editTextdg);
        editTexttt=findViewById(R.id.editTexttt);
        btnthem=findViewById(R.id.btnthem);
        spinner=findViewById(R.id.spinner);
        editTexttsp.setEnabled(false);
        editTexttt.setEnabled(false);

        ArrayAdapter arrayChucVu = new ArrayAdapter(MainActivityThemHDN.this, R.layout.dropdown_item, DS_SanPham());
        spinner.setAdapter(arrayChucVu);
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
                startActivity(new Intent(MainActivityThemHDN.this, MainActivityQLHoaDonNhap.class));
                finish();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivityThemHDN.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    HoaDonNhap HoaDonNhap=new HoaDonNhap(mhdn,masp,tensp,Integer.parseInt(sl),Integer.parseInt(dg),Integer.parseInt(tt));
                    if (Them_HDN(HoaDonNhap)){
                        editTextmhdn.setText("");
                        editTextsl.setText("");
                        editTextdg.setText("");
                        editTexttt.setText("");
                        spinner.setSelection(0);
                        Toast.makeText(MainActivityThemHDN.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivityThemHDN.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}