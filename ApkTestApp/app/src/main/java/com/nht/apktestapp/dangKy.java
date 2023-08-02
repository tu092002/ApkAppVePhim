package com.nht.apktestapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Dao.UserDao;
import com.nht.apktestapp.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class dangKy extends AppCompatActivity {
    Button btnDangKy;
    EditText edtHoTen, edtUsername, edtPassword, edtRole;
    ImageView ibtnUpAvt, imgAvt;
    UserDao userDao;
    Context context;
    private ActivityResultLauncher<String> imagePickerLauncherDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dangky);

        // Ánh xak
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtRole = (EditText) findViewById(R.id.edtRole);
        imgAvt = (ImageView) findViewById(R.id.imgAvt);
        ibtnUpAvt = (ImageView) findViewById(R.id.ibtnUpAvt);
        String online = "true";

        // khai báo một số biến dùng chung
        context = this;
        userDao = new UserDao(); // tạo csdl , và bảng

        imagePickerLauncherDangKy = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                // Xử lý hình ảnh đã chọn từ thư viện ở đây

                // 'uri' chứa đường dẫn đến hình ảnh đã chọn
                // Bạn có thể gọi phương thức uploadImageToServer(uri) để upload hình ảnh lên server.
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgAvt.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        //btn Upfile
        ibtnUpAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                imagePickerLauncherDangKy.launch("image/*"); // Điều này sẽ mở thư viện hình ảnh để chọn hình.
            }
        });
        // button đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String role = edtRole.getText().toString();
                if (TextUtils.isEmpty(hoTen) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(dangKy.this, "Bạn chưa nhập đủ thông tin  !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.database.isUsernameExists(username) == true) {
                    Toast.makeText(dangKy.this, "Username đã có người sử dụng, Vui lòng chọn Username khác !", Toast.LENGTH_SHORT).show();
                    return;
                }

                User u = new User();
                //Chuyển  data imageView =>  byte[] bỏ vô model class
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAvt.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                u.setMaUser(0);
                u.setHoTen(edtHoTen.getText().toString());
                u.setUsername(edtUsername.getText().toString());
                u.setPassword(edtPassword.getText().toString());
                u.setRole(edtRole.getText().toString());
                u.setAvt(hinhAnh);
                u.setOnline(online);

                try {

                    MainActivity.database.InsertUser(u);
                    Toast.makeText(dangKy.this, "ĐĂNG KÍ THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(dangKy.this, "ĐĂNG KÍ THẤT BẠI", Toast.LENGTH_SHORT).show();

                }


                Intent i = new Intent(dangKy.this, dangNhap.class);
                Bundle b = new Bundle();


//                b.putString("Key1", "Lập trinh cùng Tú");
                b.putString("USERNAME", edtUsername.getText().toString());
                b.putString("PASSWORD", edtPassword.getText().toString());

                i.putExtras(b);
                startActivity(i);


            }
        });


    }
}