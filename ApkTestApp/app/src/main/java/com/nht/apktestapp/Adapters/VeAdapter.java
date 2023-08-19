package com.nht.apktestapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nht.apktestapp.Dao.GheDao;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Model.Ve;
import com.nht.apktestapp.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class VeAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Ve> listVe;

    public VeAdapter(Context context, int layout, List<Ve> listVe) {
        this.context = context;
        this.layout = layout;
        this.listVe = listVe;
    }

    public int getCount() {
        return listVe.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);


        ImageView imgPhimCartLv = (ImageView)  convertView.findViewById(R.id.imgPhimCartLv);
        TextView tvMaVeCartLv = (TextView) convertView.findViewById(R.id.tvMaVeCartLv);
        TextView tvGiaVeCartLv = (TextView) convertView.findViewById(R.id.tvGiaVeCartLv);
        TextView tvThanhToanCartLv =(TextView) convertView.findViewById(R.id.tvThanhToanCartLv);
        TextView tvTenRapCartLv =(TextView) convertView.findViewById(R.id.tvTenRapCartLv);
        TextView tvTenPhimCartLv =(TextView) convertView.findViewById(R.id.tvTenPhimCartLv);
        TextView tvTenGheCartLv =(TextView) convertView.findViewById(R.id.tvTenGheCartLv);
        TextView tvNgayXemCartLv =(TextView) convertView.findViewById(R.id.tvNgayXemCartLv);
        TextView tvNgayDatCartLv =(TextView) convertView.findViewById(R.id.tvNgayDatCartLv);

        PhimDao phimDao = new PhimDao();
        GheDao gheDao = new GheDao();
        RapDao  rapDao = new RapDao();


        // chuyển byte[] => ảnh giao diện bitmap
        byte[] hinhAnh =  phimDao.getImgPhimById(listVe.get(position).getMaPhim());
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        imgPhimCartLv.setImageBitmap(bitmap);
        // các trường còn lại chuyển ra list VIew cart
        tvMaVeCartLv.setText(Integer.toString(listVe.get(position).getMaVe()));
        tvGiaVeCartLv.setText(Double.toString(listVe.get(position).getGiaVe()));
        tvThanhToanCartLv.setText("Chưa thanh toán");

        if(listVe.get(position).getThanhToan().equals("true")){
            tvThanhToanCartLv.setText("Đã thanh toán");
            tvThanhToanCartLv.setTextColor(R.drawable.baseline_add_24);// xanh


        }

        tvTenPhimCartLv.setText(phimDao.getTenPhimById(listVe.get(position).getMaPhim()));
        tvTenRapCartLv.setText(rapDao.getTenRapById(listVe.get(position).getMaRap()));

        tvTenGheCartLv.setText(gheDao.getTenGheById(listVe.get(position).getMaGhe()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        tvNgayXemCartLv.setText(listVe.get(position).getNgayXem().format(formatter));
        tvNgayDatCartLv.setText(listVe.get(position).getNgayDat().format(formatter));

        return convertView;
    }
}
