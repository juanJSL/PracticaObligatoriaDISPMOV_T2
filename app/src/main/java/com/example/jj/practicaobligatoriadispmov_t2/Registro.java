package com.example.jj.practicaobligatoriadispmov_t2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Registro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private int anyo, mes, dia;
    private EditText fecha;
    private EditText nombre;
    private EditText mail;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fecha = (EditText) findViewById(R.id.fechaET);
        nombre = (EditText) findViewById(R.id.nombreET);
        mail = (EditText) findViewById(R.id.mailET);
    }

    public void abrirCalendario(View v){
        fecha.setText("");
        final Calendar calendario = Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anyo=calendario.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(this, this,anyo, mes, dia);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int mes, int dia) {
        anyo=year;
        mes=mes+1;
        dia=dia;
        fecha.setText(dia+"-"+mes+"-"+anyo);
    }

    /*

     */
    public void validar(View v) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        if(!nombre.getText().toString().equals(getText(R.string.nombre))
                &&!nombre.getText().toString().equals("")
                &&!mail.getText().toString().equals(getText(R.string.mail))
                &&!mail.getText().toString().equals("")
                &&!fecha.getText().toString().equals(getText(R.string.fecha))
                &&!fecha.getText().toString().equals("")) {

            Date fNac = null;
            Date fActual = null;
            int edad;

            try {
                fNac = formatoFecha.parse(fecha.getText().toString());
                fActual = new Date();
                edad = (int) ((fActual.getTime() - fNac.getTime()) / 1000 / 60 / 60 / 24 / 360);

                if (edad < 18)
                    Toast.makeText(getApplicationContext(), R.string.menorDeEdad, Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(getApplicationContext(), R.string.registroCompletado, Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    i.putExtra("REGISTRADO", true);
                    i.putExtra("NOMBRE", nombre.getText().toString());
                    i.putExtra("MAIL", mail.getText().toString());
                    i.putExtra("FECHA", fecha.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(getApplicationContext(), R.string.toastFaltanDatos, Toast.LENGTH_LONG).show();
    }

    public void volver(View v){
        Intent i = new Intent();
        i.putExtra("REGISTRADO", false);
        setResult(RESULT_OK, i);
        finish();
    }
}
