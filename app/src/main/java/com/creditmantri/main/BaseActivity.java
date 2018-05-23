package com.creditmantri.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseActivity extends AppCompatActivity   {

    private AppCompatActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Default Init*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mActivity = this;

        /*Init default font*/
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.otf").build());
    }


    /*Apply font plugin default class*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    /*This method is used to check, if the current view can be focused in the edit text */
    protected void setupUI(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(mActivity);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View mInnerView = ((ViewGroup) view).getChildAt(i);
                setupUI(mInnerView);
            }
        }
    }

    /*Keypad to be hidden when a touch made outside the edit text*/
    protected void hideSoftKeyboard(Activity activity) {
        try {
            if (activity != null && !activity.isFinishing()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) activity
                        .getSystemService(INPUT_METHOD_SERVICE);

                if (mInputMethodManager != null && activity.getCurrentFocus() != null
                        && activity.getCurrentFocus().getWindowToken() != null) {
                    mInputMethodManager.hideSoftInputFromWindow(activity
                            .getCurrentFocus().getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            Log.e(activity.getClass().getSimpleName(), e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        CreditMantri.activityResumed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        CreditMantri.activityStopped();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /*Application control*/
    protected CreditMantri app() {
        return ((CreditMantri) mActivity.getApplication());
    }


    /*Direct to next activity*/
    public void nextScreen(Class<?> clazz) {
        Intent nextScreenIntent = new Intent(getApplicationContext(), clazz);
        mActivity.startActivity(nextScreenIntent);
        mActivity.overridePendingTransition(com.creditmantri.R.anim.slide_in_right,
                com.creditmantri.R.anim.slide_out_left);

        mActivity.finish();

    }

    /*Direct to previous activity*/
    public void previousScreen(Class<?> clazz) {
        Intent previousScreenIntent = new Intent(getApplicationContext(), clazz);
        mActivity.startActivity(previousScreenIntent);
        mActivity.overridePendingTransition(com.creditmantri.R.anim.slide_out_right,
                com.creditmantri.R.anim.slide_in_left);
        mActivity.finish();
    }


    /*Check screen orientation*/
    protected boolean IsScreenModePortrait() {
        return this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /*API call back success*/
    public void onRequestSuccess(Object resObj) {

    }

    /*API call back failure*/
    public void onRequestFailure( Throwable t) {
//        if (t.getMessage() != null && !t.getMessage().isEmpty() && !(t instanceof IOException)) {
//            DialogManager.getInstance().showAlertPopup(mActivity, t.getMessage(), new InterfaceBtnCallback() {
//                @Override
//                public void onPositiveClick() {
//
//                }
//            });
//        }
    }






}
