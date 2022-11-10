package poly.edu.vn.mot_nhieu.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DAO.DAO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.R;

public class LichKhamAdapter extends RecyclerView.Adapter<LichKhamViewHolder> {
    ArrayList<DTO_order_detail> list = new ArrayList<>();
    Context context;

    public LichKhamAdapter(ArrayList<DTO_order_detail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LichKhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lichkhamdadat,parent,false);
        return new LichKhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichKhamViewHolder holder, int position) {
        DAO_order_detail dao_order_detail = new DAO_order_detail(context);
        dao_order_detail.open();

        DTO_order_detail obj = list.get(position);
        holder.tvIDLichDat.setText("Mã đơn hàng: "+obj.getORDER_ID());
        LinearLayoutManager manager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        holder.rvLichDaDat.setLayoutManager(manager);
        ArrayList<DTO_service> list1 = dao_order_detail.listService(obj.getORDER_ID());
        SerivceAdapter adapter = new SerivceAdapter(list1,context);
        holder.rvLichDaDat.setAdapter(adapter);
        float tongtien = 0;
        for(int i=0;i<list1.size();i++){
            DTO_service objService = list1.get(i);
            tongtien += objService.getPRICE();
        }
        holder.tvThanhTien.setText(tongtien+"đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
