package poly.edu.vn.mot_nhieu.ADAPTER;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.mot_nhieu.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTenDichVu,tvXoaGioHang,tvGia;
    public CheckBox chkMuaHang;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTenDichVu  = itemView.findViewById(R.id.tvTenDichVu);
        tvXoaGioHang = itemView.findViewById(R.id.tvXoaGioHang);
        tvGia = itemView.findViewById(R.id.tvGia);
        chkMuaHang = itemView.findViewById(R.id.chkMuaHang);
    }
}
