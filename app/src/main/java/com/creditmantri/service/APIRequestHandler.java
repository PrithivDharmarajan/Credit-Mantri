package com.creditmantri.service;


import android.support.annotation.NonNull;

import com.creditmantri.main.BaseActivity;
import com.creditmantri.model.OpenWeatherMapResponse;
import com.creditmantri.util.AppConstants;
import com.creditmantri.util.DialogManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequestHandler {

    private static APIRequestHandler sInstance = new APIRequestHandler();

    /*Init retrofit for API call*/
    private APICommonInterface mServiceInterface = serviceInterface();


    public static APIRequestHandler getInstance() {
        return sInstance;
    }

    private APICommonInterface serviceInterface() {
        return new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APICommonInterface.class);
    }



    /*Registration API*/
    public void forecastReportAPICall(String cityStr, final BaseActivity baseActivity) {
        DialogManager.getInstance().showProgress(baseActivity);
        mServiceInterface.forecastReportAPI(String.format(AppConstants.WEATHER_URL,cityStr)).enqueue(new Callback<OpenWeatherMapResponse>() {
            @Override
            public void onResponse(@NonNull Call<OpenWeatherMapResponse> call, @NonNull Response<OpenWeatherMapResponse> response) {
                DialogManager.getInstance().hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    baseActivity.onRequestSuccess(response.body());
                } else {
                    baseActivity.onRequestFailure(new Throwable(response.raw().message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<OpenWeatherMapResponse> call, @NonNull Throwable t) {
                DialogManager.getInstance().hideProgress();
                baseActivity.onRequestFailure(t);
            }
        });
    }


}


