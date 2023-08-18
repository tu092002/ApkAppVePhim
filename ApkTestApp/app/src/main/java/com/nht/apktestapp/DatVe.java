package com.nht.apktestapp;

import static com.nht.apktestapp.ActController.DateTimePickerDialog.dateTimeNgayXem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Adapters.GheAdapter;
import com.nht.apktestapp.Adapters.RapAdapter;
import com.nht.apktestapp.Adapters.VeAdapter;
import com.nht.apktestapp.Dao.GheDao;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Dao.VeDao;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.Model.Ve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatVe extends AppCompatActivity implements OnDialogDismissListener {
    public static Rap rapShowGhe;
    public static LocalDateTime limitedDateTime;
    TextView tvTenPhimDatVe, tvGiaPhimDatVe;
    ImageView imgPhimDatVe, imgRapDatVe;
    List<Phim> list = new ArrayList<>();
    List<Rap> listRap;
    List<Ghe> listGhe;
    List<String> listTenRap = new ArrayList<>();
    PhimDao phimDao;
    RapDao rapDao;
    GheDao gheDao;
    Button btnChonRap, btnDateTimePickerTgXemPage, btnDatVe, btnChonGhe;
    ImageButton ibtnCartDialog;
    TextView tvBadge;
    RapAdapter rapAdapter;
    GheAdapter gheAdapter;
    GridView gvlistRapDatVe;
    GridView gvlistGheDatVe;
    boolean[] flag = {false};
    String dateTimeString;
    Ve veDat;
    Ghe gheChon;
    VeDao veDao;
    VeAdapter veAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        // anhs xa và khởi tạo
        tvTenPhimDatVe = (TextView) findViewById(R.id.tvTenPhimDatVe);
        tvGiaPhimDatVe = (TextView) findViewById(R.id.tvGiaPhimDatVe);
        imgPhimDatVe = (ImageView) findViewById(R.id.imgPhimDatVe);
        imgRapDatVe = (ImageView) findViewById(R.id.imgRapDatVe);
        btnDateTimePickerTgXemPage = (Button) findViewById(R.id.btnDateTimePickerTgXemPage);
        btnChonGhe = (Button) findViewById(R.id.btnChonGhe);
        btnDatVe = (Button) findViewById(R.id.btnDatVe);
        ibtnCartDialog = (ImageButton) findViewById(R.id.ibtnCartDialog);
        rapDao = new RapDao();
        gheDao = new GheDao();
        phimDao = new PhimDao();
        // lấy đc listRap, rùi lấy ra cái rạp ng dùng click vô
        System.out.println(listRap);


        list = phimDao.getAllPhimToString();
        Phim phim = list.get(DetailPhim.vitriClickPhim);// Khởi tạo Spinner
        btnChonRap = (Button) findViewById(R.id.btnChonRap);
        tvTenPhimDatVe.setText(phim.getTenPhim());
        tvGiaPhimDatVe.setText("GIÁ VÉ: "+ Double.toString(phim.getGiaPhim())+ " VNĐ");
        badgeNumber();
        byte[] imgPhim = phim.getImgPhim();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgPhim, 0, imgPhim.length);
        imgPhimDatVe.setImageBitmap(bitmap);

        // Tạo một danh sách các mục


        btnChonRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRapDialog();
            }
        });

        btnChonRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRapDialog();
            }
        });

        // DateTimepicker Ngay xem page
        btnDateTimePickerTgXemPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNgayXemDateTimePicker();

            }
        });

        // NÚT  ĐẶT VÉ
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1. Mã vé tự tăng
                int maVe = 0;
                // 2. Mã phim
                int maPhim = phim.getMaPhim();
                //3. Mã User
                int maUser = dangNhap.currentUser.getMaUser();

                //4. Ma Rap
                listRap = rapDao.getAllRapToString();

                rapShowGhe = listRap.get(ListRapDialog.indexRapChon);

                int maRap = rapShowGhe.getMaRap();
