package com.nht.apktestapp.Admin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteAbortException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nht.apktestapp.Adapters.UserAdapter;
import com.nht.apktestapp.Dao.UserDao;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.User;
import com.nht.apktestapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManagerActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> imagePickerLauncher;
    private long lastClickTime = 0;
    private static final long DOUBLE_CLICK_TIME_DELTA = 300;
    int indexClickGv = 0;

    Context context;
    String role;

    UserDao userDao;

    GridView gvUser;
    List<User> listUser;
    UserAdapter adapter;
    Button btnAddUser;
    Button btnUpdate;
    Button btnDelete;
    Button btnView;

    ImageView ibtnUpImgUser;
    ImageView imgUserView;
    EditText edtId;
    EditText edtName;
    EditText edtUsername;
    EditText edtRole;
    RadioGroup rgRole;
    RadioButton rbtnUser;
    RadioButton rbtnAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        edtName = (EditText) findViewById(R.id.txtName);
        edtUsername = (EditText) findViewById(R.id.txtUsername);
//        edtRole = (EditText) findViewById(R.id.txtUserrole);
        rgRole = (RadioGroup) findViewById(R.id.rgRole);
        rbtnUser = (RadioButton) findViewById(R.id.rbtUser);
        rbtnAdmin = (RadioButton) findViewById(R.id.rbtAdmin);
        ibtnUpImgUser = (ImageView) findViewById(R.id.ibtnUpImgUser);
        imgUserView = (ImageView) findViewById(R.id.imgUserView);

        btnAddUser = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnView = (Button) findViewById(R.id.btnView);

        listUser = new ArrayList<>();
        userDao = new UserDao();
        //Hiển thị user ra view
        gvUser = (GridView) findViewById(R.id.lvUserManager);
        gvUserView();


        //Up Hinh anh
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                // Xử lý hình ảnh đã chọn từ thư viện ở đây

                // 'uri' chứa đường dẫn đến hình ảnh đã chọn
                // Bạn có thể gọi phương thức uploadImageToServer(uri) để upload hình ảnh lên server.
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgUserView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ibtnUpImgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                imagePickerLauncher.launch("image/*"); // Điều này sẽ mở thư viện hình ảnh để chọn hình.
            }
        });

        //Chon item gridview
        gvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                    // Đây là double click
                    byte[] hinhAnhOld = listUser.get(indexClickGv).getAvt();
                    Bitmap bitmapOld = BitmapFactory.decodeByteArray(hinhAnhOld, 0, hinhAnhOld.length);
                    imgUserView.setImageBitmap(bitmapOld);
                    edtName.setText(listUser.get(position).getHoTen().toString());
                    edtUsername.setText(listUser.get(position).getUsername().toString());
                    //
                    role = listUser.get(position).getRole().toString();
                    if (role.equals("user")) {
                        rbtnUser.setChecked(true);
                        rbtnAdmin.setChecked(false);
                    } else if (role.equals("admin")){
                         rbtnUser.setChecked(false);
                        rbtnAdmin.setChecked(true);
                    }
                } else {
                    // Đây là single click
                    onSingleClick();
                }
                lastClickTime = clickTime;
                indexClickGv = position;
            }
        });

        //Button Sua
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển  data imageView =>  byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgUserView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                User p = new User();
                p.setMaUser(listUser.get(indexClickGv).getMaUser());
                p.setHoTen(edtName.getText().toString());
                p.setUsername(edtUsername.getText().toString());
                p.setAvt(hinhAnh);
                if (rbtnUser.isChecked()) role = "user";
                else if (rbtnAdmin.isChecked()) role = "admin";
                p.setRole(role);
                try {
                    userDao.UpdateUserNoPass(p);
                    Toast.makeText(UserManagerActivity.this, " Cập nhật Thành công !", Toast.LENGTH_SHORT).show();
                    gvUserView();
                } catch (Exception e) {
                    Toast.makeText(UserManagerActivity.this, " Cập nhật Thất bại !", Toast.LENGTH_SHORT).show();
                    throw new SQLiteAbortException();
                }

            }
        });

        //Button Xoa
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(context);
//                    dialog.setTitle("XÁC NHẬN");
//                    dialog.setMessage("Bạn có đồng ý xóa không ? ");
//                    dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            try {
                                userDao.DeleteUser(listUser.get(indexClickGv).getMaUser());
                                Toast.makeText(UserManagerActivity.this, " Xóa Thành công !", Toast.LENGTH_SHORT).show();
//                            } catch (Exception e) {
//                                Toast.makeText(UserManagerActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    dialog.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
                } catch (Exception e) {
                    Toast.makeText(UserManagerActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
                gvUserView();
            }
        });

    }

    private void gvUserView(){
        listUser.clear();
        listUser = userDao.getAllUserToString();
        adapter = new UserAdapter(this, R.layout.listview_user_manager, listUser);
        gvUser.setAdapter(adapter);
        gvUser.setAdapter(adapter);
    }
    private void onSingleClick() {
        // Xử lý sự kiện khi nhấn đơn
    }
}