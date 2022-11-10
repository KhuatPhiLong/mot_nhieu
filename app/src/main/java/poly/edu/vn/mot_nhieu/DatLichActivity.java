package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import poly.edu.vn.mot_nhieu.ADAPTER.CartAdapter;
import poly.edu.vn.mot_nhieu.ADAPTER.ServiceMuaHangAdapter;
import poly.edu.vn.mot_nhieu.DAO.DAO_USER;
import poly.edu.vn.mot_nhieu.DAO.DAO_cart;
import poly.edu.vn.mot_nhieu.DAO.DAO_order;
import poly.edu.vn.mot_nhieu.DAO.DAO_order_detail;
import poly.edu.vn.mot_nhieu.DAO.DAO_service;
import poly.edu.vn.mot_nhieu.DTO.DTO_cart;
import poly.edu.vn.mot_nhieu.DTO.DTO_order;
import poly.edu.vn.mot_nhieu.DTO.DTO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;
import poly.edu.vn.mot_nhieu.DTO.DTO_user;

public class DatLichActivity extends AppCompatActivity {
    RecyclerView rvServiceDangKy;
    DAO_cart dao_cart;
    DAO_USER dao_user;
    DAO_service dao_service;
    DAO_order dao_order;
    DAO_order_detail dao_order_detail;
    TextView tvUserName, tvOrderTime;
    Button btnDatLich;

    public static ArrayList<DTO_cart> listMuaHang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich);
        rvServiceDangKy = findViewById(R.id.rvServiceDangKy);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        tvUserName = findViewById(R.id.tvUserName);
        btnDatLich = findViewById(R.id.btnDatLich);
        dao_user = new DAO_USER(this);
        dao_user.open();
        dao_cart = new DAO_cart(this);
        dao_cart.open();
        dao_service = new DAO_service(this);
        dao_service.open();
        dao_order = new DAO_order(this);
        dao_order.open();
        dao_order_detail = new DAO_order_detail(this);
        dao_order_detail.open();

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        tvOrderTime.setText(hour + ":" + minute);

        SharedPreferences sharedPreferences = getSharedPreferences("layid", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        DTO_user obj = dao_user.layObj(id);
        tvUserName.setText(obj.getNAME());


        ArrayList<DTO_service> listService = new ArrayList<>();

        ArrayList<DTO_cart> list = DatLichActivity.listMuaHang;
        for (int i = 0; i < list.size(); i++) {
            DTO_cart objCart = list.get(i);
            DTO_service objService = dao_service.layService(objCart.getID_SERVICE());
            listService.add(objService);
        }

        ServiceMuaHangAdapter adapter = new ServiceMuaHangAdapter(listService, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvServiceDangKy.setLayoutManager(manager);
        rvServiceDangKy.setAdapter(adapter);

        btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO_order objOrder = new DTO_order();
                objOrder.setUSER_ID(id);
                objOrder.setORDER_TIME(tvOrderTime.getText().toString());
                long resOrder = dao_order.insertRow(objOrder);

                if (resOrder > 0) {
                    Toast.makeText(DatLichActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DatLichActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                }
                DTO_order obj1 = dao_order.layDTOOrder();

                for (int i = 0; i < listService.size(); i++) {
                    DTO_service objService = listService.get(i);
                    DTO_order obj3 = dao_order.layDTOOrder();
                    DTO_order_detail objDetail = new DTO_order_detail();
                    objDetail.setORDER_ID(obj1.getID_ORDER());
                    objDetail.setSERVICE_ID(objService.getID());
                    long resDetail = dao_order_detail.insertRow(objDetail);
                }

                Toast.makeText(DatLichActivity.this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();

            }
        });

    }
}