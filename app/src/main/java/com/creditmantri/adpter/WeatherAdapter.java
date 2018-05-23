package com.creditmantri.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creditmantri.R;
import com.creditmantri.model.WeatherDetailsEntity;
import com.creditmantri.util.AppConstants;
import com.creditmantri.util.DateUtil;
import com.creditmantri.util.PreferenceUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.Holder> {

    private ArrayList<WeatherDetailsEntity> mWeatherDetailsArrList;
    private Context mContext;

    public WeatherAdapter(ArrayList<WeatherDetailsEntity> dataEntryRes, Context context) {
        mWeatherDetailsArrList = dataEntryRes;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adap_city_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        WeatherDetailsEntity weatherDetailsEntity = mWeatherDetailsArrList.get(position);
        holder.mDateTxt.setText(DateUtil.getCustomDateAndTimeFormat(weatherDetailsEntity.getDt_txt(), AppConstants.CUSTOM_DATE_TIME_FORMAT));

        holder.mWeatherTxt.setText(weatherDetailsEntity.getWeather().size() > 0 ? weatherDetailsEntity.getWeather().get(0).getDescription() : "");
        holder.mTemperatureTxt.setText(PreferenceUtil.getBoolPreferenceValue(mContext, AppConstants.CONVERSION_FAHRENHEIT) ? kelvinToFahrenheit(weatherDetailsEntity.getMain().getTemp()) : kelvinToCelsius(weatherDetailsEntity.getMain().getTemp()));
        holder.mHumidityTxt.setText(String.valueOf(weatherDetailsEntity.getMain().getHumidity()) + mContext.getString(R.string.percentage));
        holder.mWindTxt.setText(String.format(mContext.getString(R.string.wind_val), String.valueOf(weatherDetailsEntity.getWind().getSpeed())));
        holder.mPressureTxt.setText(String.format(mContext.getString(R.string.pressure_val), String.valueOf(weatherDetailsEntity.getMain().getPressure())));

        holder.mShareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shareMsgTxt = "\n" + mContext.getString(R.string.weather_details) + " " + mContext.getString(R.string.colon) + "\n\n" +
                        mContext.getString(R.string.date) + " " + mContext.getString(R.string.colon) + " " + holder.mDateTxt.getText().toString().trim() + "\n" +
                        mContext.getString(R.string.weather) + " " + mContext.getString(R.string.colon) + " " + holder.mWeatherTxt.getText().toString().trim() + "\n" +
                        mContext.getString(R.string.temperature) + " " + mContext.getString(R.string.colon) + " " + holder.mTemperatureTxt.getText().toString().trim() + "\n" +
                        mContext.getString(R.string.humidity) + " " + mContext.getString(R.string.colon) + " " + holder.mHumidityTxt.getText().toString().trim() + "\n" +
                        mContext.getString(R.string.wind) + " " + mContext.getString(R.string.colon) + " " + holder.mWindTxt.getText().toString().trim() + "\n" +
                        mContext.getString(R.string.pressure) + " " + mContext.getString(R.string.colon) + " " + holder.mPressureTxt.getText().toString().trim()+"\n";

                Intent shareWeatherReportIntent = new Intent();
                shareWeatherReportIntent.setAction(Intent.ACTION_SEND);
                shareWeatherReportIntent.putExtra(Intent.EXTRA_TEXT, shareMsgTxt);
                shareWeatherReportIntent.setType("text/plain");
                mContext.startActivity(Intent.createChooser(shareWeatherReportIntent, mContext.getResources().getText(R.string.send_to)));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mDetailLay.setVisibility(holder.mDetailLay.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }

    private String kelvinToFahrenheit(double kevinDouble) {
        return String.valueOf((int) ((kevinDouble - 273.15) * 1.8000 + 32.0)) + mContext.getString(R.string.fahrenheit_sym);
    }

    private String kelvinToCelsius(double kevinDouble) {
        return String.valueOf((int) (kevinDouble - 273.15)) + mContext.getString(R.string.celsius_sym);
    }

    @Override
    public int getItemCount() {
        return mWeatherDetailsArrList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.date_txt)
        TextView mDateTxt;

        @BindView(R.id.weather_txt)
        TextView mWeatherTxt;

        @BindView(R.id.detail_lay)
        LinearLayout mDetailLay;

        @BindView(R.id.temperature_txt)
        TextView mTemperatureTxt;

        @BindView(R.id.humidity_txt)
        TextView mHumidityTxt;

        @BindView(R.id.wind_txt)
        TextView mWindTxt;

        @BindView(R.id.pressure_txt)
        TextView mPressureTxt;

        @BindView(R.id.share_txt)
        TextView mShareTxt;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
