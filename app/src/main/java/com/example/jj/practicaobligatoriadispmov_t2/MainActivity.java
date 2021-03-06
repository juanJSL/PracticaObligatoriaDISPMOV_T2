package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Constantes para reconocer cada una de las actividades
    private final int CODIGO_REGISTRO = 0;
    private final int CODIGO_APUESTAS = 1;
    private final int CODIGO_AJUSTES = 2;

    //Variables de clase
    //private boolean registrado;
   // private String nombre;
    //private String mail;
    private String fNac;
    private String apuestaSeleccionada="";
    private int dineroApostado;
    private int resultLocal;
    private int resultVisitante;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cargo los valores por defecto de las preferencias
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        //Compruebo si el usuario esta conectado
        isConectado();

    }

    //Metodo para abrir la pantalla de registro
    public void abrirRegistro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivityForResult(i,CODIGO_REGISTRO);
    }

    //Metodo para abrir la pantalla de apuestas
    public void abrirApuestas(View v){
        //Compruebo que el usuario se haya registrado
        if(isConectado()) {
            Intent i = new Intent(this, Apuestas.class);
            startActivityForResult(i, CODIGO_APUESTAS);
        }else
            Toast.makeText(getApplicationContext(), R.string.noRegistrado, Toast.LENGTH_LONG).show();
    }

    //Metodo para abrir la pantalla de ajustes
    public void abrirAjustes(View v){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Compruebo que el usuario haya realizado alguna apuesta

        if(preferences.getString("apuestaDefecto","fallo").equals("fallo"))
            Toast.makeText(getApplicationContext(), R.string.noApostado, Toast.LENGTH_LONG).show();
        else {
            Intent i = new Intent(this, Ajustes.class);
            Bundle bundle = new Bundle();
            bundle.putString("APUESTA", preferences.getString("apuestaDefecto","fallo"));
            i.putExtras(bundle);
            startActivityForResult(i, CODIGO_AJUSTES);
        }
    }


    /******************************************
     *********ABRIR PANTALLA RESULTADO*********
     ******************************************/
    public void abrirResultado(View v){
        Intent i = new Intent(this, Resultado.class);
        startActivity(i);
    }

    //Obtengo los datos introducidos por el usuario uso un switch case
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CODIGO_REGISTRO:
                if (resultCode == RESULT_OK) {

                    //registrado = data.getExtras().getBoolean("REGISTRADO");

                    /******************************************************************************************************************
                    ****Si el usuario se ha registrado guardo los valores que se reciben de la activity Registro en las preferencias***
                    *******************************************************************************************************************/
                    if (isConectado()) {
                        //Obtengo las preferencias
                        preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        //Obtengo un editor para las preferencias
                        SharedPreferences.Editor editor = preferences.edit();
                        //Edito las preferencias
                        editor.putString("nombrePref", data.getExtras().getString("NOMBRE"));
                        editor.putString("mailPref", data.getExtras().getString("MAIL"));
                        editor.putBoolean("conectado", data.getExtras().getBoolean("REGISTRADO"));
                        editor.putString("apuestaDefecto", "FUTBOL");
                        editor.commit();


                        fNac = data.getExtras().getString("FECHA");
                        String datosRegistro = getText(R.string.datosRegistro) + "\n"+
                                getString(R.string.nombre) + ": " + data.getExtras().getString("NOMBRE") + "\n" +
                                getString(R.string.mail) + ": " + data.getExtras().getString("MAIL") + "\n" +
                                getString(R.string.fNac) + ": " + fNac;
                        new AlertDialog.Builder(this).setMessage(datosRegistro).show();
                    } else
                        new AlertDialog.Builder(this).setMessage(R.string.registroNoCompletado).show();
                }
                break;

            case CODIGO_APUESTAS:
                //Si codigo de resultado es OK almaceno la apuesta seleccionada y muestro un mensaje por pantalla
                if (resultCode == RESULT_OK) {
                    /************************************************
                     *****Guardo la apuesta hecho por el usuario*****
                     ************************************************/
                    //Obtengo las preferencias
                    preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    //Obtengo un editor para las preferencias
                    SharedPreferences.Editor editor = preferences.edit();
                    //Edito las preferencias
                    editor.putString("apuestaDefecto", data.getExtras().getString("APUESTA"));
                    editor.commit();




                    apuestaSeleccionada = data.getExtras().getString("APUESTA");
                    new AlertDialog.Builder(this).setMessage(getString(R.string.apuestaSeleccionada) + " " + data.getExtras().getString("APUESTA")).show();
                }
                break;
            case CODIGO_AJUSTES:
                //Si el codigo de resultado es OK almaceno los valores recibidos por la actividad ajustess
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

    //Metodo para las acciones de las opciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.acercaDe) {
            //Iniciar acerca de
            Intent i = new Intent(this, AcercaDe.class);
            startActivity(i);
            return true;
        }else if(id == R.id.ayuda){
            //Iniciar ayuda
            Intent i = new Intent(this, Ayuda.class);
            startActivity(i);
            return true;
            /*************************************
             ****** OPCION PARA LAS SETTINGS******
             *************************************/
        }else{
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
    }

    //Guardar el estado
    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        //guardarEstado.putString("nombre", nombre);
        //guardarEstado.putString("mail", mail);
        guardarEstado.putString("fnac", fNac);
        guardarEstado.putString("apuestaSeleccionada", apuestaSeleccionada);
        //guardarEstado.putBoolean("registrado",registrado);
        guardarEstado.putInt("dineroApostado", dineroApostado);
        guardarEstado.putInt("resultLocal", resultLocal);
        guardarEstado.putInt("resultVisitante", resultVisitante);
    }

    //Recuperar el estado
    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        //nombre = recEstado.getString("nombre");
        //mail = recEstado.getString("mail");
        fNac = recEstado.getString("fnac");
        apuestaSeleccionada = recEstado.getString("apuestaSeleccionada");
        //registrado = recEstado.getBoolean("registrado");
        dineroApostado = recEstado.getInt("dineroApostado");
        resultLocal = recEstado.getInt("resultLocal");
        resultVisitante = recEstado.getInt("resultVisitante");
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    public boolean isConectado(){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("conectado", false);
    }


}
