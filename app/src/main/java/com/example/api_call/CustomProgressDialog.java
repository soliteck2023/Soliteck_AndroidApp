package com.example.api_call;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;

public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    public static ProgressDialog getDialogue(Activity activity) {
        CustomProgressDialog dialog = new CustomProgressDialog(activity, R.style.myDialogTheme);
        dialog.setIndeterminate(true);
        dialog.getWindow().getAttributes().gravity = Gravity.FILL_HORIZONTAL; //prajakta change 17 to Gravity.CENTER
        dialog.setCancelable(false);
        return dialog;
    }
    public static ProgressDialog getDialogueProcessing(Activity activity) {
        CustomProgressDialog dialog = new CustomProgressDialog(activity, R.style.myDialogTheme);
        dialog.setIndeterminate(true);
        dialog.setTitle("Processing your transaction...");
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;  ///prajakta change 17 to Gravity.CENTER
        dialog.setCancelable(false);
        return dialog;
    }
}
