package com.nht.apktestapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nht.apktestapp.Adapters.PhimAdapter;
import com.nht.apktestapp.Admin.AdminPhim;
import com.nht.apktestapp.Admin.AdminRap;
import com.nht.apktestapp.Admin.UserManagerActivity;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Dao.VeDao;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.Model.Ve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogDismissListener, NavigationView.OnNavigationItemSelectedListener {

    public static Database database;
    public static SQLiteDatabase sqLiteDatabase;

//    TextView tvSearchPhim;
    AutoCompleteTextView actvSearchPhim;

    TextView tvSearchPhim;
    TextView tvBadge;

    PhimDao phimDao;
    PhimAdapter adapter;
    Context context;
    List<Phim> list = new ArrayList<>(); // tạo danh sách rỗng    PhimAdapter adapter;
    GridView gvListPhimMain;
    private ViewFlipper viewFlipper;
    private List<String> imageUrls;
    private DrawerLayout drawerLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // kiểm tra xem user đã đăng nhập chưa để hiển thị menu cho đúng


// Tạo database
        database = new Database(this, "film.sqlite", null, 1);
        sqLiteDatabase = database.getWritableDatabase(); // cái này cho phép ghi dữ liệu database
// có sqLiteDatabase mới dùng hàm đc Giỏ hàng number
        tvBadge = (TextView)  findViewById(R.id.tvBadge);
        badgeNumber();
        // tạo hình avt
//        imgAvtMainAct = (ImageView) findViewById(R.id.imgAvtMainAct);
//        User user = MainActivity.database.GetData("SELECT * FROM User WHERE active = 1")
//        imgAvtMainAct.setImageDrawable();

        context = this;

        phimDao = new PhimDao();


//         Hiển thị dữ liệu
        list.clear();// xóa hết nội dung trong list
        list = phimDao.getAllPhimToString();
        adapter = new PhimAdapter(context, R.layout.activity_item_phim, list);
        gvListPhimMain = (GridView) findViewById(R.id.gvListPhimMain);
        gvListPhimMain.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gvListPhimMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(MainActivity.this, DetailPhim.class);

                Bundle b = new Bundle();
                b.putString("POSITION", String.valueOf(position));
                i.putExtras(b);
                Toast.makeText(MainActivity.this, "Phim " + list.get(position).getTenPhim(), Toast.LENGTH_SHORT).show();
                startActivity(i);

            }

        });
        actvSearchPhim = (AutoCompleteTextView) findViewById(R.id.actvSearchPhim);
        ImageButton ibtnSearchPhim = (ImageButton) findViewById(R.id.ibtnSearchPhim);
        ibtnSearchPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = actvSearchPhim.getText().toString().trim();
