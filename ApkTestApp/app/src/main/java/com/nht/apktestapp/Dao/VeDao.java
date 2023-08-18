package com.nht.apktestapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nht.apktestapp.Database;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Ve;
import com.nht.apktestapp.dangNhap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    // hàm insert ko dùng đc mà phải insert query
    // trực tiếp mới đc vì kiểu local time=> string

    public int InsertVe(@NonNull Ve ve) {
        ContentValues values = new ContentValues();// Tạo đối tượng thêm dứ liệu
        //đưa dữ liệu vào đói tượng chứa
//        values.put("maVe", u.getMaVe()); // tự tăng nên ko thêm


        // Định dạng ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


        values.put("MaPhim", ve.getMaPhim());
        values.put("MaUser", ve.getMaUser());
        values.put("MaRap", ve.getMaRap());
        values.put("MaGhe", ve.getMaGhe());
        values.put("NgayDat", ve.getNgayDat().format(formatter));
        values.put("NgayXem", ve.getNgayXem().format(formatter));
        values.put("GiaVe", ve.getGiaVe());

        values.put("ThanhToan", ve.getThanhToan());


        // thực thi insert

        long kq = db.insert("Ve", null, values);
        if (kq <= 0) {
            return -1;
        }
        return 1;

    }

    public List<Ve> getListCartByUserVaChuaThanhToan(int userId) {
        List<Ve> listVe = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Cursor c = MainActivity.sqLiteDatabase.rawQuery("SELECT * FROM Ve WHERE MaUser = ? AND ThanhToan = ?", new String[]{Integer.toString(userId), "false"});
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            Ve p = new Ve();
            p.setMaVe(c.getInt(0));
            p.setMaPhim(c.getInt(1));
            p.setMaUser(c.getInt(2));
            p.setMaRap(c.getInt(3));
            p.setMaGhe(c.getInt(4));
            p.setNgayDat(LocalDateTime.parse(c.getString(5), formatter));
            p.setNgayXem(LocalDateTime.parse(c.getString(6), formatter));
            p.setGiaVe(c.getDouble(7));
            p.setThanhToan(c.getString(8));
            listVe.add(p);
            c.moveToNext();
        }

        return listVe;
    }
    public List<Ve> getListCartByUserVaDaThanhToan(int userId) {
        List<Ve> listVe = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Cursor c = MainActivity.sqLiteDatabase.rawQuery("SELECT * FROM Ve WHERE MaUser = ? AND ThanhToan = ?", new String[]{Integer.toString(userId), "true"});
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            Ve p = new Ve();
            p.setMaVe(c.getInt(0));
            p.setMaPhim(c.getInt(1));
            p.setMaUser(c.getInt(2));
            p.setMaRap(c.getInt(3));
            p.setMaGhe(c.getInt(4));
            p.setNgayDat(LocalDateTime.parse(c.getString(5), formatter));
            p.setNgayXem(LocalDateTime.parse(c.getString(6), formatter));
            p.setGiaVe(c.getDouble(7));
            p.setThanhToan(c.getString(8));
            listVe.add(p);
            c.moveToNext();
        }

        return listVe;
    }
    public List<Ve> getListCartByUser(int userId) {
        List<Ve> listVe = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Cursor c = MainActivity.sqLiteDatabase.rawQuery("SELECT * FROM Ve WHERE MaUser = ?", new String[]{Integer.toString(userId)});
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            Ve p = new Ve();
            p.setMaVe(c.getInt(0));
            p.setMaPhim(c.getInt(1));
            p.setMaUser(c.getInt(2));
            p.setMaRap(c.getInt(3));
            p.setMaGhe(c.getInt(4));
            p.setNgayDat(LocalDateTime.parse(c.getString(5), formatter));
            p.setNgayXem(LocalDateTime.parse(c.getString(6), formatter));
            p.setGiaVe(c.getDouble(7));
            p.setThanhToan(c.getString(8));
            listVe.add(p);
            c.moveToNext();
        }

        return listVe;
    }
    public List<Ve> getListCartOrVe() {
        List<Ve> ls = new ArrayList<>();
        // Tạo con trỏ đọc bảng dữ liệu phim
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Cursor c = MainActivity.sqLiteDatabase.query("Ve", null, null, null, null, null, null);
        c.moveToFirst();/// Di chuyển con trỏ về bản ghi đầu tiên
        // đọc
        while (c.isAfterLast() == false) //  trong khi không phải dòng cuối thì vẫn đoọc
        {
            Ve p = new Ve();
            p.setMaVe(c.getInt(0));
            p.setMaPhim(c.getInt(1));
            p.setMaUser(c.getInt(2));
            p.setMaRap(c.getInt(3));
            p.setMaGhe(c.getInt(4));
            p.setNgayDat(LocalDateTime.parse(c.getString(5), formatter));
            p.setNgayXem(LocalDateTime.parse(c.getString(6), formatter));
            p.setGiaVe(c.getDouble(7));
            p.setThanhToan(c.getString(8));


            // Chuyển đối tượng thêm vào mảng List <Ve>
            ls.add(p);

            c.moveToNext();

        }
        c.close();


        return ls;
    }

    public int DeleteVe(int MaVe) {
        int kq = MainActivity.sqLiteDatabase.delete("Ve", "MaVe=?", new String[]{"" + MaVe + ""});
        if (kq <= 0) {
            return -1; // Xóa thát bại
        }
        return 1;
    }

    public int thanhToanVe() {
        if (dangNhap.currentUser != null) {
            MainActivity.database.Querydata("UPDATE Ve SET ThanhToan = 'true' WHERE MaUser = " + dangNhap.currentUser.getMaUser() + " ");
            return 1;
        } else {
            Toast.makeText(context, "Bạn cần đăng nhập để thanh toán", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }


}

