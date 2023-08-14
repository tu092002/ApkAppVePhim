package com.nht.apktestapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Model.Phim;

import java.util.ArrayList;
import java.util.List;

public class DetailPhim extends AppCompatActivity {
    TextView tvTenPhimDetail, tvGiaPhimDetail, tvMoTaPhimDetail;
    ImageView imgPhimDetail;
    List<Phim> list = new ArrayList<>();
    PhimDao phimDao;
    public static  int vitriClickPhim;
    Button btnDatVePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phim);

        // ánh xạ
        tvTenPhimDetail = (TextView) findViewById(R.id.tvTenPhimDetail);
        tvGiaPhimDetail = (TextView) findViewById(R.id.tvGiaPhimDetail);
        tvMoTaPhimDetail = (TextView)  findViewById(R.id.tvMotaPhimDetail);
        imgPhimDetail = (ImageView)  findViewById(R.id.imgPhimDetail);
        Intent i = getIntent();
        Bundle  b = i.getExtras();
        phimDao = new PhimDao();

        list.clear();

        list = phimDao.getAllPhimToString();
        vitriClickPhim = Integer.parseInt( b.getString("POSITION"));
        Phim phim = list.get(vitriClickPhim);
        tvTenPhimDetail.setText(phim.getTenPhim());
        tvGiaPhimDetail.setText(Double.toString(phim.getGiaPhim()));
        tvMoTaPhimDetail.setText(phim.getMoTa());

        byte[] imgPhimByte =  phim.getImgPhim();
        Bitmap bitmap  = BitmapFactory.decodeByteArray(imgPhimByte, 0, imgPhimByte.length);
        imgPhimDetail.setImageBitmap(bitmap);

        btnDatVePage = (Button) findViewById(R.id.btnDatVePage);
        btnDatVePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dangNhap.currentUser == null){
                    Toast.makeText(DetailPhim.this, "Bạn cần đăng nhập trước khi đặt vé!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailPhim.this, dangNhap.class));
                }
                else{
                    Intent i  = new Intent(DetailPhim.this, DatVe.class);

                    Bundle b = new Bundle();
                    b.putString("POSITION_DATVE", String.valueOf(vitriClickPhim));
                    i.putExtras(b);
                    Toast.makeText(DetailPhim.this, "Phim " + list.get(vitriClickPhim).getTenPhim(), Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }

            }
        });

    }
}