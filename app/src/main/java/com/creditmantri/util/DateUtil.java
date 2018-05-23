package com.creditmantri.util;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /*custom date format*/
    public static String getCustomDateAndTimeFormat(String inputDateStr, SimpleDateFormat targetDateFormat) {
        String returnDateFormat = "";
        try {
            SimpleDateFormat inputDateFormat = AppConstants.SERVER_DATE_TIME_FORMAT;
            Date dateObj = inputDateFormat.parse( inputDateStr);
            targetDateFormat.setTimeZone(TimeZone.getDefault());
            returnDateFormat = targetDateFormat.format(dateObj);
        } catch (Exception e) {
            Log.e(AppConstants.TAG, e.getMessage());
        }

        return returnDateFormat;
    }








}
