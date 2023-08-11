package com.nht.apktestapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateTimePickerActivity extends Dialog {

    String dateTimeNgayXem;
    private DatePicker datePickerTgXem;
    private TimePicker timePickerTgXem;
    private Button btnSaveNgayXem,btnDateTimePickerTgXemPage;
    private OnDialogDismissListener callback;

    public DateTimePickerActivity(Context context, String dateTimeNgayXem, OnDialogDismissListener callback) {
        super(context);
        this.callback = callback;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ngayxem);
        btnSaveNgayXem = (Button) findViewById(R.id.btnSaveNgayXem);
        datePickerTgXem = (DatePicker) findViewById(R.id.datePickerTgXem);
        timePickerTgXem = (TimePicker) findViewById(R.id.timePickerTgXem);
        btnSaveNgayXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePickerTgXem.getYear();
                int month = datePickerTgXem.getMonth();
                int day = datePickerTgXem.getDayOfMonth();

                int hour = timePickerTgXem.getHour();
                int minute = timePickerTgXem.getMinute();

                dateTimeNgayXem = year + "-" + (month + 1) + "-" + day + " " + hour + ":" + minute;

                btnDateTimePickerTgXemPage = (Button) findViewById(R.id.btnDateTimePickerTgXemPage);
//                btnDateTimePickerTgXemPage.setText(dateTimeNgayXem);
                dismiss();
                if (callback != null) {
                    callback.onDialogNgayXemDismissed(dateTimeNgayXem);
                }
            }
        });


        Button dismissButton = findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback != null) {
                    callback.onDialogNgayXemDismissed(dateTimeNgayXem);
                }
            }
        });
    }
}
