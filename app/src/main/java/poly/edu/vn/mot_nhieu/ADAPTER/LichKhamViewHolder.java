package poly.edu.vn.mot_nhieu.ADAPTER;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import poly.edu.vn.mot_nhieu.R;

public class LichKhamViewHolder extends RecyclerView.ViewHolder {
    public TextView tvIDLichDat,tvTenDichVu,tvThanhTien;
    public RecyclerView rvLichDaDat;
    public LichKhamViewHolder(@NonNull View itemView) {
        super(itemView);
        tvIDLichDat = itemView.findViewById(R.id.tvIDLichDat);
        tvTenDichVu = itemView.findViewById(R.id.tvTenDichVu);
        tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
        rvLichDaDat = itemView.findViewById(R.id.rvLichDaDat);
    }
}
