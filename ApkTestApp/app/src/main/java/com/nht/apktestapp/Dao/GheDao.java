package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Database;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Rap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GheDao {
    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public GheDao() {
    }

    public GheDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


    }
    public List<Ghe> getAllGheToString() {
        List<Ghe> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
        Cursor c = MainActivity.sqLiteDatabase.query("Ghe", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            Ghe p = new Ghe();
            p.setMaGhe(c.getInt(0));
            p.setMaRap(c.getInt(1));
            p.setTenGhe(c.getString(2));
            p.setEmpty(c.getString(3));


            // Chuyển đối tượng thành chuỗi
            ls.add(p);

            c.moveToNext();

        }
        c.close();

        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên


        return ls;
    }
    public String getTenGheById(int id){
        String tenGhe = "";
        Cursor c = MainActivity.database.GetData("SELECT * FROM Ghe Where MaGhe = '"+ id +"' LIMIT 1");
        c.moveToFirst();
        tenGhe = c.getString(2);

        return tenGhe;
    }
    public List<Ghe> getGheByRap(Rap rap ){
        List<Ghe> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
//        Cursor c =  MainActivity.database.GetData("SELECT * FROM Ghe  WHERE "+MainActivity.database.TB_Ghe_MaRap +" = "
//                + rap.getMaRap() + "   ");
        Cursor c = MainActivity.sqLiteDatabase.rawQuery("SELECT * FROM Ghe Where ? = MaRap", new String[]{Integer.toString(rap.getMaRap())});

        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            Ghe p = new Ghe();
            p.setMaGhe(c.getInt(0));
            p.setMaRap(c.getInt(1));

            p.setTenGhe(c.getString(2));
            p.setEmpty(c.getString(3));





            // Chuyển đối tượng thành chuỗi
            ls.add(p);

            c.moveToNext();

        }
        c.close();
        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên


        return ls;
    }
    public int InsertGhe(Ghe ghe) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maGhe", ghe.getMaGhe()); // tự tăng nên ko thêm
        values.put("maRap", ghe.getMaRap());
        values.put("empty", ghe.getEmpty());

        // thực thi insert

        long kq = db.insert("Ghe", null, values);
        if(kq <= 0)
        {
            return -1;
        }
        return 1;

    }

}
