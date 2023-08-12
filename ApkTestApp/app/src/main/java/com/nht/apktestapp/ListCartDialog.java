package com.nht.apktestapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nht.apktestapp.Adapters.VeAdapter;
import com.nht.apktestapp.Dao.VeDao;
import com.nht.apktestapp.Model.Ve;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ListCartDialog extends Dialog {
    VeDao veDao;
    VeAdapter veAdapter;
    ArrayList<String> listTenVe = new ArrayList<>();
    List<Ve> listVe = new ArrayList<>();
    private OnDialogDismissListener callback;
    ListView lvCart;
    public ListCartDialog(@NonNull Context context, OnDialogDismissListener callback) {
        super(context);
        this.callback = callback;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cart);
        veDao = new VeDao();
        listVe = veDao.getListCartOrVe();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        listVe.forEach(s -> {
            listTenVe.add(Integer.toString(s.getMaPhim()));
        });

        veAdapter = new VeAdapter(getContext(), R.layout.activity_item_cart, listVe);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listTenVe);
        lvCart =  (ListView) findViewById(R.id.lvCart);
        lvCart.setAdapter(veAdapter);


        ListView lvCart = (ListView) findViewById(R.id.lvCart);


        lvCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Vé "+ position, Toast.LENGTH_SHORT).show();


                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("XÁC NHẬN");
                dialog.setMessage("Bạn có đồng ý xóa không ? ");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        MainActivity.sqLiteDatabase.rawQuery("DELETE  FROM Ve WHERE MaVe = ? ",
//                                new String[]{Integer.toString(listVe.get(position).getMaVe())});
                        veDao.DeleteVe(listVe.get(position).getMaVe());
                        listVe.remove(position);


                        veAdapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                return false;
            }
        });
        Button dismissButton = findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback != null) {
                    callback.onDialogListCartDismissed();
                }
            }
        });
    }

}
