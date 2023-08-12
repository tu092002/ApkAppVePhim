package com.nht.apktestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nht.apktestapp.Model.User;

public class HeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        ImageView imgUser = (ImageView) findViewById(R.id.imgAvtMainAct);
        TextView tvUsername = (TextView) findViewById(R.id.tvUserNameHeader);
        TextView tvHoTen = (TextView) findViewById(R.id.tvHoTenHeader);

        User userLogin = MainActivity.database.GetUserByUsername(dangNhap.username, dangNhap.password);
        tvHoTen.setText(userLogin.getHoTen());
        tvUsername.setText(dangNhap.username);
    }
}