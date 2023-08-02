package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Model.Ve;
import com.nht.apktestapp.Database;

public class VeDao {


    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public VeDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


    }

    public VeDao() {
    }

    public int InsertVe(Ve ve) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maVe", u.getMaVe()); // tự tăng nên ko thêm
        values.put("maUser", ve.getMaUser());
        values.put("maGhe", ve.getMaGhe());
        values.put("ngayDat", ve.getNgayDat());
        values.put("ngayXem", ve.getNgayXem());

        // thực thi insert

        long kq = db.insert("Ve", null, values);
        if(kq <= 0)
        {
            return -1;
        }
        return 1;

    }
}

