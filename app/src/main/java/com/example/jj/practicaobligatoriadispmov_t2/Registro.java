package com.example.jj.practicaobligatoriadispmov_t2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    //Variables de calse
    private int anyo, mes, dia;//Para modificar la fecha inicial del DatePickedDialog
    private int edad;
    private EditText fecha;
    private EditText nombre;
    private EditText mail;
    private DatePickerDialog datePickerDialog;
    private Date fNac = null;
    private Date fActual = null;
    private Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Identifico los EditText del XML
        fecha = (EditText) findViewById(R.id.fechaET);
        nombre = (EditText) findViewById(R.id.nombreET);
        mail = (EditText) findViewById(R.id.mailET);
    }

    //Metodo para abrir el DatePickedDialog
    public void abrirCalendario(View v){
        fecha.setText("");
        //Obtengo la fecha actual
        calendario = Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anyo=calendario.get(Calendar.YEAR);

        //Creo el DatePickerDialog con la fecha actual y le indico que el escuchador es la propia clase
        datePickerDialog = new DatePickerDialog(this, this,anyo, mes, dia);
        //Muestro el DatePickerDialog
        datePickerDialog.show();
    }


    //Metodo que modifica el campo fecha segun el seleccionado en el DatePickedDialog
    @Override
    public void onDateSet(DatePicker view, int year, int mes, int dia) {
        this.anyo=year;
        this.mes=mes+1;
        this.dia=dia;
        fecha.setText(this.dia+"-"+this.mes+"-"+this.anyo);
    }

    //Metodo que comprueba que todos los datos han sido introducidos
    public void validar(View v) {
        //Compruebo que se hayan insertado todos los datos
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        if(!nombre.getText().toString().equals(getText(R.string.nombre))
                &&!nombre.getText().toString().equals("")
                &&!mail.getText().toString().equals(getText(R.string.mail))
                &&!mail.getText().toString().equals("")
                &&!fecha.getText().toString().equals(getText(R.string.fecha))
                &&!fecha.getText().toString().equals("")) {
        //Si se han introducido todos los datos compruebo la edad
            fNac = null;
            fActual = null;

            try {
                fNac = formatoFecha.parse(fecha.getText().toString());
                fActual = new Date();
                edad = (int) ((fActual.getTime() - fNac.getTime()) / 1000 / 60 / 60 / 24 / 360);//Calculo la edad
                //Compruebo si es mayor de edad
                if (edad < 18)
                    //Error si es menor de edad
                    Toast.makeText(getApplicationContext(), R.string.menorDeEdad, Toast.LENGTH_LONG).show();
                else {//Si todo es correcto devuelve los datos introcucidos a la pantalla principal.
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
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();//Si no se ha introducido ninguna fecha
            }
        }else
            Toast.makeText(getApplicationContext(), R.string.toastFaltanDatos, Toast.LENGTH_LONG).show();
    }

    //Metodo que vuelve a la pantalla principal sin guardar los datos introducidos
    public void volver(View v){
        Intent i = new Intent();
        i.putExtra("REGISTRADO", false);
        setResult(RESULT_OK, i);
        finish();
    }

    //Guardar el estado
    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putInt("edad", edad);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        edad=recEstado.getInt("edad");
    }
}
