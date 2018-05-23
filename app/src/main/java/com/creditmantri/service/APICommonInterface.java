package com.creditmantri.service;


import com.creditmantri.model.OpenWeatherMapResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APICommonInterface {

    /*Reset API*/
    @GET
    Call<OpenWeatherMapResponse> forecastReportAPI(@Url String cityStr);

}
