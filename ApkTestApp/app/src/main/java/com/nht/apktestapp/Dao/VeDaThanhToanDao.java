package com.nht.apktestapp.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Database;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.VeDaThanhToanByRap;

import java.util.ArrayList;
import java.util.List;

public class VeDaThanhToanDao {
    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public VeDaThanhToanDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
//        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database
    }

    public VeDaThanhToanDao(){}


    public List<VeDaThanhToanByRap> getVeDaThanhToanByRap() {
        List<VeDaThanhToanByRap> list = new ArrayList<>();
//        SQLiteDatabase db = db.getReadableDatabase();
        Cursor cursor = MainActivity.sqLiteDatabase.rawQuery("SELECT Rap.tenRap, COUNT(Ve.MaVe) AS SoVeDaThanhToan FROM Ve INNER JOIN Rap ON Ve.maRap = Rap.MaRap WHERE Ve.ThanhToan = 'false' GROUP BY Rap.tenRap", null);
//        if (cursor.moveToFirst()) {
//            do {
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
//                String tenRap = cursor.getString(cursor.getColumnIndex("Rap.tenRap"));
//                int soVeDaThanhToan = cursor.getInt(cursor.getColumnIndex("SoVeDaThanhToan"));
            VeDaThanhToanByRap ve = new VeDaThanhToanByRap();
            ve.setTenRap(cursor.getString(0));
            ve.setSoVeDaThanhToan(cursor.getInt(1));
            list.add(ve);
//            } while (cursor.moveToNext());
//        }
            cursor.moveToNext();
        }
        cursor.close();
//        db.close();
        return list;
    }
}
