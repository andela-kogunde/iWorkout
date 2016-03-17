package com.andela.iworkout.utilities;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andela.iworkout.R;

/**
 * author: Kehinde Ogunde
 */
public class MsgBox {
    private static Toast toast;
    private static Snackbar snackbar;

    public static void show(Context context, String message) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(View view, String message, String action, View.OnClickListener clickListener) {
        if (snackbar != null) {
            snackbar.dismiss();
        }

        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(action, clickListener);
        snackbar.show();
    }

    public static AlertDialog show(Context context, String message, View.OnClickListener listener, boolean cancelable) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.completed_pushup, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(cancelable);

        TextView messageView = (TextView) dialogView.findViewById(R.id.message);
        messageView.setText(message);
        dialogView.findViewById(R.id.gotit).setOnClickListener(listener);
        return dialogBuilder.create();
    }
}
