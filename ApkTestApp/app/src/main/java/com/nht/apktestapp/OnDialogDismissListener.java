package com.nht.apktestapp;

import com.nht.apktestapp.Model.Ghe;
import com.nht.apktestapp.Model.Rap;

import java.time.LocalDateTime;

public interface OnDialogDismissListener {

    void onDialogListRapDismissed(Rap rapShowGhe);
    void onDialogListGheDismissed(Ghe gheChon);
    void onDialogListRapDismissed();

    void onDialogListGheDismissed();

    void onDialogNgayXemDismissed(LocalDateTime dateTimeNgayXem);
    void onDialogNgayXemDismissed();

    void onDialogListCartDismissed();

}
