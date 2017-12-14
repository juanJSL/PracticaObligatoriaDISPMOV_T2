package com.example.jj.practicaobligatoriadispmov_t2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }

    //Vuelve a la pantalla principal
    public void volverAyuda(View v){
        finish();
    }
}
