package com.nht.apktestapp;

import com.nht.apktestapp.Model.Rap;

public interface OnDialogDismissListener {

    void onDialogListRapDismissed(Rap rapShowGhe);
    void onDialogListGheDismissed();
    void onDialogNgayXemDismissed(String dateTimeNgayXem);

}
