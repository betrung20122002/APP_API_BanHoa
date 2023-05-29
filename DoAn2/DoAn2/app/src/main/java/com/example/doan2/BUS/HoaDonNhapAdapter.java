package com.example.doan2.BUS;

import static com.example.doan2.Database.HoaDonNhapDAO.xoa_hdn;
import static com.example.doan2.Database.NhanVienDAO.xoa_nv;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLHoaDonNhap.MainActivityQLHoaDonNhap.*;

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
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLHoaDonNhap.MainActivitySuaHDN;
import com.example.doan2.GUI.QLNhanVien.MainActivitySuaNhanvien;
import com.example.doan2.R;

import java.util.List;

public class HoaDonNhapAdapter extends RecyclerView.Adapter<HoaDonNhapAdapter.HoaDonNhapViewholdle>{
    List<HoaDonNhap> lists;
    Context context;

    public HoaDonNhapAdapter(List<HoaDonNhap> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public HoaDonNhapViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hdn, parent, false);
        return new HoaDonNhapViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonNhapViewholdle holder, int position) {
        HoaDonNhap HoaDonNhap = lists.get(position);
        holder.textmahdn.setText(HoaDonNhap.getMahdn());
        holder.textmasp.setText(HoaDonNhap.getMasp());
        holder.texttensp.setText(HoaDonNhap.getTensp()+"");
        holder.textsl.setText(HoaDonNhap.getSoluong()+"");
        holder.textdg.setText(HoaDonNhap.getDongia()+"");
        holder.texttt.setText(HoaDonNhap.getTongtien()+"");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CV_.equals("Quản lý")) {
                    MAHDN = HoaDonNhap.getMahdn();
                    context.startActivity(new Intent(context, MainActivitySuaHDN.class));
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
                                    if (xoa_hdn(HoaDonNhap.getMahdn())) {
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

    public class HoaDonNhapViewholdle extends RecyclerView.ViewHolder {
        TextView textmahdn, textmasp, texttensp, textsl,textdg,texttt;
        ImageView imageViewedit, imageViewDelete;

        public HoaDonNhapViewholdle(@NonNull View itemView) {
            super(itemView);
            textmahdn = itemView.findViewById(R.id.textmahdn);
            textmasp = itemView.findViewById(R.id.textmasp);
            texttensp = itemView.findViewById(R.id.texttensp);
            textsl = itemView.findViewById(R.id.textsl);
            textdg = itemView.findViewById(R.id.textdg);
            texttt = itemView.findViewById(R.id.texttt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
