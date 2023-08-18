package com.nht.apktestapp.ActController;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.nht.apktestapp.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimePickerDialog extends Dialog {

    public static  LocalDateTime dateTimeNgayXem;
    private DatePicker datePickerTgXem;
    private TimePicker timePickerTgXem;
    private Button btnSaveNgayXem, btnDateTimePickerTgXemPage;
    private OnDialogDismissListener callback;

    public DateTimePickerDialog(Context context, String dateTimeNgayXem, OnDialogDismissListener callback) {
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
                // Tạo đối tượng LocalDate từ thông tin ngày, tháng, năm
                LocalDate localDate = LocalDate.of(year, month + 1, day); // Lưu ý: tháng bắt đầu từ 1
                // Tạo đối tượng LocalTime từ thông tin giờ và phút
                LocalTime localTime = LocalTime.of(hour, minute);

                // Tạo đối tượng LocalDateTime từ LocalDate và LocalTime
                LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);


                dateTimeNgayXem = localDateTime;

//                dateTimeNgayXem = year + "-" + (month + 1) + "-" + day + " " + hour + ":" + minute;

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
                if(dateTimeNgayXem != null){
                    dismiss();
                    if (callback != null) {
                        callback.onDialogNgayXemDismissed(dateTimeNgayXem);
                    }
                }
                else {
                    dismiss();
                    if (callback != null) {
                        callback.onDialogNgayXemDismissed();
                    }
                }

            }
        });
    }
}
