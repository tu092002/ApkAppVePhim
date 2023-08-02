package com.nht.apktestapp;

import static java.sql.Types.TIMESTAMP;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.Model.User;

public class Database extends SQLiteOpenHelper {
    public static String TB_User = "User";
    public static String TB_Phim = "Phim";
    public static String TB_Ve = "Ve";
//    public static String TB_LoaiVe = "LoaiVe";
//    public static String TB_ChiTietPhim = "ChiTietPhim";
    public static String TB_Rap = "Rap";
    public static String TB_Ghe = "Ghe";
    public static String TB_XuatChieu = "XuatChieu";
    //Xuast chiếu
    public static String TB_XuatChieu_MaXuatChieu = "MaXuatChieu";
    public static String TB_XuatChieu_MaPhim = "MaPhim";
    public static String TB_XuatChieu_ThoiGian = "ThoiGian";

    // User
    public static String TB_User_MaUser = "MaUser";
    public static String TB_User_HoTen = "HoTen";
    public static String TB_User_UserName = "Username";
    public static String TB_User_Password = "Password";
    public static String TB_User_Role = "Role";
    public static String TB_User_Avt = "Avt";
    public static  String TB_User_Online = "Online";

    //Phim
    public static String TB_Phim_MaPhim = "MaPhim";
    public static String TB_Phim_TenPhim = "TenPhim";
    public static String TB_Phim_MoTa = "MoTa";
    public static String TB_Phim_ImgPhim = "ImgPhim";
    public static String TB_Phim_GiaPhim = "GiaPhim";

    //Ve
    public static String TB_Ve_MaVe = "MaVe";
    public static String TB_Ve_MaPhim = "MaPhim";
    public static String TB_Ve_MaUser = "MaUser";
    public static String TB_Ve_MaRap = "MaRap";

    public static String TB_Ve_MaGhe = "MaGhe";
    public static String TB_Ve_NgayDat = "NgayDat";
    public static String TB_Ve_NgayXem = "NgayXem";
    public static String TB_Ve_GiaVe = "GiaVe";
    public static String TB_Ve_ThanhToan = "ThanhToan";
//    //LoaiVe
//    public static String TB_LoaiVe_MaLoaiVe = "MaLoaiVe";
//    public static String TB_LoaiVe_DonGia = "DonGia";
//    public static String TB_LoaiVe_TenLoaiVe = "TenLoaiVe";
//    //ChiTietPhim
//    public static String TB_ChiTietPhim_MaChiTietPhim = "MaChiTietPhim";
//    public static String TB_ChiTietPhim_MaVe = "MaVe";
//    public static String TB_ChiTietPhim_MaPhim = "MaPhim";
//    public static String TB_ChiTietPhim_XuatChieu = "XuatChieu";
    //Rap
    public static String TB_Rap_MaRap = "MaRap";
    public static String TB_Rap_TenRap = "TenRap";
    public static String TB_Rap_MoTaRap = "MoTaRap";
    public static String TB_Rap_ImgRap = "ImgRap";


    //Ghe
    public static String TB_Ghe_MaGhe = "MaGhe";
    public static String TB_Ghe_TenGhe = "TenGhe";

    public static String TB_Ghe_MaRap = "MaRap";
    public static String TB_Ghe_Empty = "Empty";


