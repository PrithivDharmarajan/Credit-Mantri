package com.creditmantri.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.creditmantri.R;
import com.creditmantri.adpter.WeatherAdapter;
import com.creditmantri.main.BaseActivity;
import com.creditmantri.model.OpenWeatherMapResponse;
import com.creditmantri.model.WeatherDetailsEntity;
import com.creditmantri.service.APIRequestHandler;
import com.creditmantri.util.AppConstants;
import com.creditmantri.util.DateUtil;
import com.creditmantri.util.DialogManager;
import com.creditmantri.util.InterfaceBtnCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeScreen extends BaseActivity {

    @BindView(R.id.home_parent_lay)
    ViewGroup mHomeParentViewGroup;

    @BindView(R.id.header_txt)
    TextView mHeaderTxt;

    @BindView(R.id.header_right_img_lay)
    RelativeLayout mHeaderRightImgLay;

    @BindView(R.id.header_right_img)
    ImageView mHeaderRightImg;

    @BindView(R.id.city_spinner)
    Spinner mCitySpinner;

    @BindView(R.id.weather_recycler_view)
    RecyclerView mWeatherRecyclerView;

    private String mCityNameStr = "";
    private LinkedHashMap<String, String> mSortedArrList = new LinkedHashMap<>();
    private boolean mDoubleTapToExitAppBool = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_home_screen);
        initView();
    }


    /*View initialization*/
    private void initView() {
        /*ButterKnife for variable initialization*/
        ButterKnife.bind(this);

        /*Keypad to be hidden when a touch made outside the edit text*/
        setupUI(mHomeParentViewGroup);

        /*Set Header view*/
        mHeaderTxt.setText(getString(R.string.app_name));
        mHeaderRightImgLay.setVisibility(View.VISIBLE);
        mHeaderRightImg.setImageResource(R.drawable.setting_img);

        /*Set adapter data*/
        setCitySpinnerData();
    }

    /*View onClick*/
    @OnClick({R.id.header_right_img_lay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_right_img_lay:
                nextScreen(Settings.class);
                break;
        }
    }

    /*Weather Map API call*/
    private void weatherMapAPICall(String cityNameStr) {
        mCityNameStr = cityNameStr;
        APIRequestHandler.getInstance().forecastReportAPICall(cityNameStr, this);
    }


    /*Set City Spinner data*/
    private void setCitySpinnerData() {
        final List<String> cityArrList = Arrays.asList(getResources().getStringArray(R.array.city_list));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(HomeScreen.this, R.layout.adap_spinner_equ_selected, cityArrList);
        adapter.setDropDownViewResource(R.layout.adap_spinner_equ_list);
        mCitySpinner.setAdapter(adapter);

        /*City Spinner item click*/
        mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weatherMapAPICall(cityArrList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Default item click for API call*/
        if (cityArrList.size() > 0) {
            mCitySpinner.setSelection(0, true);
        }

    }

    /*Alert API Request success and failure*/
    @Override
    public void onRequestSuccess(Object resObj) {
        super.onRequestSuccess(resObj);
        if (resObj instanceof OpenWeatherMapResponse) {
            OpenWeatherMapResponse alertResponse = (OpenWeatherMapResponse) resObj;
            ArrayList<WeatherDetailsEntity> weatherDetailsArrList = alertResponse.getList();
            ArrayList<WeatherDetailsEntity> locWeatherArrList = new ArrayList<>();
            mSortedArrList=new LinkedHashMap<>();
            for (int posInt = 0; posInt < weatherDetailsArrList.size(); posInt++) {
                String dateStr = DateUtil.getCustomDateAndTimeFormat(weatherDetailsArrList.get(posInt).getDt_txt(), AppConstants.CUSTOM_DATE_TIME_FORMAT);
                if (!mSortedArrList.containsKey(dateStr)) {
                    mSortedArrList.put(dateStr, dateStr);
                    locWeatherArrList.add(weatherDetailsArrList.get(posInt));
                }
            }
            setAdapter(locWeatherArrList);
        }
    }

    @Override
    public void onRequestFailure(Throwable t) {
        if (t instanceof IOException) {
            DialogManager.getInstance().showNetworkErrorPopup(this,
                    (t instanceof java.net.ConnectException ? getString(R.string.no_internet) : getString(R.string
                            .connect_time_out)), new InterfaceBtnCallback() {
                        @Override
                        public void onPositiveClick() {
                            weatherMapAPICall(mCityNameStr);
                        }
                    });
        }
    }

    /*set Adapter*/
    private void setAdapter(ArrayList<WeatherDetailsEntity> notifications) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mWeatherRecyclerView.setLayoutManager(linearLayoutManager);
        mWeatherRecyclerView.setAdapter(new WeatherAdapter(notifications, this));

    }

    /*On back key pressed*/
    @Override
    public void onBackPressed() {
        exitFromApp();
    }

    /*App exit process*/
    private void exitFromApp() {
        if (mDoubleTapToExitAppBool) {
            finishAffinity();
            return;
        }

        mDoubleTapToExitAppBool = true;
        DialogManager.getInstance().showToast(this, getString(R.string.exit_msg));
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleTapToExitAppBool = false;
            }
        }, 2000);


    }

}