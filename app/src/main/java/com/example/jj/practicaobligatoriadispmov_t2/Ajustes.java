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

        //Identifico el TextView que muestra el partido
        partido = (TextView) findViewById(R.id.partido);
        //Cambio el titulo del partido en funcion de lo que se pase como parametro
        partido.setText(partidoJugado(String.valueOf(getIntent().getExtras().get("APUESTA"))));


        //Cargo el spinner con los valores que se pueden dar
        spinnerDinero = (Spinner) findViewById(R.id.spinnerDinero);
        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, CANTIDAD_DINERO);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDinero.setAdapter(adaptador);

        //Identifico y activo el tabhost
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();//Para activarlo

        //Creo la pestaña de dinero y la de combinacion
        dinero = tabHost.newTabSpec("tabDinero");
        combinacion = tabHost.newTabSpec("tabCombinacion");

        //Cambio el titulo de cada una de las pestañas
        dinero.setIndicator(getString(R.string.dinero));
        dinero.setContent(R.id.tabDinero);
        combinacion.setIndicator(getString(R.string.combinacion));
        combinacion.setContent(R.id.tabCombinacion);

        //Añado las tabs
        tabHost.addTab(dinero);
        tabHost.addTab(combinacion);
    }

    //En funcion de lo recibido desde la mainActivity muestro un partido u otro
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

    //Vuelve a la pantalla principal
    public void volverAjustes(View v){
        finish();
    }

    //Comprueba que todos los calores sean correctos y si lo son vuelve a la pantalla principal y le envia los datos introducidos
    public void guardarAjustes(View v){
        //Compruebo que el spinner tiene un valor seleccionado
        if(spinnerDinero.getSelectedItem()==null)
            Toast.makeText(getApplicationContext(), R.string.noSpinnerSelected, Toast.LENGTH_LONG).show();
        else
        try {
            //Compruebo que los resultados introducidos son valores validos
            if (Integer.parseInt(num1.getText().toString()) < 0 || Integer.parseInt(num1.getText().toString()) > 300
                    || Integer.parseInt(num2.getText().toString()) < 0 || Integer.parseInt(num2.getText().toString()) > 300)
                Toast.makeText(getApplicationContext(), R.string.numNoValido, Toast.LENGTH_LONG).show();
            else {
                //Si son validos envio los valores a la pantalla principal
                Intent i = new Intent();
                i.putExtra("DINERO_APOSTADO", Integer.parseInt(spinnerDinero.getSelectedItem().toString()));
                i.putExtra("RESULTADO_LOCAL", Integer.parseInt(num1.getText().toString()));
                i.putExtra("RESULTADO_VISITANTE", Integer.parseInt(num2.getText().toString()));
                setResult(RESULT_OK, i);
                finish();
            }
        }catch (NumberFormatException e){
            //Mensaje de error si no se introduce ninguna combinacion
            Toast.makeText(getApplicationContext(), R.string.numNoIntroducido, Toast.LENGTH_LONG).show();
        }

    }
}
