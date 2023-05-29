package com.example.doan2.BUS;

import static com.example.doan2.Database.NhanVienDAO.xoa_nv;
import static com.example.doan2.Database.SanPhamDAO.xoa_sp;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham.*;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan2.DTO.NhanVien;
import com.example.doan2.DTO.SanPham;
import com.example.doan2.GUI.QLNhanVien.MainActivitySuaNhanvien;
import com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham;
import com.example.doan2.GUI.QLSanPham.MainActivitySuaSanPham;
import com.example.doan2.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewholdle>{
    List<SanPham> lists;
    Context context;

    public SanPhamAdapter(List<SanPham> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public SanPhamViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp, parent, false);
        return new SanPhamViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewholdle holder, int position) {
        SanPham sanPham = lists.get(position);
        holder.textma.setText(sanPham.getMasp());
        holder.textten.setText(sanPham.getTensp());
        holder.textchm.setText(sanPham.getCauhinh());
        holder.textsl.setText(sanPham.getSoluong()+"");
        holder.textgb.setText(sanPham.getDongia()+"");
        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHinhanh(), 0, sanPham.getHinhanh().length);
        holder.img.setImageBitmap(bitmap);
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")) {
                    MASP = sanPham.getMasp();
                    context.startActivity(new Intent(context, MainActivitySuaSanPham.class));
                } else {
                    Toast.makeText(context, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                if (CV_.equals("Quản lý")) {
                    builder.setMessage("Bạn có muốn xóa nhân viên này ko")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (xoa_sp(sanPham.getMasp())) {
                                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });

                    builder.create();
                    builder.show();
                } else {
                    Toast.makeText(context, "Bạn không phải là quản lý", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    public class SanPhamViewholdle extends RecyclerView.ViewHolder {
        TextView textma, textten, textchm, textsl,textgb;
        ImageView imageViewedit, imageViewDelete,img;

        public SanPhamViewholdle(@NonNull View itemView) {
            super(itemView);
            textma = itemView.findViewById(R.id.textma);
            textten = itemView.findViewById(R.id.textten);
            textchm = itemView.findViewById(R.id.textchm);
            textsl = itemView.findViewById(R.id.textsl);
            textgb = itemView.findViewById(R.id.textgb);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            img = itemView.findViewById(R.id.img);
        }
    }
}
