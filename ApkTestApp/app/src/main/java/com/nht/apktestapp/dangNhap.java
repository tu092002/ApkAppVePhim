package com.nht.apktestapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Model.User;

public class dangNhap extends AppCompatActivity {
    EditText edtUsernameLogin, edtPasswordLogin;
    Button btnDangKyPage;
    Button btnDangNhap;
    String taiKhoanHienTai, matKhauHienTai, hoTenHienTai;
    int maUserHienTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dangnhap);


        //Ánh xa
        edtUsernameLogin = (EditText) findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = (EditText) findViewById(R.id.edtPasswordLogin);

        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKyPage = (Button) findViewById(R.id.btnDangKyPage);


//        Intent i = getIntent(); // sài chung cho 2 cách
////        String value1 = i.getStringExtra("Key1");
////        int value2 = i.getIntExtra("Key2", 0);
////
////
////        tv1.setText(value1);
////        tv2.setText(""+value2);
//
//
//        // cách 2  bằng Bundle
//        Bundle b = i.getExtras();
//        String value1 = b.getString("USERNAME");
//        String value2 = b.getString("PASSWORD");
//        edtUsernameLogin.setText(value1);
//        edtPasswordLogin.setText(value2);

        // btn dang nhap

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtUsernameLogin.getText().toString();
                String password = edtPasswordLogin.getText().toString();
                try{
                        User userLogin =  MainActivity.database.GetUserByUsername(username,password);
                    Toast.makeText(dangNhap.this, "Chào mừng " + userLogin.getHoTen() + " quay trở lại!!!" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(dangNhap.this, MainActivity.class));

                }
                catch (Exception e){
                    Toast.makeText(dangNhap.this, "Đăng nhập Fail", Toast.LENGTH_SHORT).show();

                }


            }
        });
        // btn DangKyPage
        btnDangKyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangNhap.this, dangKy.class));
            }
        });


    }
}