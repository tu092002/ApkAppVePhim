package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class PhimDao {

    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public PhimDao() {
    }

    public PhimDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


    }


    public int InsertPhim(Phim u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maPhim", u.getMaPhim()); // tự tăng nên ko thêm
        values.put("TenPhim", u.getTenPhim());
        values.put("MoTa", u.getMoTa());
        values.put("GiaPhim", u.getGiaPhim());
//        values.put("ImgPhim", u.getImgPhim());

        // thực thi insert

        long kq = MainActivity.sqLiteDatabase.insert("Phim", null, values);
        if (kq <= 0) {
            return -1; // thát bại
        }
        return 1;

    }

    // 2. Hiển thị dữ liệu dạng String
    public List<Phim> getAllPhimToString() {
        List<Phim> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
        Cursor c = MainActivity.sqLiteDatabase.query("Phim", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            Phim p = new Phim();
            p.setMaPhim(c.getInt(0));
            p.setTenPhim(c.getString(1));
            p.setMoTa(c.getString(2));



            p.setImgPhim(c.getBlob(3));

            p.setGiaPhim(c.getDouble(4));

            // Chuyển đối tượng thành chuỗi
            ls.add(p);

            c.moveToNext();

        }
        c.close();

        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên


        return ls;
    }
    // 3. Sửa
    public int  UpdatePhim(Phim u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maPhim", u.getMaPhim()); // tự tăng nên ko thêm
        values.put("TenPhim", u.getTenPhim());
        values.put("MoTa", u.getMoTa());
        values.put("GiaPhim", u.getGiaPhim());
        values.put("ImgPhim", u.getImgPhim());
        // thực thi update

        long kq = MainActivity.sqLiteDatabase.update("Phim",values,"MaPhim=?", new String[]{Integer.toString(u.getMaPhim())} );
        if (kq <= 0) {
            return -1; // UPdate thát bại
        }
        return 1;

    }

    //4. Xóa
    public int DeletePhim(int MaPhim){
        int kq = MainActivity.sqLiteDatabase.delete("Phim", "MaPhim=?",  new String[]{""+ MaPhim + ""});
        if (kq <= 0) {
            return -1; // Xóa thát bại
        }
        return 1;
    }


}
