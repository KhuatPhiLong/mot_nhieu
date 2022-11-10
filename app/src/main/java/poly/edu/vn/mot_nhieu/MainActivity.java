package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poly.edu.vn.mot_nhieu.ADAPTER.SerivceAdapter;
import poly.edu.vn.mot_nhieu.DAO.DAO_service;
import poly.edu.vn.mot_nhieu.DTO.DTO_service;

public class MainActivity extends AppCompatActivity {
    TextView tvThemDichVu,tvGioHang,tvDanhSachLichKhamDaDat;
    RecyclerView rvDanhSachDichVu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        DAO_service dao_service = new DAO_service(this);
        dao_service.open();

        ArrayList<DTO_service> list_service = dao_service.selecAll();
        SerivceAdapter adapter = new SerivceAdapter(list_service,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvDanhSachDichVu.setLayoutManager(manager);
        rvDanhSachDichVu.setAdapter(adapter);

        tvGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });

        tvThemDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_them_dichvu);

                EditText edTenDichvu = dialog.findViewById(R.id.edTenDichvu);
                EditText edGiaDichVu = dialog.findViewById(R.id.edGiaDichVu);
                Button btnThem = dialog.findViewById(R.id.btnThem);
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DTO_service obj = new DTO_service();
                        obj.setNAME(edTenDichvu.getText().toString());
                        obj.setPRICE(Float.parseFloat(edGiaDichVu.getText().toString()));
                        
                        long res  = dao_service.insertRow(obj);
                        if(res>0){
                            list_service.clear();
                            list_service.addAll(dao_service.selecAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Thêm dịch vụ khám thành công", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Thêm dịch vụ khám thất bại", Toast.LENGTH_SHORT).show();
                        }
                        
                    }
                });
                dialog.show();
            }
        });

        tvDanhSachLichKhamDaDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),DanhSachLichKhamDaDatActivity.class);
                startActivity(intent);
            }
        });
    }
    public void init(){
        tvThemDichVu = findViewById(R.id.tvThemDichVu);
        rvDanhSachDichVu = findViewById(R.id.rvDanhSachDichVu);
        tvGioHang = findViewById(R.id.tvGioHang);
        tvDanhSachLichKhamDaDat = findViewById(R.id.tvDanhSachLichKhamDaDat);
    }
}