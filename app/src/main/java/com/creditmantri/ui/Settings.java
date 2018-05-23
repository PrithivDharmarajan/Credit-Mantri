package com.creditmantri.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.creditmantri.R;
import com.creditmantri.main.BaseActivity;
import com.creditmantri.util.AppConstants;
import com.creditmantri.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


public class Settings extends BaseActivity {

    @BindView(R.id.settings_parent_lay)
    ViewGroup mSettingsViewGroup;

    @BindView(R.id.header_txt)
    TextView mHeaderTxt;

    @BindView(R.id.header_left_img_lay)
    RelativeLayout mHeaderLeftImgLay;

    @BindView(R.id.celsius_radio_btn)
    RadioButton mCelsiusRadioBtn;

    @BindView(R.id.fahrenheit_radio_btn)
    RadioButton mFahrenheitRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_settings);
        initView();
    }


    /*View initialization*/
    private void initView() {
        /*ButterKnife for variable initialization*/
        ButterKnife.bind(this);

        /*Keypad to be hidden when a touch made outside the edit text*/
        setupUI(mSettingsViewGroup);

        /*Set Header view*/
        mHeaderTxt.setText(getString(R.string.settings));
        mHeaderLeftImgLay.setVisibility(View.VISIBLE);

        /*Set Data */
        mCelsiusRadioBtn.setChecked(!PreferenceUtil.getBoolPreferenceValue(this, AppConstants.CONVERSION_FAHRENHEIT));
        mFahrenheitRadioBtn.setChecked(PreferenceUtil.getBoolPreferenceValue(this, AppConstants.CONVERSION_FAHRENHEIT));


    }


    /*View onClick*/
    @OnClick({R.id.header_left_img_lay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_left_img_lay:
                onBackPressed();
                break;

        }
    }


    /*View onCheckedChanged*/
    @OnCheckedChanged({R.id.celsius_radio_btn, R.id.fahrenheit_radio_btn})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.celsius_radio_btn:
                if (isChecked) {
                    PreferenceUtil.storeBoolPreferenceValue(this, AppConstants.CONVERSION_FAHRENHEIT, false);
                    mFahrenheitRadioBtn.setChecked(false);
                }

                break;
            case R.id.fahrenheit_radio_btn:
                if (isChecked) {
                    PreferenceUtil.storeBoolPreferenceValue(this, AppConstants.CONVERSION_FAHRENHEIT, true);
                    mCelsiusRadioBtn.setChecked(false);
                }
                break;

        }
    }


    @Override
    public void onBackPressed() {
        previousScreen(HomeScreen.class);
    }

}