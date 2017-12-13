package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String FUTBOL = "FUTBOL";
    private final String TENIS = "TENIS";
    private final String BALONCESTO = "BALONCESTO";
    private final String BALONMANO = "BALONMANO";
    private final int CODIGO_REGISTRO = 0;
    private final int CODIGO_APUESTAS = 1;
    private final int CODIGO_AJUSTES = 2;
    private boolean registrado = false;
    private boolean apuestaSleccionada = false;
    private String nombre;
    private String mail;
    private String fNac;
    private String apuestaSeleccionada="";
    private int dineroApostado;
    private int resultLocal;
    private int resultVisitante;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void abrirRegistro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivityForResult(i,CODIGO_REGISTRO);
    }

    public void abrirApuestas(View v){
        if(registrado) {
            Intent i = new Intent(this, Apuestas.class);
            startActivityForResult(i, CODIGO_APUESTAS);
        }else
            Toast.makeText(getApplicationContext(), R.string.noRegistrado, Toast.LENGTH_LONG).show();
    }

    public void abrirAjustes(View v){
        if(apuestaSeleccionada.equals(""))
            Toast.makeText(getApplicationContext(), R.string.noApostado, Toast.LENGTH_LONG).show();
        else {
            Intent i = new Intent(this, Ajustes.class);
            Bundle bundle = new Bundle();
            bundle.putString("APUESTA", apuestaSeleccionada);
            i.putExtras(bundle);
            startActivityForResult(i, CODIGO_AJUSTES);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODIGO_REGISTRO:
                if (resultCode == RESULT_OK) {
                    registrado = data.getExtras().getBoolean("REGISTRADO");
                    if (registrado) {
                        nombre = data.getExtras().getString("NOMBRE");
                        mail = data.getExtras().getString("MAIL");
                        fNac = data.getExtras().getString("FECHA");
                        String datosRegistro = getText(R.string.datosRegistro) +
                                getString(R.string.nombre) + "\n" + nombre + ": " +
                                getString(R.string.mail) + "\n" + mail + ": " +
                                getString(R.string.fNac) + ": " + fNac;
                        new AlertDialog.Builder(this).setMessage(datosRegistro).show();
                    } else {
                        new AlertDialog.Builder(this).setMessage(R.string.registroNoCompletado).show();
                    }
                }
                break;

            case CODIGO_APUESTAS:
                if (resultCode == RESULT_OK) {
                    apuestaSeleccionada = data.getExtras().getString("APUESTA");
                    new AlertDialog.Builder(this).setMessage(getString(R.string.apuestaSeleccionada) + " " + apuestaSeleccionada).show();

                }
                break;
            case CODIGO_AJUSTES:
                if (resultCode == RESULT_OK) {
                    dineroApostado = data.getExtras().getInt("DINERO_APOSTADO");
                    resultLocal = data.getExtras().getInt("RESULTADO_LOCAL");
                    resultVisitante = data.getExtras().getInt("RESULTADO_VISITANTE");
                }
                break;

        }
    }



    //Creacion del menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.acercaDe) {
            Intent i = new Intent(this, AcercaDe.class);
            startActivity(i);
            return true;
        }else{
            Intent i = new Intent(this, Ayuda.class);
            startActivity(i);
            return true;
        }


        //return super.onOptionsItemSelected(item);
    }
}
