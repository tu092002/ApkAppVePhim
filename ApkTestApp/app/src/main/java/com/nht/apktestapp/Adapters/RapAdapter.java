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

import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.R;

import java.util.List;

public class RapAdapter extends BaseAdapter {


    int layout;
    Context context;
    List<Rap> arrayList;

    public RapAdapter(Context context, int layout, List<Rap> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    private class ViewHolder {
        TextView tvTenRapGv;
        TextView tvMoTaRapGv;
        ImageView imgRapGv; // ảnh hiển thị trong grid vỉew
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(layout, null);
            viewHolder  = new ViewHolder();

            //ánh xạ
            viewHolder.tvTenRapGv = (TextView) convertView.findViewById(R.id.tvTenRapGv);
            viewHolder.tvMoTaRapGv = (TextView) convertView.findViewById(R.id.tvMoTaRapGv);
            viewHolder.imgRapGv = (ImageView) convertView.findViewById(R.id.imgRapGv);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder)  convertView.getTag();
        }
        Rap Rap = arrayList.get(position);
        //Gán giá trị
        viewHolder.tvTenRapGv.setText(arrayList.get(position).getTenRap());
        viewHolder.tvMoTaRapGv.setText(arrayList.get(position).getMoTaRap());
//        viewHolder.imgPhimGv.setImageResource(arrayList.get(position).getImgPhim());

        // chuyển byte[] => bitmap
        byte[] hinhAnhRap =  Rap.getImgRap();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnhRap, 0, hinhAnhRap.length);
        viewHolder.imgRapGv.setImageBitmap(bitmap);
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
