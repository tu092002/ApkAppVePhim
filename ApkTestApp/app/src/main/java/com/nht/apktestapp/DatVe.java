package com.nht.apktestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nht.apktestapp.Adapters.GheAdapter;
import com.nht.apktestapp.Adapters.RapAdapter;
import com.nht.apktestapp.Dao.GheDao;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Dao.VeDao;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.Model.Ve;

import java.util.ArrayList;
import java.util.List;

public class DatVe extends AppCompatActivity implements OnDialogDismissListener {
    TextView tvTenPhimDatVe, tvGiaPhimDatVe;
    List<Phim> list = new ArrayList<>();
    List<Rap> listRap;
    List<Ghe> listGhe;
    List<String> listTenRap = new ArrayList<>();
    PhimDao phimDao;
    RapDao rapDao;
    GheDao gheDao;
    Button btnChonRap, btnDateTimePickerTgXemPage, btnDatVe;
    RapAdapter rapAdapter;
    GheAdapter gheAdapter;
    GridView gvlistRapDatVe;
    GridView gvlistGheDatVe;
    boolean[] flag = {false};
    String dateTimeString;
    Rap rapShowGhe;
    Ve veDat;
    VeDao veDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        // anhs xa và khởi tạo
        tvTenPhimDatVe = (TextView) findViewById(R.id.tvTenPhimDatVe);
        tvGiaPhimDatVe = (TextView) findViewById(R.id.tvGiaPhimDatVe);
        btnDateTimePickerTgXemPage = (Button) findViewById(R.id.btnDateTimePickerTgXemPage);
        btnDatVe = (Button) findViewById(R.id.btnDatVe);
        rapDao = new RapDao();
        gheDao = new GheDao();
        phimDao = new PhimDao();

        // lấy đc listRap, rùi lấy ra cái rạp ng dùng click vô
        listRap = rapDao.getAllRapToString();
        System.out.println(listRap);
        rapShowGhe = listRap.get(ListRapDialog.indexRapChon - 1);


        list = phimDao.getAllPhimToString();
        Phim phim = list.get(DetailPhim.vitriClickPhim);// Khởi tạo Spinner
        btnChonRap = (Button) findViewById(R.id.btnChonRap);
        tvTenPhimDatVe.setText(phim.getTenPhim());
        tvGiaPhimDatVe.setText(Double.toString(phim.getGiaPhim()));
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
                int maUser = 2;

                //4. Ma Rap
                int maRap = rapShowGhe.getMaRap();
                Toast.makeText(DatVe.this, "thêm thành côg" + maRap, Toast.LENGTH_SHORT).show();
//
//                // 5. Ma ghế
//                int maGhe = 4;
//                // 6. gia ve
//                double giaVe = phim.getGiaPhim();

//                // 7.ngay Dat
//                LocalDateTime currentDate = LocalDateTime.now();
//                LocalDateTime ngayDatDateTime = currentDate;
//
//                // Định dạng Chung cho  ngày tháng
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//                //8. Ngày xem:  lấy ngày đặt là tg hiện tại
//
//                // định dạng từ CHuỗi => localDateTime
//                LocalDateTime ngayXemDatetime = LocalDateTime.parse(dateTimeString, formatter);
//
//                // 9.thanh toanas ==  false
//                // 10. TẠO VÉ
//                Ve veDat = new Ve(maVe, maPhim, maUser, maRap, maGhe, ngayDatDateTime, ngayXemDatetime, giaVe, "false");
//
//
//                long i = veDao.InsertVe(veDat);
//                if (i == 1){
//                    Toast.makeText(DatVe.this, "thêm thành côg", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(DatVe.this, "thêm thất bại", Toast.LENGTH_SHORT).show();
//
//                }
            }
        });

    }


    private void showRapDialog() {
        ListRapDialog customDialog = new ListRapDialog(this, rapShowGhe, this);


        customDialog.show();
    }

    public void showGheDialog(Rap dataFromDialogRAP) {
        ListGheDialog customDialog = new ListGheDialog(this, rapShowGhe, this);


        customDialog.show();
    }

    private void showNgayXemDateTimePicker() {
        DateTimePickerActivity customDialog = new DateTimePickerActivity(this, dateTimeString, this);


        customDialog.show();
    }

    @Override
    public void onDialogNgayXemDismissed(String dateTimeNgayXem) {
        // Gọi hàm sau khi dialog Rap đã được đóng

        dateTimeString = dateTimeNgayXem;
        btnDateTimePickerTgXemPage.setText(dateTimeString);

    }

    @Override
    public void onDialogListRapDismissed(Rap rapShowGhe) {
        // Gọi hàm sau khi dialog Rap đã được đóng
        showGheDialog(rapShowGhe);
        btnChonRap.setText(rapShowGhe.getTenRap());

    }

    @Override
    public void onDialogListGheDismissed() {
        // Gọi hàm sau khi dialog Ghe đã được đóng
    }
//    private void showGheDialog(Rap rap) {
//        // Tạo đối tượng Dialog và gán layout
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_ghe);
//
//        // Lấy các thành phần trong layout của dialog
////        TextView dialogText = dialog.findViewById(R.id.dialog_text);
//        Button closeButton = dialog.findViewById(R.id.dialog_button);
//        gvlistRapDatVe = dialog.findViewById(R.id.gvListRapDatVe);
//        // Đặt nội dung và hành động cho các thành phần
////        dialogText.setText("Đây là nội dung của dialog");
//
//        gheDao = new GheDao();
//        listGhe = gheDao.getGheByRap(rap);
//
//
//        // Tạo một Adapter để kết nối dữ liệu và Spinner
//
//        gheAdapter = new GheAdapter(this, R.layout.activity_item_ghe, listGhe);
//
//        // Định dạng giao diện cho Spinner khi đổ xuống
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Đặt Adapter cho Spinner
//        gvlistGheDatVe.setAdapter(gheAdapter);
//        gvlistGheDatVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////
////                Rap  rap = listRap.get(position);
////                btnChonRap.setText(rap.getTenRap());
//                Toast.makeText(DatVe.this, "Rap " + listGhe.get(position).getTenGhe(), Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//
//            }
//        });
//
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss(); // Đóng dialog khi nhấn nút
//            }
//        });
//
//        // Hiển thị dialog
//        dialog.show();
//    }
}

