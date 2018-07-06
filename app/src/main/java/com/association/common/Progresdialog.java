package com.association.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.association.R;

/**
 * Created by Mani on 21-11-2017.
 */

public class Progresdialog {
    public static ProgressDialog pgdialog;

    public static void showDialog(Context mContext, String strMessage) {
        try {
            if (pgdialog != null)
                if (pgdialog.isShowing())
                    pgdialog.dismiss();
            pgdialog = ProgressDialog.show(mContext, "", strMessage, true);
//            pgdialog = new ProgressDialog(BookingHistory.this);

            pgdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            pgdialog.setCancelable(false);
            pgdialog.show();
            pgdialog.setContentView(R.layout.custom_loading);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dismissDialog() {
        try {
            if (pgdialog.isShowing())
                pgdialog.dismiss();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
