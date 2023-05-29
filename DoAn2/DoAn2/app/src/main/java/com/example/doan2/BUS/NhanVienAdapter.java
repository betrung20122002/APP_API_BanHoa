package com.example.doan2.BUS;

import static com.example.doan2.Database.NhanVienDAO.xoa_nv;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien.MANV;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.doan2.GUI.QLNhanVien.MainActivitySuaNhanvien;
import com.example.doan2.GUI.QLNhanVien.MainActivityThemNhanVien;
import com.example.doan2.R;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanvienViewholdle> {
    List<NhanVien> lists;
    Context context;


    public NhanVienAdapter(List<NhanVien> lists, Context context) {
        this.lists = lists;
        this.context = context;
        notifyDataSetChanged();;
    }

    @NonNull
    @Override
    public NhanvienViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nv, parent, false);
        return new NhanvienViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanvienViewholdle holder, int position) {
        NhanVien nhanVien = lists.get(position);
        holder.textmanv.setText(nhanVien.getManv());
        holder.texttennv.setText(nhanVien.getTennv());
        holder.textdiachi.setText(nhanVien.getDiachi());
        holder.textsdt.setText(nhanVien.getSdt());
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")) {
                    MANV = nhanVien.getManv();
                    context.startActivity(new Intent(context, MainActivitySuaNhanvien.class));
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
                                    if (xoa_nv(nhanVien.getManv())) {
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

    public class NhanvienViewholdle extends RecyclerView.ViewHolder {
        TextView textmanv, texttennv, textdiachi, textsdt;
        ImageView imageViewedit, imageViewDelete;

        public NhanvienViewholdle(@NonNull View itemView) {
            super(itemView);
            textmanv = itemView.findViewById(R.id.textmanv);
            texttennv = itemView.findViewById(R.id.texttennv);
            textdiachi = itemView.findViewById(R.id.textdiachi);
            textsdt = itemView.findViewById(R.id.textsdt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
