package com.nht.apktestapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nht.apktestapp.Model.Ve;
import com.nht.apktestapp.R;

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



        TextView tvMaVeCartLv = (TextView) convertView.findViewById(R.id.tvMaVeCartLv);
        TextView tvGiaVeCartLv = (TextView) convertView.findViewById(R.id.tvGiaVeCartLv);
        TextView tvThanhToanCartLv =(TextView) convertView.findViewById(R.id.tvThanhToanCartLv);

        tvMaVeCartLv.setText(Integer.toString(listVe.get(position).getMaVe()));
        tvGiaVeCartLv.setText(Double.toString(listVe.get(position).getGiaVe()));
        tvThanhToanCartLv.setText(listVe.get(position).getThanhToan());

        return convertView;
    }
}