    public Database(@Nullable Context context) {
        super(context, "film.sqlite", null, 1);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // quan hệ khóa ngoại
        // ve
        String fk_Ve_User = "ALTER TABLE " + TB_Ve + " ADD CONSTRAINT fk_Ve_User FOREIGN KEY (MaUser) REFERENCES " + TB_User + "(MaUser)";
        String fk_Ve_Phim = "ALTER TABLE " + TB_Ve + " ADD CONSTRAINT fk_Ve_Phim FOREIGN KEY (MaPhim) REFERENCES " + TB_Phim + "(MaPhim)";
        String fk_Ve_Ghe = "ALTER TABLE " + TB_Ve + " ADD CONSTRAINT fk_Ve_Ghe FOREIGN KEY (MaGhe) REFERENCES " + TB_Ghe + "(MaGhe)";

        // ghế của rạp nào
        String fk_Ghe_Rap = "ALTER TABLE " + TB_Ghe  + "ADD CONTRAINT fk_Ghe_Rap  FOREIGN KEY (MaRap) REFERENCES " + TB_Rap  +"(MaRap)";





        String tbXuatChieu = "CREATE TABLE IF NOT EXISTS "+ TB_XuatChieu + "(" + TB_XuatChieu_MaXuatChieu + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_XuatChieu_MaPhim + "INTEGER, "+TB_XuatChieu_ThoiGian+" DATETIME)";

        String tbUser = "CREATE TABLE IF NOT EXISTS " + TB_User + "(" + TB_User_MaUser + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_User_HoTen + " TEXT, " + TB_User_UserName + " TEXT, " + TB_User_Password + " TEXT, "
                + TB_User_Role + " TEXT, " + TB_User_Avt + " BLOB,"+TB_User_Online+" TEXT )";
        String tbPhim = "CREATE TABLE  IF NOT EXISTS " + TB_Phim + " (" + TB_Phim_MaPhim + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Phim_TenPhim + " TEXT, " + TB_Phim_MoTa + " TEXT," + TB_Phim_ImgPhim + " BLOB, "+TB_Phim_GiaPhim+ " DOUBLE )";


        String tbVe = "CREATE TABLE IF NOT EXISTS " + TB_Ve + " (" + TB_Ve_MaVe + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Ve_MaPhim + " INTEGER, " + TB_Ve_MaUser + " INTEGER, " + TB_Ve_MaRap + " INTEGER, " + TB_Ve_MaGhe + " INTEGER, "+TB_Ve_NgayDat +
                " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +TB_Ve_NgayXem+" DATETIME , "+TB_Ve_GiaVe +" DOUBLE, "+TB_Ve_ThanhToan+" TEXT)";

//        String tbLoaiVe = "CREATE TABLE IF NOT EXISTS " + TB_LoaiVe + "(" + TB_LoaiVe_MaLoaiVe + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + TB_LoaiVe_TenLoaiVe + " TEXT, " + TB_LoaiVe_DonGia + " INTEGER)";
//        String tbChiTietPhim = "CREATE TABLE  IF NOT EXISTS " + TB_ChiTietPhim + "(" + TB_ChiTietPhim_MaChiTietPhim + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + TB_ChiTietPhim_MaRap + " INTEGER ," + TB_ChiTietPhim_MaPhim + " INTEGER ," + TB_ChiTietPhim_MaVe + " INTEGER, "
//                + TB_ChiTietPhim_XuatChieu + " DATETIME)";
        String tbRap = "CREATE TABLE IF NOT EXISTS " + TB_Rap + "(" + TB_Rap_MaRap + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + TB_Rap_TenRap + " TEXT,  "+TB_Rap_MoTaRap+" TEXT, "+TB_Rap_ImgRap+" BLOB)";
        String tbGhe = "CREATE TABLE IF NOT EXISTS " + TB_Ghe + "(" + TB_Ghe_MaGhe + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_Ghe_MaRap + " INTEGER, " +TB_Ghe_TenGhe +" TEXT, "+ TB_Ghe_Empty + " TEXT)";

        db.execSQL(tbXuatChieu);
        db.execSQL(tbUser);
        db.execSQL(tbPhim);
        db.execSQL(tbVe);
//        db.execSQL(tbLoaiVe);
//        db.execSQL(tbChiTietPhim);
        db.execSQL(tbRap);
        db.execSQL(tbGhe);
//        db.execSQL(fk_Ve_User);
//        db.execSQL(fk_Ve_Phim);
//        db.execSQL(fk_Ve_LoaiVe);
//        db.execSQL(fk_Ve_Ghe);
//        db.execSQL(fk_Ghe_Rap);

    }

    public void Querydata(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }

    //    public Cursor GetData(String sql, String s[]) {
