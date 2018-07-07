package com.association.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

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

  //strType=0 -finish
  //strType=1 -Restart the Activity
  //strType=2 -Dismiss dialog

  public static String alerts(final Context context, String Errormsg, final String strType) {
    try {

      final Dialog dialog = new Dialog(context);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      Window window = dialog.getWindow();
      dialog.setContentView(R.layout.popupdialog);
      window.setGravity(Gravity.CENTER);
      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
      window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
      dialog.setCancelable(false);

      TextView tvClose = (TextView) dialog.findViewById(R.id.close);
      TextView tvMsg = (TextView) dialog.findViewById(R.id.popup_text);

      tvMsg.setText(Errormsg);
      dialog.setCanceledOnTouchOutside(false);
      dialog.show();
      tvClose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (strType.equals("1")) {
            Intent refresh = new Intent(context, context.getClass());
            context.startActivity(refresh);
            ((Activity) context).finish();
          } else if (strType.equals("0")) {
            ((Activity) context).finish();
          }
          dialog.dismiss();

        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Errormsg;

  }
}
