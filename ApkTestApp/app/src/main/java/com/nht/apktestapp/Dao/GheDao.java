package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Database;

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
