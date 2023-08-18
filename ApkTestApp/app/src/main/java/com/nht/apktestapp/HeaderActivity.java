package com.nht.apktestapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeaderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        ImageView imgAvtHeader = (ImageView) findViewById(R.id.imgAvtHeader);
        TextView tvUserNameHeader = (TextView) findViewById(R.id.tvUserNameHeader);
        TextView tvHoTenHeader = (TextView) findViewById(R.id.tvHoTenHeader);
        if (dangNhap.currentUser != null){
            // 2 cach lấy curent user, vì đã đặt public static User và cả 1 cái username bên dangNhap
            tvUserNameHeader.setText(dangNhap.currentUser.getHoTen());
            tvHoTenHeader.setText(dangNhap.currentUser.getUserName());


            byte[] imgByteAvt = dangNhap.currentUser.getAvt(); // Lấy mảng byte của hình ảnh từ đối tượng phim
            // Tạo đối tượng Bitmap từ mảng byte
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgByteAvt, 0, imgByteAvt.length);
            // Gắn hình ảnh vào ImageView
            // Thay thế R.id.imageView bằng ID của ImageView của bạn
            imgAvtHeader.setImageBitmap(bitmap);
        }

    }
}