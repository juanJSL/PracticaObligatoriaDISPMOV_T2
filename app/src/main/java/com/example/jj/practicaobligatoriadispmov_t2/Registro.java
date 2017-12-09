package com.example.jj.practicaobligatoriadispmov_t2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Registro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private static int anyo, mes, dia;
    private static EditText fecha;
    private static DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fecha = findViewById(R.id.fechaET);

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
}