//        SQLiteDatabase db = getReadableDatabase();
//        return db.rawQuery(sql, s);
//    }
    public Cursor GetData(String query) {
        SQLiteDatabase db = getReadableDatabase();

        try {
            // Execute the SQL query and get the result set (Cursor) from the database.
            Cursor cursor = db.rawQuery(query, null);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int InsertPhimToDb(Phim p) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String sql = "INSERT INTO Phim VALUES(null, ?, ?, ?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1, p.getTenPhim());
            statement.bindString(2, p.getMoTa());
            statement.bindBlob(3, p.getImgPhim());
            statement.bindDouble(4, p.getGiaPhim());

            statement.executeInsert();
            return 1; // thành công
        } catch (Exception ex) {
            return -1;// fail
        }

    }
    public int InsertRapToDb(Rap p) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String sql = "INSERT INTO Rap VALUES(null, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1, p.getTenRap());
            statement.bindString(2, p.getMoTaRap());
            statement.bindBlob(3, p.getImgRap());

            statement.executeInsert();
            return 1; // thành công
        } catch (Exception ex) {
            return -1;// fail
        }

    }
    public int InsertUser(User p) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String sql = "INSERT INTO User VALUES(null, ?, ?, ?, ?, ?, ?)"  ;
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1, p.getHoTen());
            statement.bindString(2, p.getUserName());
            statement.bindString(3, p.getPassword());
            statement.bindString(4, p.getRole());
            statement.bindBlob(5, p.getAvt());
            statement.bindString(6, p.getOnline());

            statement.executeInsert();
            return 1; // thành công
        } catch (Exception ex) {
            return -1;// fail
        }

    }
    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = getReadableDatabase();

        // Cột(s) bạn muốn lấy dữ liệu
        String[] projection = {
                "username"
        };

        // Điều kiện WHERE để tìm kiếm username cụ thể
        String selection = "username = ?";
        String[] selectionArgs = { username };

        // Thực hiện truy vấn SELECT
        Cursor cursor = db.query(
                "User", // Tên bảng
                projection, // Các cột muốn lấy dữ liệu
                selection, // Điều kiện WHERE
                selectionArgs, // Giá trị của điều kiện WHERE
                null,
                null,
                null
        );

        // Kiểm tra xem có dữ liệu trả về hay không
        boolean usernameExists = cursor != null && cursor.getCount() > 0;

        // Đóng cursor sau khi kiểm tra
        if (cursor != null) {
            cursor.close();
        }

        return usernameExists;
    }

    public User GetUserByUsername(String username, String password) {
        SQLiteDatabase database = getReadableDatabase();
        User user = null;

        String selectQuery = "SELECT * FROM " + TB_User + " WHERE " + TB_User_UserName + " = " + username + " AND "
                + TB_User_Password + " = " + password;
        Cursor cursor = MainActivity.database.GetData(selectQuery);

        if (cursor != null && cursor.moveToFirst()) {

            // lấy index
            int maUserIndex = cursor.getColumnIndex(TB_User_MaUser);
            int hoTenIndex = cursor.getColumnIndex(TB_User_HoTen);

            int usernameIndex = cursor.getColumnIndex(TB_User_UserName);
            int passwordIndex = cursor.getColumnIndex(TB_User_Password);
            int roleIndex = cursor.getColumnIndex(TB_User_Role);
            int imgAvtIndex = cursor.getColumnIndex(TB_User_Avt);
            int OnlineIndex = cursor.getColumnIndex(TB_User_Online);

            // Thêm các cột thông tin người dùng khác nếu có
            int maUserFromDB = cursor.getInt(maUserIndex);
            String hoTenFromDB = cursor.getString(hoTenIndex);

            String usernameFromDB = cursor.getString(usernameIndex);
            String passwordFromDB = cursor.getString(passwordIndex);
            String roleFromDB = cursor.getString(roleIndex);
            byte[] imgAvtFromDB = cursor.getBlob(imgAvtIndex);
            String OnlineFromDB = cursor.getString(OnlineIndex);

            // Lấy thông tin người dùng khác nếu có

            user = new User(maUserFromDB, hoTenFromDB, usernameFromDB, passwordFromDB, roleFromDB, imgAvtFromDB,OnlineFromDB);
            // Tạo đối tượng User từ thông tin lấy được
        }

        if (cursor != null) {
            cursor.close();
        }

        return user;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // chỉ dùng khi ta tạo db bằng contructor(context) khi đó
        // cả hàm oncreate cùng phải sẽ đc ngầm gọi mà ta ko cần gọi ra
        db.execSQL("DROP TABLE IF EXISTS " + TB_User + "");
        db.execSQL("DROP TABLE IF EXISTS " + TB_Phim + "");
        db.execSQL("DROP TABLE IF EXISTS " + TB_Ve + "");
//        db.execSQL("DROP TABLE IF EXISTS " + TB_LoaiVe + "");
        db.execSQL("DROP TABLE IF EXISTS " + TB_Rap + "");
        db.execSQL("DROP TABLE IF EXISTS " + TB_Ghe + "");
//        db.execSQL("DROP TABLE IF EXISTS " + TB_ChiTietPhim + "");
    }
}
