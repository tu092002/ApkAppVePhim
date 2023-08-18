package com.nht.apktestapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.R;

import java.util.List;

public class GheAdapter extends BaseAdapter {


    int layout;
    Context context;
    List<Ghe> arrayList;

    public GheAdapter(Context context, int layout, List<Ghe> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    private class ViewHolder {
        TextView tvTenGheGv;
        TextView tvEmptyGv;
        ImageView imgGheGv; // ảnh hiển thị trong grid vỉew
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(layout, null);
            viewHolder  = new ViewHolder();

            //ánh xạ

            viewHolder.imgGheGv = (ImageView) convertView.findViewById(R.id.imgGheGv);
            viewHolder.tvTenGheGv = (TextView) convertView.findViewById(R.id.tvTenGheGv);
            viewHolder.tvEmptyGv = (TextView) convertView.findViewById(R.id.tvEmptyGv);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder)  convertView.getTag();
        }
        Ghe ghe = arrayList.get(position);
        //Gán giá trịn
//        GheDao gheDao = new GheDao();
//        List<Ghe>  listGhe = gheDao.getGheByRap(DatVe.rapShowGhe);
//
//     Cursor c =  MainActivity.database.GetData("SELECT * FROM Ghe  WHERE TenGhe = '" + arrayList.get(position).getTenGhe() + "' LIMIT 1");
//        c.moveToFirst();
        if (ghe.getEmpty().equals("false") ) {
                viewHolder.imgGheGv.setImageResource(R.drawable.ghe_user);
        }


        viewHolder.tvTenGheGv.setText(arrayList.get(position).getTenGhe());
        viewHolder.tvEmptyGv.setText(arrayList.get(position).getEmpty());
//        viewHolder.imgPhimGv.setImageResource(arrayList.get(position).getImgPhim());

        // chuyển byte[] => bitmap
//        byte[] hinhAnh =  Ghe.getImgPhim();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
//        viewHolder.imgGheGv.setImageBitmap(bitmap);
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
