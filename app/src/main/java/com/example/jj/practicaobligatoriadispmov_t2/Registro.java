package com.example.jj.practicaobligatoriadispmov_t2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private static int anyo, mes, dia;
    private static EditText fecha;
    private static EditText nombre;
    private static EditText mail;
    private static DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fecha = (EditText) findViewById(R.id.fechaET);
        nombre = (EditText) findViewById(R.id.nombreET);
        mail = (EditText) findViewById(R.id.mailET);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int mes, int dia) {
                anyo=year;
                mes=mes;
                dia=dia;
                fecha.setText(dia+"-"+mes+"-"+anyo);
            }
        };


    }

    public void abrirCalendario(View v){
        fecha.setText("");
        final Calendar calendario = Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anyo=calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener,anyo, mes, dia);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int mes, int dia) {
        anyo=year;
        mes=mes+1;
        dia=dia;
        fecha.setText(dia+"-"+mes+"-"+anyo);
    }

    public void comprobarEdad(View v) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        Date fNac = null;
        Date fActual = null;
        int edad;
        try {
            fNac = formatoFecha.parse(fecha.getText().toString());
            fActual = new Date();
            edad = (fActual)-()
            nombre.setText(""+ fNac.getTime());
        } catch (ParseException e) {
            nombre.setText("Ex");
        }



    }

/*
    //Guardar estado de la activity

    String str = nombre.getText().toString();
    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putString("nombre", nombre.getText().toString());
        guardarEstado.putString("mail", mail.getText().toString());
        guardarEstado.putString("fecha", fecha.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        nombre.setText(recEstado.getString("nombre"));
        mail.setText(recEstado.getString("mail"));
        fecha.setText(recEstado.getString("fecha"));
    }
 */

}
