package com.nht.apktestapp.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteAbortException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Adapters.RapAdapter;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.MainActivity;
import com.nht.apktestapp.Adapters.PhimAdapter;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdminRap extends AppCompatActivity {
    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Ngưỡng thời gian giữa hai lần nhấn (300 milliseconds)
    Button btnThemRap, btnXoaRap, btnSuaRap, btnHienThiRap;
    GridView gvListRap;
    EditText edtTenRap, edtMoTaRap;
    ImageView imgRap, ibtnUpImgRap;
    //    ArrayAdapter<Phim> arrayAdapter;
    RapAdapter adapter;
    RapDao rapDao;
    List<Rap> list = new ArrayList<>(); // tạo danh sách rỗng
    Context context;
    int indexClickGv = 0;
    private long lastClickTime = 0;
    private ActivityResultLauncher<String> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_rap);
        // dọn đatabase
//        MainActivity.database.Querydata("Delete * FROM Phim");
        // Ánh xạ
        btnThemRap = findViewById(R.id.btnThemRap);
        btnSuaRap = findViewById(R.id.btnSuaRap);
        btnXoaRap = findViewById(R.id.btnXoaRap);
        btnHienThiRap = findViewById(R.id.btnHienThiRap);
        gvListRap = findViewById(R.id.gvListRap);
        edtTenRap = findViewById(R.id.edtTenRap);
        edtMoTaRap = findViewById(R.id.edtMoTaRap);
        imgRap = findViewById(R.id.imgRap);
        ibtnUpImgRap = findViewById(R.id.ibtnUpImgRap);
        // Khởi tạo các biến
        context = this;

        rapDao = new RapDao();

//         Hiển thị dữ liệu
        list.clear();// xóa hết nội dung trong list
        list = rapDao.getAllRapToString();
        adapter = new RapAdapter(context, R.layout.activity_item_rap, list);

        gvListRap.setAdapter(adapter);
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                // Xử lý hình ảnh đã chọn từ thư viện ở đây

                // 'uri' chứa đường dẫn đến hình ảnh đã chọn
                // Bạn có thể gọi phương thức uploadImageToServer(uri) để upload hình ảnh lên server.
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgRap.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        //btn Upfile
        ibtnUpImgRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                imagePickerLauncher.launch("image/*"); // Điều này sẽ mở thư viện hình ảnh để chọn hình.
            }
        });

        btnThemRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chuyển  data imageView =>  byte[] bỏ vô model class
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgRap.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();

                Rap u = new Rap(0, edtTenRap.getText().toString(), edtMoTaRap.getText().toString(), hinhAnh);
                int kq = MainActivity.database.InsertRapToDb(u);

                if (kq == -1) {
                    Toast.makeText(AdminRap.this, "Thêm Rạp Thất bại !", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(AdminRap.this, "Thêm Rạp Thành công !", Toast.LENGTH_SHORT).show();
                    edtTenRap.setText(null);
                    edtMoTaRap.setText(null);
                    imgRap.setImageResource(R.drawable.imgempty);
                }

                adapter.notifyDataSetChanged();
            }
        });
        btnHienThiRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();// xóa hết nội dung trong list
                list = rapDao.getAllRapToString();// nhận mảng phim


                adapter = new RapAdapter(context, R.layout.activity_item_rap, list);


                gvListRap.setAdapter(adapter);
            }
        });

        gvListRap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                    // Đây là double click
                    byte[] hinhAnhOld = list.get(indexClickGv).getImgRap();
                    Bitmap bitmapOld = BitmapFactory.decodeByteArray(hinhAnhOld, 0, hinhAnhOld.length);
                    imgRap.setImageBitmap(bitmapOld);
                    edtMoTaRap.setText(list.get(position).getMoTaRap().toString());
                } else {
                    // Đây là single click
                    onSingleClick();
                }
                lastClickTime = clickTime;
//                imgPhim.setImageResource(list.get(indexClickGv).getImgPhim());
                // chuyển:  byte[] => hình ảnh(bitmap) (chuyển ảnh của item GV đang click lên ô Edit ở trên tiến hành xem sửa)


                // chuyển :   hình ảnh => byte[] để đưa vào model class
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPhim.getDrawable();
//                Bitmap bitmapNew = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//                bitmapNew.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
//                byte[] hinhAnhNew = byteArray.toByteArray();
//
//                Phim p = new Phim(list.get(position).getMaPhim(),edtTenPhim.getText().toString(), edtMoTa.getText().toString(), hinhAnhNew);

                indexClickGv = position;
            }
        });
        btnSuaRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển  data imageView =>  byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgRap.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                Rap p = new Rap(list.get(indexClickGv).getMaRap(), edtTenRap.getText().toString(), edtMoTaRap.getText().toString(), hinhAnh);
                try {
//                    MainActivity.database.Querydata("UPDATE PHIM SET MaPhim = null, TenPhim = " + p.getTenPhim() + ",MoTa =  " + p.getMoTa() + ",ImgPhim =  " + p.getImgPhim()
//                            + " WHERE MaPhim = " + p.getMaPhim() + ";");
                    rapDao.UpdateRap(p);

                    Toast.makeText(AdminRap.this, " Cập nhật Thành công !", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AdminRap.this, " Cập nhật Thất bại !", Toast.LENGTH_SHORT).show();
                    throw new SQLiteAbortException();
                }

            }
        });


        gvListRap.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // xử lí sự kiện code tại vị trí position

                androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(context);
                dialog.setTitle("XÁC NHẬN");
                dialog.setMessage("Bạn có đồng ý xóa không ? ");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                      int  kq = MainActivity.database.DeletePhimToDb(position);
                        try {
//                            String maPhimView = parent.getItemAtPosition(position).toString();
                            MainActivity.database.Querydata("Delete from Rap where " + list.get(position).getMaRap() + " == MaRap ");

                        } catch (Exception e) {
                            Toast.makeText(AdminRap.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        list.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                });
                dialog.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
//                edtTenPhim.setText(list.get(position).getTenPhim().toString());


                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                return false;
            }
        });

    }

    private void onSingleClick() {
        // Xử lý sự kiện khi nhấn đơn
    }

    private void onDoubleClick() {
        // Xử lý sự kiện khi nhấn đúp
    }
//    @Override
//    protected void d(int requestCode, int resultCode, @Nullable Intent data) {
//        if (data != null) {
//            uri
////            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
////            imgPhim.setImageBitmap(bitmap);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
}