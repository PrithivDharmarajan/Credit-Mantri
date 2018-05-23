package com.creditmantri.util;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppConstants {

    static final String SHARE_PREFERENCE = "SHARE_PREFERENCE";
    public static String TAG = "TAG";


    /*App URL's*/
    final public static String BASE_URL = "https://api.openweathermap.org/";

    final public static String WEATHER_URL = BASE_URL + "data/2.5/forecast?q=%1$s,IN&appid=3997dc6a60689dcc8a80559c82d09e9c";

    static final SimpleDateFormat SERVER_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final SimpleDateFormat CUSTOM_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static String CONVERSION_FAHRENHEIT = "CONVERSION_FAHRENHEIT";


}

