package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO = 0;
    private static boolean registrado = false;
    private static String nombre;
    private static String mail;
    private static String fNac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void abrirRegistro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivityForResult(i,CODIGO);
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO && resultCode == RESULT_OK) {
            registrado = data.getExtras().getBoolean("REGISTRADO");
            if(registrado){
                nombre = data.getExtras().getString("NOMBRE");
                mail = data.getExtras().getString("MAIL");
                fNac = data.getExtras().getString("FECHA");
                String datosRegistro= getText(R.string.datosRegistro)+
                        getString(R.string.nombre)+"\n"+nombre+"" +
                        getString(R.string.mail)+"\n"+mail+"" +
                        getString(R.string.fNac)+"\n"+fNac;
                new AlertDialog.Builder(this).setMessage(datosRegistro).show();
            }else{
                new AlertDialog.Builder(this).setMessage(R.string.registroNoCompletado).show();
            }


        }
    }

    public void abrirApuestas(View v){
        Intent i = new Intent(this, Apuestas.class);
        startActivityForResult(i,CODIGO);
    }
}
