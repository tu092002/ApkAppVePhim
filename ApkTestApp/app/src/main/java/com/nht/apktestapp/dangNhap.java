package com.nht.apktestapp;

import android.content.Intent;
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
    public static User currentUser  = null;
    private  String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dangnhap);


        //Ánh xa
        edtUsernameLogin = (EditText) findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = (EditText) findViewById(R.id.edtPasswordLogin);

        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKyPage = (Button) findViewById(R.id.btnDangKyPage);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    username = edtUsernameLogin.getText().toString();
                    password = edtPasswordLogin.getText().toString();
                    if(username.equals("")||password.equals(""))
                        Toast.makeText(dangNhap.this, "Chưa nhập username hoặc password", Toast.LENGTH_SHORT).show();
                    else {
                        if (MainActivity.database.checkLogin(username, password) == true) {
                             currentUser = MainActivity.database.GetUserByUsername(username, password);
                            Toast.makeText(dangNhap.this, "Chào mừng " + dangNhap.currentUser.getHoTen() + " quay trở lại!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(dangNhap.this, MainActivity.class));

                        } else Toast.makeText(dangNhap.this, "Username hoặc Password sai!!!", Toast.LENGTH_SHORT).show();
                    }
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