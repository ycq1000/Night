package com.example.ycq.night.ui;

import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.example.ycq.night.R;

import java.util.List;

public class SettingActivity extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        //super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.appsetgroup, target);
    }
}
