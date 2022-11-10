package poly.edu.vn.mot_nhieu.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.R;

public class ServiceMuaHangAdapter extends RecyclerView.Adapter<ServiceMuahangViewHolder> {
    ArrayList<DTO_service> list =new ArrayList<>();
    Context context;

    public ServiceMuaHangAdapter(ArrayList<DTO_service> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceMuahangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_muahang,parent,false);
        return new ServiceMuahangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceMuahangViewHolder holder, int position) {
        DTO_service obj = list.get(position);
        holder.tvTenDichVu.setText(obj.getNAME());
        holder.tvGia.setText(obj.getPRICE()+"đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
