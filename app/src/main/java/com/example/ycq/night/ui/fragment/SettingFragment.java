package com.example.ycq.night.ui.fragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.ycq.night.R;

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.appset);
    }
}
