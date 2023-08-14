package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Database;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Rap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class RapDao {

    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public RapDao() {
    }

    public RapDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


    }


    public int InsertRapDao(Rap u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maPhim", u.getMaPhim()); // tự tăng nên ko thêm
        values.put("TenPhim", u.getTenRap());
        values.put("MoTa", u.getMoTaRap());
//        values.put("ImgPhim", u.getImgPhim());

        // thực thi insert

        long kq = MainActivity.sqLiteDatabase.insert("Rap", null, values);
        if (kq <= 0) {
            return -1; // thát bại
        }
        return 1;

    }

    // 2. Hiển thị dữ liệu dạng String
    public List<Rap> getAllRapToString() {
        List<Rap> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
        Cursor c = MainActivity.sqLiteDatabase.query("Rap", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            Rap p = new Rap();
            p.setMaRap(c.getInt(0));
            p.setTenRap(c.getString(1));
            p.setMoTaRap(c.getString(2));


            p.setImgRap(c.getBlob(3));
            // Chuyển đối tượng thành chuỗi
            ls.add(p);

            c.moveToNext();

        }
        c.close();

        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên


        return ls;
    }
    public String getTenRapById(int id){
        String tenRap = "";
        Cursor c = MainActivity.database.GetData("SELECT * FROM Rap Where MaRap = '"+ id +"' LIMIT 1");
        c.moveToFirst();
        tenRap = c.getString(1);

        return tenRap;
    }
    // 3. Sửa
    public  int  UpdateRap(Rap u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maPhim", u.getMaPhim()); // tự tăng nên ko thêm
        values.put("TenRap", u.getTenRap());
        values.put("MoTaRap", u.getMoTaRap());
        values.put("ImgRap", u.getImgRap());
        // thực thi update

        long kq = MainActivity.sqLiteDatabase.update("Rap",values,"MaRap=?", new String[]{Integer.toString(u.getMaRap())} );
        if (kq <= 0) {
            return -1; // UPdate thát bại
        }
        return 1;

    }

    //4. Xóa
    public int DeleteRap(int MaRap){
        int kq = MainActivity.sqLiteDatabase.delete("Rap", "MaRap=?",  new String[]{""+ MaRap + ""});
        if (kq <= 0) {
            return -1; // Xóa thát bại
        }
        return 1;
    }


}
