package com.example.jj.practicaobligatoriadispmov_t2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by JJ on 07/02/2018.
 */

public class SettingsActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new SettingsFragment());
        ft.commit();

    }
}
