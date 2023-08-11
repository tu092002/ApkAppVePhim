package com.nht.apktestapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nht.apktestapp.Adapters.GheAdapter;
import com.nht.apktestapp.Adapters.RapAdapter;
import com.nht.apktestapp.Dao.GheDao;
import com.nht.apktestapp.Dao.PhimDao;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;

import java.util.ArrayList;
import java.util.List;

public class ListGheDialog extends Dialog {
    TextView tvTenPhimDatVe, tvGiaPhimDatVe;
    List<Phim> list = new ArrayList<>();
    List<Rap> listRap = new ArrayList<>();
    List<Ghe> listGhe = new ArrayList<>();
    List<String> listTenRap = new ArrayList<>();
    PhimDao phimDao;
    RapDao rapDao;
    GheDao gheDao;
    Button btnChonRap;
    RapAdapter rapAdapter;
    GheAdapter gheAdapter;
    GridView gvlistRapDatVe;
    GridView gvlistGheDatVe;
    boolean[] flag = {false};
    Rap rapShowGhe;
    private OnDialogDismissListener callback;

    public ListGheDialog(Context context, OnDialogDismissListener callback) {
        super(context);
        this.callback = callback;
    }
    public ListGheDialog(Context context, Rap rapShowGhe, OnDialogDismissListener callback) {
        super(context);
        this.rapShowGhe = rapShowGhe;
        this.callback = callback;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ghe);
        gvlistGheDatVe = findViewById(R.id.gvListGheDatVe);
        // Đặt nội dung và hành động cho các thành phần
//        dialogText.setText("Đây là nội dung của dialog");

        gheDao = new GheDao();
//        listGhe = gheDao.getGheByRap(rapShowGhe);
        listGhe = gheDao.getAllGheToString();



        // Tạo một Adapter để kết nối dữ liệu và Spinner

        gheAdapter = new GheAdapter(getContext(), R.layout.activity_item_ghe, listGhe);

        // Định dạng giao diện cho Spinner khi đổ xuống
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Đặt Adapter cho Spinner
        gvlistGheDatVe.setAdapter(gheAdapter);
        gvlistGheDatVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//
//                Rap  rap = listRap.get(position);
//                btnChonRap.setText(rap.getTenRap());
                Toast.makeText(getContext(), "Rap " + listGhe.get(position).getTenGhe(), Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        Button dismissButton = findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback != null) {
                    callback.onDialogListGheDismissed();
                }
            }
        });
    }
}
