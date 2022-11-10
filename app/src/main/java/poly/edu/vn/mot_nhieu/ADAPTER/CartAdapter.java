package poly.edu.vn.mot_nhieu.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.DAO.DAO_cart;
import poly.edu.vn.mot_nhieu.DAO.DAO_service;
import poly.edu.vn.mot_nhieu.DTO.DTO_cart;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.DatLichActivity;
import poly.edu.vn.mot_nhieu.MainActivity;
import poly.edu.vn.mot_nhieu.R;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    ArrayList<DTO_cart> list = new ArrayList<>();
    Context context;
    DAO_cart dao_cart;

    public CartAdapter(ArrayList<DTO_cart> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        DAO_service dao_service = new DAO_service(context);
        dao_service.open();
        DTO_cart obj = list.get(position);
        DTO_service obj1 = dao_service.layService(obj.getID_SERVICE());
        holder.tvTenDichVu.setText(obj1.getNAME());
        holder.tvGia.setText(obj1.getPRICE()+"đ");
        holder.tvXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao_cart = new DAO_cart(context);
                dao_cart.open();
                int res  =dao_cart.deleteRow(obj);
                if(res>0){
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.chkMuaHang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    DatLichActivity.listMuaHang.add(obj);
                }
                else{
                    for(int i=0;i<DatLichActivity.listMuaHang.size();i++){
                        if(DatLichActivity.listMuaHang.get(i).getID_CART() == obj.getID_CART()){
                            DatLichActivity.listMuaHang.remove(DatLichActivity.listMuaHang.get(i));
                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
