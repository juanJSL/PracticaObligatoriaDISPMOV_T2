package com.example.jj.practicaobligatoriadispmov_t2;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by JJ on 07/02/2018.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        // Constructor Por Defecto
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }




}