//
                // 5. Ma ghế
                listGhe = gheDao.getGheByRap(rapShowGhe);
                gheChon = ListGheDialog.gheChon;
                MainActivity.database.Querydata("UPDATE Ghe SET Empty = 'false' WHERE MaGhe = " + gheChon.getMaGhe() + "");
                int maGhe = gheChon.getMaGhe();
                // 6. gia ve
                double giaVe = phim.getGiaPhim();
                try {
                    LocalDateTime currentDate = LocalDateTime.now();
                    LocalDateTime ngayDatDateTime = currentDate;

                    // Định dạng Chung cho  ngày tháng
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    //8. Ngày xem:  lấy ngày đặt là tg hiện tại

                    //  localDateTime ngayxem đã được lấy từ dialog DatetimepIcker từ user chọn
                    LocalDateTime ngayXemDatetime = dateTimeNgayXem;
                    // 9.thanh toanas ==  false
                    String thanhToan = "false";
                    // 10. TẠO VÉ

                    veDat = new Ve(maVe, maPhim, maUser, maRap, maGhe, ngayDatDateTime, ngayXemDatetime, giaVe, "false");
                    MainActivity.database.Querydata("insert into Ve(MaPhim, MaUser, MaRap, MaGhe, NgayDat, NgayXem, GiaVe, ThanhToan)"
                            + "values(" + maPhim + ", " + maUser + ", " + maRap + ", " + maGhe + ", '" + ngayDatDateTime.format(formatter) + "', '" + ngayXemDatetime.format(formatter) + "', " + giaVe + ", '" + thanhToan + "')");
                    Toast.makeText(DatVe.this, "Bạn đã thêm 1 vé phim", Toast.LENGTH_SHORT).show();


                    LocalDateTime currentDateTime = LocalDateTime.now();
                    limitedDateTime = currentDateTime.plusMinutes(1);

                } catch (Exception e) {
                    Toast.makeText(DatVe.this, "Có lỗi  " + e, Toast.LENGTH_SHORT).show();
                    Toast.makeText(DatVe.this, "Bạn chưa nhập đủ thông tin  " + e, Toast.LENGTH_SHORT).show();
                }
                // 7.ngay Dat

            }
        });
        // NUT CART
        ibtnCartDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCartDialog();
                badgeNumber();
            }
        });


//        //Thanh Toán
//        Button btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
//        String momo = "https://momo.vn";
//        btnThanhToan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(momo)));
//            }
//        });


    }

    public void badgeNumber() {
        if (dangNhap.currentUser != null) {
            VeDao veDao = new VeDao();
            List<Ve> listCart = veDao.getListCartByUserVaChuaThanhToan(dangNhap.currentUser.getMaUser());
            TextView tvBadge = (TextView) findViewById(R.id.tvBadge);
            tvBadge.setText(Integer.toString(listCart.size()));
            tvBadge.invalidate();
        }

    }

    private void showRapDialog() {
        ListRapDialog customDialog = new ListRapDialog(this, rapShowGhe, this);


        customDialog.show();
    }

    public void showGheDialog(Rap dataFromDialogRAP) {
        ListGheDialog customDialog = new ListGheDialog(this, dataFromDialogRAP, this);


        customDialog.show();
    }

    private void showNgayXemDateTimePicker() {
        DateTimePickerDialog customDialog = new DateTimePickerDialog(this, dateTimeString, this);


        customDialog.show();
    }

    private void showCartDialog() {
        ListCartDialog customDialog = new ListCartDialog(this, this);


        customDialog.show();
    }

    @Override
    public void onDialogListCartDismissed() {
        // Gọi hàm sau khi dialog Rap đã được đóng

//        recreate();
        badgeNumber();
    }

    @Override
    public void onDialogNgayXemDismissed(LocalDateTime dateTimeNgayXem) {
        // Gọi hàm sau khi dialog Rap đã được đóng

        dateTimeString = dateTimeNgayXem.toString();
        btnDateTimePickerTgXemPage.setText(dateTimeString);

    }

    @Override
    public void onDialogNgayXemDismissed() {

    }

    @Override
    public void onDialogListRapDismissed(Rap rapShowGhe) {
        // Gọi hàm sau khi dialog Rap đã được đóng

        showGheDialog(rapShowGhe);
        btnChonRap.setText(rapShowGhe.getTenRap());
        byte[] imgRap = rapShowGhe.getImgRap();
        Bitmap bitmapRap = BitmapFactory.decodeByteArray(imgRap, 0, imgRap.length);
        imgRapDatVe.setImageBitmap(bitmapRap);


    }

    @Override
    public void onDialogListGheDismissed(Ghe gheChon) {
        try {


            btnChonGhe.setText(gheChon.getTenGhe());


        } catch
        (Exception e) {
            Toast.makeText(this, "Lỗi " + e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDialogListRapDismissed() {

    }

    @Override
    public void onDialogListGheDismissed() {

    }

}

