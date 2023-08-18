package com.nht.apktestapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.nht.apktestapp.Adapters.RapAdapter;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Model.Phim;
import com.nht.apktestapp.Model.Rap;

import java.util.ArrayList;
import java.util.List;

public class ListRapDialog extends Dialog {
    TextView tvTenPhimDatVe, tvGiaPhimDatVe;
    Button btnChonRap;

    List<Phim> list = new ArrayList<>();
    List<Rap> listRap = new ArrayList<>();
    List<String> listTenRap = new ArrayList<>();
    RapDao rapDao;
    RapAdapter rapAdapter;
    GridView gvlistRapDatVe;
    boolean[] flag = {false};
    public static int indexRapChon = 1;
    Rap rapShowGhe=  null;
    private OnDialogDismissListener callback;

    public ListRapDialog(Context context, Rap dataToPassRap, OnDialogDismissListener callback) {
        super(context);
        this.callback = callback;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rap);
        gvlistRapDatVe = findViewById(R.id.gvListRapDatVe);
        // Đặt nội dung và hành động cho các thành phần
//        dialogText.setText("Đây là nội dung của dialog");

        rapDao = new RapDao();
        listRap = rapDao.getAllRapToString();
        listRap.forEach(rap -> {
            listTenRap.add(rap.getTenRap());
        });

        // Tạo một Adapter để kết nối dữ liệu và Spinner
        rapAdapter = new RapAdapter(getContext(), R.layout.activity_item_rap, listRap);

        // Định dạng giao diện cho Spinner khi đổ xuống
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Đặt Adapter cho Spinner
        gvlistRapDatVe.setAdapter(rapAdapter);


        gvlistRapDatVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                indexRapChon = position;
                rapShowGhe = listRap.get(indexRapChon);

                dismiss();
                if (callback != null) {

                    callback.onDialogListRapDismissed(rapShowGhe);


                }
                //                Toast.makeText(DatVe.this, "rap " + listRap.get(position).getTenRap(), Toast.LENGTH_SHORT).show();

            }
        });
        Button dismissButton = findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rapShowGhe != null){
                    dismiss();
                    if (callback != null) {
                        callback.onDialogListRapDismissed(rapShowGhe);

                    }
                }
                else{
                    dismiss();
                    if (callback != null) {
                        callback.onDialogListRapDismissed();

                    }
                }

            }
        });
    }
}
