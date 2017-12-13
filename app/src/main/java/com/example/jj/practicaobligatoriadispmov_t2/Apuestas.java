package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Apuestas extends AppCompatActivity {
    private static CheckBox futbol, baloncesto, balonmano, tenis;
    private static int numCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas);
        futbol = findViewById(R.id.futbolCheck);
        baloncesto = findViewById(R.id.baloncestoCheck);
        balonmano = findViewById(R.id.baloncestoCheck);
        tenis = findViewById(R.id.tenisCheck);
        numCheck=0;
    }

    public void aceptar(View v) {
        switch (numCheck) {
            case 0:
                new AlertDialog.Builder(this).setMessage(getString(R.string.apuestaNoSeleccionada)).show();
                break;
            case 1:
                new AlertDialog.Builder(this).setMessage(getString(R.string.apuestaGuardada)).show();
                Intent i = new Intent();
                i.putExtra("APUESTA", buscarSeleccionado());
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                new AlertDialog.Builder(this).setMessage((getString(R.string.masDeUnaApuesta))).show();
                break;
        }
    }

    public void volverApuestas(View v){
        finish();
    }

    //Cuando hago click en un checkBox segun su estado sumo o resto uno al numero de CheckBox seleccionados
    public void checkClick(View v){
        CheckBox c = (CheckBox)v;
        if(c.isChecked())
            numCheck++;
        else
            numCheck--;
    }

    //Busca entre los checkBox cual es el unico seleccionado y devuleve la cadena que le corresponde
    public static String buscarSeleccionado(){
        if(futbol.isChecked())
            return "FUTBOL";
        else if(tenis.isChecked())
            return "TENIS";
        else if(baloncesto.isChecked())
            return "BALONCESTO";
        else
            return "BALONMANO";
    }
}
