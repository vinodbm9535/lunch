package com.example.admin.lunch.helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.lunch.R;

public class Utils {

    private static Dialog dialog;

    public static void progressShow(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dialog.show();
    }

    public static void progressDismiss(Context context) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void showSnackBar(View rootview, String msg, int length) {
        Snackbar snack = Snackbar.make(rootview, msg, length);
        snack.show();
    }

    public static boolean isEmptyEditText(EditText editText, String msg) {

        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(msg);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean isPhoneCorrect(EditText editText, String msg, int length) {

        if (editText.getText().toString().trim().length() != length) {
            editText.setError(msg);
            editText.requestFocus();
            return true;
        }
        return false;
    }
}

