package com.example.doan2.GUI.QLSanPham;

import static com.example.doan2.Database.SanPhamDAO.Sua_SanPham;
import static com.example.doan2.Database.SanPhamDAO.TT_SanPham;
import static com.example.doan2.Database.SanPhamDAO.Them_SanPham;
import static com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham.MASP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doan2.DTO.SanPham;
import com.example.doan2.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

public class MainActivitySuaSanPham extends AppCompatActivity {
    private ImageView img, ImageView_OnBack;
    private EditText editTextmsp, editTextttsp, editTextchm, editTextsl, editTextdg;
    private Button btnsua;
    private Uri img_uri;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_san_pham);
        img = findViewById(R.id.img);
        editTextmsp = findViewById(R.id.editTextmsp);
        editTextttsp = findViewById(R.id.editTextttsp);
        editTextchm = findViewById(R.id.editTextchm);
        editTextsl = findViewById(R.id.editTextsl);
        editTextdg = findViewById(R.id.editTextdg);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnsua = findViewById(R.id.btnsua);
        editTextmsp.setEnabled(false);

        SanPham sanPham=TT_SanPham(MASP);
        editTextmsp.setText(sanPham.getMasp());
        editTextttsp.setText(sanPham.getTensp());
        editTextchm.setText(sanPham.getCauhinh());
        editTextsl.setText(sanPham.getSoluong()+"");
        editTextdg.setText(sanPham.getDongia()+"");
        Bitmap bitmap_ = BitmapFactory.decodeByteArray(sanPham.getHinhanh(), 0, sanPham.getHinhanh().length);
        img.setImageBitmap(bitmap_);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera(v);
            }
        });
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaSanPham.this,MainActivityQLSanPham.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển đổi từ drawable về bit map
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                bitmap = bitmapDrawable.getBitmap();
                //chuyển đổi từ bitmap về byte
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();


                String ma = editTextmsp.getText().toString().trim();
                String ten = editTextttsp.getText().toString().trim();
                String chm = editTextchm.getText().toString().trim();
                String sl = editTextsl.getText().toString().trim();
                String gb = editTextdg.getText().toString().trim();

                if (ma.isEmpty() || ten.isEmpty() || chm.isEmpty() || sl.isEmpty() || gb.isEmpty()) {
                    Toast.makeText(MainActivitySuaSanPham.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    SanPham sanPham = new SanPham(ma, ten, chm, Integer.parseInt(sl),  Integer.parseInt(gb), hinhanh);
                    if (Sua_SanPham(sanPham)) {
                        startActivity(new Intent(MainActivitySuaSanPham.this,MainActivityQLSanPham.class));
                        finish();
                        Toast.makeText(MainActivitySuaSanPham.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivitySuaSanPham.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            img_uri = data.getData();
            img.setImageURI(img_uri);
        }

    }
    public void Camera(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivitySuaSanPham.this, view);
        popupMenu.inflate(R.menu.menu_camera);
        popupMenu.setGravity(Gravity.CENTER);
        popupMenu.setForceShowIcon(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //dùng thư viên   'com.github.dhaval2404:imagepicker:2.1'
                    case R.id.menu_camera:
                        ImagePicker.with(MainActivitySuaSanPham.this)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .cameraOnly()
                                .start();
                        return true;
                    case R.id.menu_chon_anh:
                        ImagePicker.with(MainActivitySuaSanPham.this)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .galleryOnly()
                                .start();
                        return true;

                }
                return false;
            }
        });
        popupMenu.show();
    }
}