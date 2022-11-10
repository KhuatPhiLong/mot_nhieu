package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.ADAPTER.LichKhamAdapter;
import poly.edu.vn.mot_nhieu.DAO.DAO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_order_detail;

public class DanhSachLichKhamDaDatActivity extends AppCompatActivity {
    RecyclerView rvDonLichKham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lich_kham_da_dat);
        rvDonLichKham = findViewById(R.id.rvDonLichKham);

        DAO_order_detail dao_order_detail = new DAO_order_detail(this);
        dao_order_detail.open();
        SharedPreferences sharedPreferences = getSharedPreferences("layid", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        ArrayList<DTO_order_detail> list = dao_order_detail.selectByID(id);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LichKhamAdapter adapter = new LichKhamAdapter(list, this);
        rvDonLichKham.setLayoutManager(manager);
        rvDonLichKham.setAdapter(adapter);
    }


}