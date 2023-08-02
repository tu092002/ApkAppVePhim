package com.nht.apktestapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private DrawerLayout drawerLayout;
    public static Database database;
    public static SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Tạo database
        database = new Database(this, "film.sqlite", null, 1);
        sqLiteDatabase = database.getWritableDatabase(); // cái này cho phép ghi dữ liệu database



        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //hien thi list phim
        ListView lvMoive =  findViewById(R.id.lvMovie);
        ArrayList<String> items = new ArrayList<>();
        items.add("Phim1");
        items.add("Phim2");
        items.add("Phim3");
        items.add("Phim4");
        items.add("Phim5");

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvMoive.setAdapter(aa);
//        PhimAdapter adapter;
//        Context context;
//        PhimDao phimDao = new PhimDao();
//        List<Phim> list = new ArrayList<>();
//        // Khởi tạo các biến
//        context = this;
//
//        list.clear();
//        list = phimDao.getAllPhimToString();// nhận mảng phim
//        adapter = new PhimAdapter(context, R.layout.activity_item_phim, list);
//        lvMoive.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.btnAdminPhim ) {
            startActivity(new Intent(this, AdminPhim.class));

        } else if (itemId == R.id.btnAdminRap) {
            startActivity(new Intent(this, AdminRap.class));

        } else if (itemId == R.id.btnDangNhapPage) {
            startActivity(new Intent(this, dangNhap.class));
            // Xử lý logout nếu cần thiết
        }
        else if (itemId == R.id.btnDangKyPage) {
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