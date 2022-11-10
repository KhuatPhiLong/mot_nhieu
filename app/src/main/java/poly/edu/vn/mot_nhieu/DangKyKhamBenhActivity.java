package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import poly.edu.vn.mot_nhieu.DAO.DAO_USER;
import poly.edu.vn.mot_nhieu.DAO.DAO_order;
import poly.edu.vn.mot_nhieu.DAO.DAO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_order;
import poly.edu.vn.mot_nhieu.DTO.DTO_order_detail;
import poly.edu.vn.mot_nhieu.DTO.DTO_user;

public class DangKyKhamBenhActivity extends AppCompatActivity {
    TextView tvUserName, tvService, tvPrice, tvOrderTime;
    Button btnDatLich;
    DAO_USER dao_user;
    DAO_order_detail dao_order_detail;
    DAO_order dao_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_kham_benh);
        init();
        dao_user = new DAO_USER(this);
        dao_user.open();

        dao_order = new DAO_order(this);
        dao_order.open();

        dao_order_detail = new DAO_order_detail(this);
        dao_order_detail.open();

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        tvOrderTime.setText(hour + ":" + minute);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nameService = bundle.getString("nameService");
        float priceService = bundle.getFloat("priceService", 0);
        tvService.setText(nameService);
        tvPrice.setText(priceService + "đ");
        SharedPreferences sharedPreferences = getSharedPreferences("layid", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        DTO_user obj = dao_user.layObj(id);
        tvUserName.setText(obj.getNAME());

        int idService = bundle.getInt("idService",0);

        btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO_order objOrder = new DTO_order();
                objOrder.setUSER_ID(id);
                objOrder.setORDER_TIME(tvOrderTime.getText().toString());
                long resOrder = dao_order.insertRow(objOrder);
                if(resOrder>0){
                    Toast.makeText(DangKyKhamBenhActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DangKyKhamBenhActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                }
                DTO_order obj1 = dao_order.layDTOOrder();

                DTO_order_detail objDetail = new DTO_order_detail();
                objDetail.setORDER_ID(obj1.getID_ORDER());
                objDetail.setSERVICE_ID(idService);
                long resDetail = dao_order_detail.insertRow(objDetail);
                if(resDetail>0){
                    Toast.makeText(DangKyKhamBenhActivity.this, "Đặt lịch khám thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DangKyKhamBenhActivity.this, "Đặt lịch khám thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init() {
        tvUserName = findViewById(R.id.tvUserName);
        tvService = findViewById(R.id.tvService);
        tvPrice = findViewById(R.id.tvPrice);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        btnDatLich = findViewById(R.id.btnDatLich);
    }
}