package com.example.jj.practicaobligatoriadispmov_t2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by JJ on 07/02/2018.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