//                list = phimDao.getListPhimByKeyWord(keyWord);
                Cursor c = MainActivity.database.GetData("SELECT * FROM Phim WHERE TenPhim LIKE '%" + keyWord + "%'");
                list.clear();
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    Phim p = new Phim();
                    p.setMaPhim(c.getInt(0));
                    p.setTenPhim(c.getString(1));
                    p.setMoTa(c.getString(2));
                    p.setImgPhim(c.getBlob(3));
                    p.setGiaPhim(c.getDouble(4));
                    list.add(p);
                    c.moveToNext();
                }
                c.close();
                adapter.notifyDataSetChanged();


            }
        });

        //Gợi ý tìm kiếm
        List<String> listTenPhim = new ArrayList<>();
        listTenPhim = phimDao.getAllTenPhimToString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, listTenPhim
        );
        actvSearchPhim.setAdapter(adapter);



        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        Menu menu = navigationView.getMenu();

        if (dangNhap.currentUser != null) {

            MenuItem btnDangNhapPage = menu.findItem(R.id.btnDangNhapPage);
            btnDangNhapPage.setTitle("ĐĂNG XUẤT");
            if (!dangNhap.currentUser.getRole().equals("admin")) {
                // cho phép quản trị hay ko ?
                MenuItem btnAdminPhim = menu.findItem(R.id.btnAdminPhim);
                MenuItem btnAdminRap = menu.findItem(R.id.btnAdminRap);
                MenuItem btnThongKe  =  menu.findItem(R.id.btnThongKe);
                MenuItem btnUserManager  =  menu.findItem(R.id.btnUserManager);
                btnThongKe.setVisible(false);
                btnUserManager.setVisible(false);
                btnAdminPhim.setVisible(false);
                btnAdminRap.setVisible(false);
            }
        } else if (dangNhap.currentUser == null) {
            // cho phép quản trị hay ko ?
            MenuItem btnUserManager  =  menu.findItem(R.id.btnUserManager);

            MenuItem btnAdminPhim = menu.findItem(R.id.btnAdminPhim);
            MenuItem btnAdminRap = menu.findItem(R.id.btnAdminRap);
            MenuItem btnThongKe  =  menu.findItem(R.id.btnThongKe);
            btnUserManager.setVisible(false);

            btnThongKe.setVisible(false);
            btnAdminPhim.setVisible(false);
            btnAdminRap.setVisible(false);
        }


        ImageView imgAvtHeader = (ImageView) headerView.findViewById(R.id.imgAvtHeader);
        TextView tvUserNameHeader = (TextView) headerView.findViewById(R.id.tvUserNameHeader);
        TextView tvHoTenHeader = (TextView) headerView.findViewById(R.id.tvHoTenHeader);
        if (dangNhap.currentUser != null) {
            // 2 cach lấy curent user, vì đã đặt public static User và cả 1 cái username bên dangNhap
            tvUserNameHeader.setText(dangNhap.currentUser.getHoTen());
            tvHoTenHeader.setText(dangNhap.currentUser.getUserName());


            byte[] imgByteAvt = dangNhap.currentUser.getAvt(); // Lấy mảng byte của hình ảnh từ đối tượng phim
            // Tạo đối tượng Bitmap từ mảng byte
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgByteAvt, 0, imgByteAvt.length);
            // Gắn hình ảnh vào ImageView
            // Thay thế R.id.imageView bằng ID của ImageView của bạn
            imgAvtHeader.setImageBitmap(bitmap);
        }

        ImageButton ibtnCartDialog = (ImageButton) findViewById(R.id.ibtnCartDialog);
        ibtnCartDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCartDialog();
                badgeNumber();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //Khu vực khai báo, tạo viewFlipper
        list =  phimDao.getAllPhimToString();

        for (int i = 0; i < 5; i++) {
            // Lấy tham chiếu đến ImageView và gắn hình ảnh cho nó
            ImageView imageView = findViewById(getResources().getIdentifier("imgv" + (i + 1), "id", getPackageName()));
//            imageView.setImageResource(list.get(i).getImgPhim());
            byte[] imgByte = list.get(i).getImgPhim(); // Lấy mảng byte của hình ảnh từ đối tượng phim
            // Tạo đối tượng Bitmap từ mảng byte
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            // Gắn hình ảnh vào ImageView
            // Thay thế R.id.imageView bằng ID của ImageView của bạn
            imageView.setImageBitmap(bitmap);
        }

        //Khai báo xử lí sự kiện bottom navigation view
        BottomNavigationView navView = findViewById(R.id.botNaView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.actionHome) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.actionHistory) {
                    Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.actionSearch) {
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.actionMe) {
                    Toast.makeText(MainActivity.this, "me", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;

            }
        });

    }

    public void badgeNumber() {
        if(dangNhap.currentUser != null){
            VeDao veDao = new VeDao();
            List<Ve> listCart = veDao.getListCartByUser(dangNhap.currentUser.getMaUser());
            TextView tvBadge = (TextView) findViewById(R.id.tvBadge);
            tvBadge.setText(Integer.toString(listCart.size()));
            tvBadge.invalidate();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.btnAdminPhim && dangNhap.currentUser.getRole().equals("admin")) {
            startActivity(new Intent(this, AdminPhim.class));

        } else if (itemId == R.id.btnAdminRap && dangNhap.currentUser.getRole().equals("admin")) {
            startActivity(new Intent(this, AdminRap.class));

        } else if (itemId == R.id.btnDangNhapPage && dangNhap.currentUser == null) {
            startActivity(new Intent(this, dangNhap.class));
            // Xử lý logout nếu cần thiết
        } else if (itemId == R.id.btnDangNhapPage && dangNhap.currentUser != null) {

            dangNhap.currentUser = null;
            recreate();
            // Xử lý logout nếu cần thiết
        } else if (itemId == R.id.btnDangKyPage) {
            startActivity(new Intent(this, dangKy.class));
            // Xử lý logout nếu cần thiết
        } else if (itemId == R.id.btnThongKe && dangNhap.currentUser.getRole().equals("admin")) {
            startActivity(new Intent(this, ChartActivity.class));
            // Xử lý logout nếu cần thiết
        }
        else if (itemId == R.id.btnUserManager && dangNhap.currentUser.getRole().equals("admin")) {
            startActivity(new Intent(this, UserManagerActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDialogListRapDismissed(Rap rapShowGhe) {

    }

    @Override
    public void onDialogListCartDismissed() {
        badgeNumber();
    }

    private void showCartDialog() {
        ListCartDialog customDialog = new ListCartDialog(this, this);


        customDialog.show();
    }

    @Override
    public void onDialogListGheDismissed(Ghe gheChon) {

    }

    @Override
    public void onDialogListGheDismissed() {

    }

    @Override
    public void onDialogNgayXemDismissed(LocalDateTime dateTimeNgayXem) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
//    //

}