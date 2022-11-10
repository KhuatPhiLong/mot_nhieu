package poly.edu.vn.mot_nhieu.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DAO.DAO_cart;
import poly.edu.vn.mot_nhieu.DAO.DAO_service;
import poly.edu.vn.mot_nhieu.DTO.DTO_cart;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.DangKyKhamBenhActivity;
import poly.edu.vn.mot_nhieu.MainActivity;
import poly.edu.vn.mot_nhieu.R;

public class SerivceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {
    ArrayList<DTO_service> list  = new ArrayList<>();
    Context context;

    public SerivceAdapter(ArrayList<DTO_service> list, Context context) {
        this.list = list;
        this.context = context;
    }

    DAO_service dao_service;
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        DTO_service obj = list.get(position);
        holder.tvTenDichVu.setText(obj.getNAME());
        holder.tvGia.setText(obj.getPRICE()+"đ");
        holder.tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DangKyKhamBenhActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameService",obj.getNAME());
                bundle.putFloat("priceService",obj.getPRICE());
                bundle.putInt("idService",obj.getID());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.tvThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO_cart obj1 = new DTO_cart();
                obj1.setID_SERVICE(obj.getID());
                SharedPreferences peSharedPreferences = context.getSharedPreferences("layid",context.MODE_PRIVATE);
                int id = peSharedPreferences.getInt("id",0);
                obj1.setID_USER(id);
                DAO_cart dao_cart = new DAO_cart(context);
                dao_cart.open();
                long res = dao_cart.insertRow(obj1);
                ArrayList<DTO_cart> list1 = dao_cart.selectAll(id);
                if(res>0){
                    list1.clear();
                    list1.addAll(dao_cart.selectAll(id));
                    notifyDataSetChanged();
                    Toast.makeText(context, "Bạn đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
