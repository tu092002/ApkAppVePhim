package com.nht.apktestapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.User;
import com.nht.apktestapp.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {

    int layout;
    Context context;
    List<User> arrayList;

    public UserAdapter(Context context, int layout, List<User> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
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
        View row = inflater.inflate(R.layout.listview_user_manager, null);

        TextView userId = (TextView) row.findViewById(R.id.txtUserId);
        TextView name = (TextView) row.findViewById(R.id.txtName);
        TextView username = (TextView) row.findViewById(R.id.txtUsername);
        TextView userrole = (TextView) row.findViewById(R.id.txtUserrole);
        ImageView imgUser = (ImageView) row.findViewById(R.id.imgUser);


        User user = arrayList.get(position);
        userId.setText(user.getMaUser() + "");
        name.setText(user.getHoTen());
        username.setText(user.getUsername());
        userrole.setText(user.getRole());

        Bitmap bmImgUser = BitmapFactory.decodeByteArray(user.getAvt(), 0, user.getAvt().length);
        imgUser.setImageBitmap(bmImgUser);

        return row;
    }

}
