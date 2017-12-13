package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {
    private TabHost tabHost;
    private TabHost.TabSpec dinero;
    private TabHost.TabSpec combinacion;
    private Spinner spinnerDinero;
    private final Integer[] CANTIDAD_DINERO = {1, 2, 5, 10};
    private TextView partido;
    private EditText num1;
    private EditText num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        //Identifico los EditText
        num1 = (EditText) findViewById(R.id.numero1);
        num2 = (EditText) findViewById(R.id.numero2);

        partido = (TextView) findViewById(R.id.partido);
        //Cambio el titulo del partido en funcion de lo que se pase como parametro
        partido.setText(partidoJugado(String.valueOf(getIntent().getExtras().get("APUESTA"))));


        //Cargo el spinner con los valores que se pueden dar
        spinnerDinero = (Spinner) findViewById(R.id.spinnerDinero);
        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, CANTIDAD_DINERO);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDinero.setAdapter(adaptador);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();//Para activarlo

        dinero = tabHost.newTabSpec("tabDinero");
        combinacion = tabHost.newTabSpec("tabCombinacion");

        dinero.setIndicator(getString(R.string.dinero));
        dinero.setContent(R.id.tabDinero);
        combinacion.setIndicator(getString(R.string.combinacion));
        combinacion.setContent(R.id.tabCombinacion);

        //añadir las tabs
        tabHost.addTab(dinero);
        tabHost.addTab(combinacion);
    }

    private String partidoJugado(String tipoApuesta) {
        String str="";
        switch (tipoApuesta) {
            case ("FUTBOL"):
                str = "Real Madrid - Barcelona";
            break;
            case ("TENIS"):
                str = "Nadal - Ferrer";
            break;
            case ("BALONCESTO"):
                str = "Estudiantes - Barcelona";
            break;
            case ("BALONMANO"):
                str = "Naturhouse - Granoller";
            break;
        }
        return str;
    }

    public void volverAjustes(View v){
        finish();
    }

    public void guardarAjustes(View v){
        if(spinnerDinero.getSelectedItem()==null)
            Toast.makeText(getApplicationContext(), R.string.noSpinnerSelected, Toast.LENGTH_LONG).show();
        else
        try {
            if (Integer.parseInt(num1.getText().toString()) < 0 || Integer.parseInt(num1.getText().toString()) > 300
                    || Integer.parseInt(num2.getText().toString()) < 0 || Integer.parseInt(num2.getText().toString()) > 300)
                Toast.makeText(getApplicationContext(), R.string.numNoValido, Toast.LENGTH_LONG).show();
            else {
                Intent i = new Intent();
                i.putExtra("DINERO_APOSTADO", Integer.parseInt(spinnerDinero.getSelectedItem().toString()));
                i.putExtra("RESULTADO_LOCAL", Integer.parseInt(num1.getText().toString()));
                i.putExtra("RESULTADO_VISITANTE", Integer.parseInt(num2.getText().toString()));
                setResult(RESULT_OK, i);
                finish();
            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), R.string.numNoIntroducido, Toast.LENGTH_LONG).show();
        }

    }
}
