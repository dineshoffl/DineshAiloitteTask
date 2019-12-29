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
    ImageView my_cart, notification;
    //  NotificationBadge notify_badge, cart_badge;
    boolean isloading = false;
    LinearLayout image_lay;
    int totalcount = -1;
    ProgressDialog dialog;
    TextView tv_clickhere;
    LinearLayout timer_lay;
    AlertDialog alertDialog, commonalert;
    ImageView imgback;
    int REQUEST_CHECK_SETTINGS = 100;
    TextView tv_title;
    boolean isLastPage = false;
    Button btn_ok;
    int FILE_REQUEST_CODE = 100;
    int IMAGE_REQUEST_CODE = 101;
    int CAMERA_REQUEST_CODE = 102;
    TextView tv_hrs, tv_mins, tv_secs,tv_tool_tittle;
    private Handler handler = new Handler();
    private Runnable runnable;
    TimeZone timeZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        dialog=new ProgressDialog(this);





    }

    public void openSettings() {
        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null)));
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//        startActivityForResult(intent, 101);
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


//    public File saveBitmapToFile(File file){
//
//
//        long length = file.length();
//        length = length/1024;
//        if (length>400){
//            try {
//                // BitmapFactory options to downsize the image
//                BitmapFactory.Options o = new BitmapFactory.Options();
//                o.inJustDecodeBounds = true;
//                o.inSampleSize = 6;
//                // factor of downsizing the image
//
//                FileInputStream inputStream = new FileInputStream(file);
//                //Bitmap selectedBitmap = null;
//                BitmapFactory.decodeStream(inputStream, null, o);
//                inputStream.close();
//
//                // The new size we want to scale to
//                final int REQUIRED_SIZE=400;
//
//                // Find the correct scale value. It should be the power of 2.
//                int scale = 1;
//                while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
//                        o.outHeight / scale / 2 >= REQUIRED_SIZE) {
//                    scale *= 2;
//                }
//
//                BitmapFactory.Options o2 = new BitmapFactory.Options();
//                o2.inSampleSize = scale;
//                inputStream = new FileInputStream(file);
//
//                Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
//                inputStream.close();
//
//                // here i override the original image file
//                file.createNewFile();
//                FileOutputStream outputStream = new FileOutputStream(file);
//
//                selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 200 , outputStream);
//
//                return file;
//            } catch (Exception e) {
//                return file;
//            }
//        }
//        else {
//            return file;
//        }
//
//    }
//    public void setcameraPicker(int maxSelect) {
//        Intent intent = new Intent(this, FilePickerActivity.class);
//        intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
//                .setCheckPermission(true)
//                .setShowImages(true)
//                .setShowVideos(false)
//                .setShowFiles(false)
//                .enableImageCapture(true)
//                .enableVideoCapture(false)
//                .setIgnoreNoMedia(true)
//                .setMaxSelection(maxSelect)
//                .build());
//        intent.putExtra("camera", "camera");
//        startActivityForResult(intent, CAMERA_REQUEST_CODE);
//
//    }

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


    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
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
