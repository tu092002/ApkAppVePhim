package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.User;
import com.nht.apktestapp.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDao {

    private SQLiteDatabase db;
    private Database dbHelper;
    private Context context;

    public UserDao() {
    }

    public UserDao(Context context) {
        this.context = context;
        dbHelper = new Database(context);// thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


    }
    public int InsertUser(User u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maUser", u.getMaUser()); // tự tăng nên ko thêm
        values.put("hoTen", u.getHoTen());
        values.put("username", u.getUserName());
        values.put("password", u.getPassword());
        values.put("role", u.getRole());
        // thực thi insert

        long kq = db.insert("User", null, values);
        if(kq <= 0)
        {
            return -1;
        }
        return 1;

    }

    public List<User> getAllUserToString() {
        List<User> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
        Cursor c = MainActivity.sqLiteDatabase.query("Phim", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            User p = new User();
            p.setMaUser(c.getInt(0));
            p.setHoTen(c.getString(1));
            p.setUsername(c.getString(2));
            p.setPassword(c.getString(3));
            p.setRole(c.getString(4));


            p.setAvt(c.getBlob(5));
            // Chuyển đối tượng thành chuỗi
            ls.add(p);

            c.moveToNext();

        }
        c.close();

        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên


        return ls;
    }
}
