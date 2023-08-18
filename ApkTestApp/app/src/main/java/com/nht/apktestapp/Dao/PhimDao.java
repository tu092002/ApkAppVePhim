package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nht.apktestapp.Database;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Phim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhimDao {

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

    public String getTenPhimById(int id) {
        String tenPhim = "";
        Cursor c = MainActivity.database.GetData("SELECT * FROM Phim Where MaPhim = '" + id + "' LIMIT 1");
        c.moveToFirst();
        tenPhim = c.getString(1);
        c.close();
        return tenPhim;
    }

    public byte[] getImgPhimById(int id) {
        byte[] imgPhim = null;
        Cursor c = MainActivity.database.GetData("SELECT * FROM Phim Where MaPhim = '" + id + "' LIMIT 1 ");
        c.moveToFirst();
        int indexOfImgPhim =  c.getColumnIndex("ImgPhim");
        imgPhim =  c.getBlob(indexOfImgPhim);
        c.close();
        return imgPhim;
    }
    public Double getDiemPhimById(int id) {
        Double diem = null;
        Cursor c = MainActivity.database.GetData("SELECT * FROM Phim Where MaPhim = '" + id + "' LIMIT 1");
        c.moveToFirst();
        int indexOfDiemPhim  = c.getColumnIndex("DiemPhim");
        diem = c.getDouble(indexOfDiemPhim);
        c.close();
        return diem;
    }
    public List<Phim> getListPhimByKeyWord(String keyWord) {
        List<Phim> listPhimBykeyword = new ArrayList<>();
        Cursor c = MainActivity.database.GetData("SELECT * FROM Phim WHERE TenPhim LIKE '%" + keyWord + "%'");
        listPhimBykeyword.clear();
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Phim p = new Phim();
            p.setMaPhim(c.getInt(0));
            p.setTenPhim(c.getString(1));
            p.setMoTa(c.getString(2));
            p.setImgPhim(c.getBlob(3));
            p.setGiaPhim(c.getDouble(4));
            p.setDiemPhim(c.getDouble(5));
            listPhimBykeyword.add(p);
            c.moveToNext();
        }
        c.close();

        return listPhimBykeyword;
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
            p.setDiemPhim(c.getDouble(5));
            // Chuyển đối tượng thành chuỗi
            ls.add(p);
            c.moveToNext();
        }
        c.close();
        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên
        return ls;
    }

    //    public List<String> getImagePhim(){
//        List<String> list = new ArrayList<>();
//        // Tạo con trỏ đọc bảng dữ liệu phim
//        Cursor c = MainActivity.sqLiteDatabase.query("Phim", null, null, null, null, null, null);
//        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
//        // đọc
//        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
//        {
//            Phim p  = new Phim();
//            list.add(p.getImgPhim().toString());
//            c.moveToNext();
//        }
//        c.close();
//        Collections.reverse(list);// phim mới thêm sẽ hiện ở đầu tiên
//        return list;
//    }
    // 3. Sửa
    public int UpdatePhim(Phim u) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maPhim", u.getMaPhim()); // tự tăng nên ko thêm
        values.put("TenPhim", u.getTenPhim());
        values.put("MoTa", u.getMoTa());
        values.put("GiaPhim", u.getGiaPhim());
        values.put("ImgPhim", u.getImgPhim());
        // thực thi update

        long kq = MainActivity.sqLiteDatabase.update("Phim", values, "MaPhim=?", new String[]{Integer.toString(u.getMaPhim())});
        if (kq <= 0) {
            return -1; // UPdate thát bại
        }
        return 1;

    }

    //4. Xóa
    public int DeletePhim(int MaPhim) {
        int kq = MainActivity.sqLiteDatabase.delete("Phim", "MaPhim=?", new String[]{"" + MaPhim + ""});
        if (kq <= 0) {
            return -1; // Xóa thát bại
        }
        return 1;
    }

//Ham lấy list tên phim
    public List<String> getAllTenPhimToString() {
        List<String> ls = new ArrayList<>();// Tao danh sách rỗng
        // Tạo con trỏ đọc bảng dữ liệu phim
        Cursor c = MainActivity.sqLiteDatabase.query("Phim", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            // Chuyển đối tượng thành chuỗi
            String tenPhim = (c.getString(1));
            ls.add(tenPhim);
            c.moveToNext();
        }
        c.close();
        Collections.reverse(ls);// phim mới thêm sẽ hiện ở đầu tiên
        return ls;
    }

    public void UpdateDiemPhim(Double i, int maPhim) {
        Double diem = getDiemPhimById(maPhim);
        diem = (diem + i) / 2;
//        MainActivity.sqLiteDatabase.rawQuery("UPdate Phim Set DiemPhim = ? Where MaPhim = ?", new String[]{Double.toString(diem), Integer.toString(maPhim)});
        MainActivity.database.Querydata("update Phim Set DiemPhim ="+ diem +" Where MaPhim = "+ maPhim);
    }
}