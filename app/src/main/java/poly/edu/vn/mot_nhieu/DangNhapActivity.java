package poly.edu.vn.mot_nhieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import poly.edu.vn.mot_nhieu.DAO.DAO_USER;
import poly.edu.vn.mot_nhieu.DTO.DTO_user;

public class DangNhapActivity extends AppCompatActivity {
    EditText edUserName,edPassWord;
    Button btnDanhNhap,btnDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        DAO_USER dao_user = new DAO_USER(DangNhapActivity.this);
        dao_user.open();
        init();

        edUserName.setText("long");
        edPassWord.setText("long");
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(DangNhapActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog);
                dialog.setContentView(R.layout.dangki);
                
                EditText edDKUserName = dialog.findViewById(R.id.edDKUserName);
                EditText edDKPassword = dialog.findViewById(R.id.edDKPassWord);
                Button btnDKDangKi = dialog.findViewById(R.id.btnDKDangKi);
                
                btnDKDangKi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        

                        DTO_user obj = new DTO_user();
                        obj.setNAME(edDKUserName.getText().toString());
                        obj.setPASSWORD(edDKPassword.getText().toString());
                        
                        long res = dao_user.insertRow(obj);
                        if(res>0){
                            Toast.makeText(DangNhapActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(DangNhapActivity.this, "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        btnDanhNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = dao_user.check(edUserName.getText().toString(),edPassWord.getText().toString());
                if(check == true){
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(DangNhapActivity.this, "Xem lại username hoặc pass đã sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void init(){
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        btnDanhNhap = findViewById(R.id.btnDanhNhap);
        btnDangKi = findViewById(R.id.btnDangKi);
    }
}