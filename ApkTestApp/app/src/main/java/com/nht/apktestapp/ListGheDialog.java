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
import com.nht.apktestapp.Dao.GheDao;
import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Rap;

import java.util.ArrayList;
import java.util.List;

public class ListGheDialog extends Dialog {
    TextView tvTenPhimDatVe, tvGiaPhimDatVe;
    List<Ghe> listGhe = new ArrayList<>();
    List<String> listTenRap = new ArrayList<>();
    Ghe gheChon = new Ghe();
    GheDao gheDao;
    public static int indexGheChon = 1;
    GheAdapter gheAdapter;
    GridView gvlistGheDatVe;
    Rap rapShowGhe;
    private OnDialogDismissListener callback;

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
        listGhe = gheDao.getGheByRap(rapShowGhe);
//        listGhe = gheDao.getAllGheToString();


        // Tạo một Adapter để kết nối dữ liệu và Spinner

        gheAdapter = new GheAdapter(getContext(), R.layout.activity_item_ghe, listGhe);


        gvlistGheDatVe.setAdapter(gheAdapter);
        gvlistGheDatVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                indexGheChon = position;
                Ghe gheChon = listGhe.get(position);

                Toast.makeText(getContext(), "Rạp "+rapShowGhe.getTenRap() + ",  Ghế " + listGhe.get(position).getTenGhe(), Toast.LENGTH_SHORT).show();

                dismiss();
                if (callback != null) {
                    callback.onDialogListGheDismissed(gheChon);
                }
            }
        });
        Button dismissButton = findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback != null) {
                    callback.onDialogListGheDismissed(gheChon);
                }
            }
        });
    }
}
