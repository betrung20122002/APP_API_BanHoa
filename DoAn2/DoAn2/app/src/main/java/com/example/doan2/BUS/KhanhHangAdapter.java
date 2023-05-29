package com.example.doan2.BUS;

import static com.example.doan2.Database.KhachHangDAO.xoa_kh;
import static com.example.doan2.Database.NhanVienDAO.xoa_nv;
import static com.example.doan2.GUI.MainActivity.CV_;
import static com.example.doan2.GUI.QLKhanhHang.MainActivityQLKhachHang.*;

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

import com.example.doan2.DTO.KhachHang;
import com.example.doan2.DTO.NhanVien;
import com.example.doan2.GUI.QLKhanhHang.MainActivitySuaKH;
import com.example.doan2.GUI.QLNhanVien.MainActivitySuaNhanvien;
import com.example.doan2.R;

import java.util.List;

public class KhanhHangAdapter extends RecyclerView.Adapter<KhanhHangAdapter.KhanhHangViewholdle> {
    List<KhachHang> lists;
    Context context;

    public KhanhHangAdapter(List<KhachHang> lists, Context context) {
        this.lists = lists;
        this.context = context;
        notifyDataSetChanged();
        ;
    }

    @NonNull
    @Override
    public KhanhHangViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kh, parent, false);
        return new KhanhHangViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhanhHangViewholdle holder, int position) {
        KhachHang khachHang = lists.get(position);
        holder.textmakh.setText(khachHang.getMakh());
        holder.texttenkh.setText(khachHang.getTenkh());
        holder.textdiachi.setText(khachHang.getDiachi());
        holder.textsdt.setText(khachHang.getSdt());
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MAKH = khachHang.getMakh();
                context.startActivity(new Intent(context, MainActivitySuaKH.class));
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Bạn có muốn xóa khách hàng này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_kh(khachHang.getMakh())) {
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

    public class KhanhHangViewholdle extends RecyclerView.ViewHolder {
        TextView textmakh, texttenkh, textdiachi, textsdt;
        ImageView imageViewedit, imageViewDelete;

        public KhanhHangViewholdle(@NonNull View itemView) {
            super(itemView);
            textmakh = itemView.findViewById(R.id.textmakh);
            texttenkh = itemView.findViewById(R.id.texttenkh);
            textdiachi = itemView.findViewById(R.id.textdiachi);
            textsdt = itemView.findViewById(R.id.textsdt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
