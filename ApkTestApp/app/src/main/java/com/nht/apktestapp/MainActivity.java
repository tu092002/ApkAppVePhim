package com.nht.apktestapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.nht.apktestapp.Adapters.PhimAdapter;
import com.nht.apktestapp.Admin.AdminPhim;
import com.nht.apktestapp.Admin.AdminRap;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Model.Phim;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static Database database;
    public static SQLiteDatabase sqLiteDatabase;
    ImageView imgAvtMainAct;
    PhimDao phimDao;
    PhimAdapter adapter;
    Context context;
    List<Phim> list = new ArrayList<>(); // tạo danh sách rỗng    PhimAdapter adapter;
    GridView gvListPhimMain;
    private DrawerLayout drawerLayout;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Tạo database
        database = new Database(this, "film.sqlite", null, 1);
        sqLiteDatabase = database.getWritableDatabase(); // cái này cho phép ghi dữ liệu database


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

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.btnAdminPhim) {
            startActivity(new Intent(this, AdminPhim.class));

        } else if (itemId == R.id.btnAdminRap) {
            startActivity(new Intent(this, AdminRap.class));

        } else if (itemId == R.id.btnDangNhapPage) {
            startActivity(new Intent(this, dangNhap.class));
            // Xử lý logout nếu cần thiết
        } else if (itemId == R.id.btnDangKyPage) {
            startActivity(new Intent(this, dangKy.class));
            // Xử lý logout nếu cần thiết
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
}