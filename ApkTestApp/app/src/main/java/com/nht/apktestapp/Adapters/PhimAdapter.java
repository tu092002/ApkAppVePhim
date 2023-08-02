package com.nht.apktestapp.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.R;

import java.util.List;

public class PhimAdapter extends BaseAdapter {


    int layout;
    Context context;
    List<Phim> arrayList;

    public PhimAdapter(Context context, int layout, List<Phim> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    private class ViewHolder {
        TextView tvTenPhimGv;
        TextView tvMoTaGv;
        TextView tvGiaPhimGv;
        ImageView imgPhimGv; // ảnh hiển thị trong grid vỉew
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(layout, null);
            viewHolder  = new ViewHolder();

            //ánh xạ
            viewHolder.tvTenPhimGv = (TextView) convertView.findViewById(R.id.tvTenPhimGv);
            viewHolder.tvMoTaGv = (TextView) convertView.findViewById(R.id.tvMoTaGv);
            viewHolder.tvGiaPhimGv = (TextView) convertView.findViewById(R.id.tvGiaPhimGv);
            viewHolder.imgPhimGv = (ImageView) convertView.findViewById(R.id.imgPhimGv);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder)  convertView.getTag();
        }
        Phim phim = arrayList.get(position);
        //Gán giá trị
        viewHolder.tvTenPhimGv.setText(arrayList.get(position).getTenPhim());
        viewHolder.tvMoTaGv.setText(arrayList.get(position).getMoTa());
        viewHolder.tvGiaPhimGv.setText(Double.toString(arrayList.get(position).getGiaPhim()));
//        viewHolder.imgPhimGv.setImageResource(arrayList.get(position).getImgPhim());

        // chuyển byte[] => bitmap
        byte[] hinhAnh =  phim.getImgPhim();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        viewHolder.imgPhimGv.setImageBitmap(bitmap);
        return convertView;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }



}
