package com.ailoitte.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.ailoitte.R;

import java.util.TimeZone;



public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog dialog;

    AlertDialog alertDialog, commonalert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        dialog=new ProgressDialog(this);





    }



    public void shwProgress() {
        dialog.setMessage(getString(R.string.please_wait));

        dialog.setCancelable(false);
        //  ProgressBar progressbar=(ProgressBar)dialog.findViewById(android.R.id.progress);
        // progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#16172b"), android.graphics.PorterDuff.Mode.SRC_IN);
        dialog.show();
    }

    public void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        // inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }


    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onDestroy();
    }

    public void hideprogress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



    public void closeCommonAlert() {
        commonalert.dismiss();
    }

    public void alert(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        dialog.dismiss();

                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    @Override
    protected void onResume() {
        super.onResume();

    }



    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // finish();
    }
    protected abstract int getlayout();
}
