package poly.edu.vn.mot_nhieu.ADAPTER;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.mot_nhieu.R;

public class ServiceMuahangViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTenDichVu,tvGia;
    public CheckBox chkMuaHang;

    public ServiceMuahangViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTenDichVu = itemView.findViewById(R.id.tvTenDichVu);
        tvGia = itemView.findViewById(R.id.tvGia);
        chkMuaHang = itemView.findViewById(R.id.chkMuaHang);
    }
}
