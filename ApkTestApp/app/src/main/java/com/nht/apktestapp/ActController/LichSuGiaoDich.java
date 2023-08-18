package com.nht.apktestapp.ActController;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Adapters.VeAdapter;
import com.nht.apktestapp.Dao.VeDao;
import com.nht.apktestapp.Model.Ve;
import com.nht.apktestapp.R;

import java.util.ArrayList;
import java.util.List;

public class LichSuGiaoDich extends AppCompatActivity {
    VeAdapter veAdapter;
    VeDao vedao;
    List<Ve> listVeDaGiaoDich = new ArrayList<>();
    ListView lvLichSuVe;
    ImageView imgAvtLichSuGiaoDich;
    TextView tvUsernameLichSu;
    TextView tvHoTenLichSu;
    TextView tvRoleLichSu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        vedao = new VeDao();
        listVeDaGiaoDich = vedao.getListCartByUserVaDaThanhToan(dangNhap.currentUser.getMaUser());
        veAdapter = new VeAdapter(this, R.layout.activity_item_cart, listVeDaGiaoDich);
        lvLichSuVe = (ListView) findViewById(R.id.lvLichSuVe);
        tvUsernameLichSu = (TextView) findViewById(R.id.tvUsernameLichSu);
        tvHoTenLichSu = (TextView) findViewById(R.id.tvHoTenLichSu);
        tvRoleLichSu = (TextView) findViewById(R.id.tvRoleLichSu);
        lvLichSuVe.setAdapter(veAdapter);
        veAdapter.notifyDataSetChanged();


        imgAvtLichSuGiaoDich = (ImageView) findViewById(R.id.imgAvtLichSuGiaoDich);
        byte[] img = dangNhap.currentUser.getAvt();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        imgAvtLichSuGiaoDich.setImageBitmap(bitmap);


        tvUsernameLichSu.setText(dangNhap.currentUser.getUserName());
        tvHoTenLichSu.setText(dangNhap.currentUser.getHoTen());
        tvRoleLichSu.setText(dangNhap.currentUser.getRole());
    }
}