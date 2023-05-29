package com.example.doan2.BUS;

import static com.example.doan2.Database.HoaDonNhapDAO.xoa_hdn;
import static com.example.doan2.Database.HoaDonXuatDAO.xoa_hdx;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLHoaDonXuat.MainActivityQLHoaDonXuat.*;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan2.DTO.HoaDonNhap;
import com.example.doan2.DTO.HoaDonXuat;
import com.example.doan2.GUI.QLHoaDonNhap.MainActivitySuaHDN;
import com.example.doan2.GUI.QLHoaDonXuat.MainActivitySuaHDX;
import com.example.doan2.R;

import java.util.List;

public class HoaDonXuatAdapter extends RecyclerView.Adapter<HoaDonXuatAdapter.HoaDonXuatViewholdle>{
    List<HoaDonXuat> lists;
    Context context;

    public HoaDonXuatAdapter(List<HoaDonXuat> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public HoaDonXuatViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hdx, parent, false);
        return new HoaDonXuatViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonXuatViewholdle holder, int position) {
        HoaDonXuat HoaDonXuat = lists.get(position);
        holder.textmahdn.setText(HoaDonXuat.getMahdx());
        holder.textmasp.setText(HoaDonXuat.getMasp());
        holder.texttensp.setText(HoaDonXuat.getTensp());
        holder.textmkh.setText(HoaDonXuat.getMakh());
        holder.textsl.setText(HoaDonXuat.getSoluong()+"");
        holder.textdg.setText(HoaDonXuat.getDongia()+"");
        holder.texttt.setText(HoaDonXuat.getTongtien()+"");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")) {
                    MAHDX = HoaDonXuat.getMahdx();
                    context.startActivity(new Intent(context, MainActivitySuaHDX.class));
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
                    builder.setMessage("Bạn có muốn xóa hóa đơn này ko")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (xoa_hdx(HoaDonXuat.getMahdx())) {
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

    public class HoaDonXuatViewholdle extends RecyclerView.ViewHolder {
        TextView textmahdn, textmasp, texttensp, textsl,textdg,texttt,textmkh;
        ImageView imageViewedit, imageViewDelete;

        public HoaDonXuatViewholdle(@NonNull View itemView) {
            super(itemView);
            textmahdn = itemView.findViewById(R.id.textmahdn);
            textmasp = itemView.findViewById(R.id.textmasp);
            texttensp = itemView.findViewById(R.id.texttensp);
            textsl = itemView.findViewById(R.id.textsl);
            textdg = itemView.findViewById(R.id.textdg);
            textmkh = itemView.findViewById(R.id.textmkh);
            texttt = itemView.findViewById(R.id.texttt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
