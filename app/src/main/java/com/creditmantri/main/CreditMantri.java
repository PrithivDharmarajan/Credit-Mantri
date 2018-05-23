package com.creditmantri.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.creditmantri.ui.HomeScreen;

import java.lang.Thread.UncaughtExceptionHandler;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class CreditMantri extends android.app.Application {

    private static boolean activityVisible;
    private static CreditMantri mInstance;

    public static synchronized CreditMantri getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityStopped() {
        activityVisible = false;
    }

    public static void activityFinished() {
        activityVisible = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        /*init UncaughtException*/
        Thread.setDefaultUncaughtExceptionHandler(new unCaughtException());

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /*unCaughtException*/
    private class unCaughtException implements UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {

            /*If the App is crashed we push to restart the application*/
            if (activityVisible) {
                Class<?> nextScreenClass = HomeScreen.class;
                Intent intent = new Intent(mInstance, nextScreenClass);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }

                Runtime.getRuntime().exit(0);
            }
        }
    }


    @Override
    public void registerActivityLifecycleCallbacks(
            ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }
}
