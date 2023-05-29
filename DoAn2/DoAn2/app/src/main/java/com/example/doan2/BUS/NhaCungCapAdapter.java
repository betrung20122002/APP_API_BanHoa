package com.example.doan2.BUS;

import static com.example.doan2.Database.NhaCungCapDAO.xoa_NhaCungCap;
import static com.example.doan2.Database.SanPhamDAO.xoa_sp;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLSanPham.MainActivityQLSanPham.MASP;

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

import com.example.doan2.DTO.NhaCungCap;
import com.example.doan2.DTO.SanPham;
import com.example.doan2.GUI.QLNhaCungCap.*;

import static com.example.doan2.GUI.QLNhaCungCap.MainActivityQLNhaCungCap.*;

import com.example.doan2.R;

import java.util.List;

public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.NhaCungCapViewholdle> {
    List<NhaCungCap> lists;
    Context context;

    public NhaCungCapAdapter(List<NhaCungCap> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public NhaCungCapViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ncc, parent, false);
        return new NhaCungCapViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhaCungCapViewholdle holder, int position) {
        NhaCungCap nhaCungCap = lists.get(position);
        holder.textma.setText(nhaCungCap.getMancc());
        holder.textten.setText(nhaCungCap.getTenncc());
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")) {
                    MANCC = nhaCungCap.getMancc();
                    context.startActivity(new Intent(context, MainActivitySuaNCC.class));
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
                    builder.setMessage("Bạn có muốn xóa nhà cung cấp này ko")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (xoa_NhaCungCap(nhaCungCap.getMancc())) {
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

    public class NhaCungCapViewholdle extends RecyclerView.ViewHolder {
        TextView textma, textten;
        ImageView imageViewedit, imageViewDelete;

        public NhaCungCapViewholdle(@NonNull View itemView) {
            super(itemView);
            textma = itemView.findViewById(R.id.textma);
            textten = itemView.findViewById(R.id.textten);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
