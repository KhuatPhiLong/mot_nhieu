package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.ADAPTER.CartAdapter;
import poly.edu.vn.mot_nhieu.ADAPTER.SerivceAdapter;
import poly.edu.vn.mot_nhieu.DAO.DAO_cart;
import poly.edu.vn.mot_nhieu.DTO.DTO_cart;

public class GioHangActivity extends AppCompatActivity {
    RecyclerView rvGioHang;
    TextView tvThongBao,tvThanhTien;
    DAO_cart dao_cart;
    ArrayList<DTO_cart> listCart;
    Button btnDatLichGioHang;
    float tongTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        rvGioHang = findViewById(R.id.rvGioHang);
        tvThongBao = findViewById(R.id.tvThongBao);
        btnDatLichGioHang = findViewById(R.id.btnDatLichGioHang);
        DatLichActivity.listMuaHang.clear();
        tvThongBao.setVisibility(View.GONE);
        dao_cart = new DAO_cart(this);
        dao_cart.open();

        SharedPreferences peSharedPreferences = getSharedPreferences("layid",MODE_PRIVATE);
        int id = peSharedPreferences.getInt("id",0);
        listCart = dao_cart.selectAll(id);

        for(int i=0;i<listCart.size();i++){
            tongTien+=listCart.get(i).getPRICE_CART();
        }
        tvThanhTien.setText(tongTien+"đ");
        if (listCart.size() == 0) {
            tvThongBao.setVisibility(View.VISIBLE);
        }
        else{
            CartAdapter adapter = new CartAdapter(listCart,GioHangActivity.this);
            LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rvGioHang.setLayoutManager(manager);
            rvGioHang.setAdapter(adapter);
        }
        btnDatLichGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),DatLichActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DatLichActivity.listMuaHang.clear();
        CartAdapter adapter = new CartAdapter(listCart,GioHangActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvGioHang.setLayoutManager(manager);
        rvGioHang.setAdapter(adapter);
    }

}