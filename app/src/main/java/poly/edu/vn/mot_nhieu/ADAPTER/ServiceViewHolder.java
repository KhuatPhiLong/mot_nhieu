package poly.edu.vn.mot_nhieu.ADAPTER;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.mot_nhieu.R;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTenDichVu,tvDangKi,tvThemVaoGio,tvGia;
    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTenDichVu  = itemView.findViewById(R.id.tvTenDichVu);
        tvDangKi = itemView.findViewById(R.id.tvDangKi);
        tvThemVaoGio = itemView.findViewById(R.id.tvThemVaoGio);
        tvGia = itemView.findViewById(R.id.tvGia);
    }
}
