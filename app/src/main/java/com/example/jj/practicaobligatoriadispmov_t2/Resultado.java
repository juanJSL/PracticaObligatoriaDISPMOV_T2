package com.example.jj.practicaobligatoriadispmov_t2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    private final String consulta =
            "SELECT "+
                    BDHelper.COL_ID+","+
                    BDHelper.COL_T1+","+
                    BDHelper.COL_T2+","+
                    BDHelper.COL_R1+","+
                    BDHelper.COL_R2+
                    " FROM "+ BDHelper.TABLE_NAME;

    private SQLiteDatabase database;
    private Cursor cursor;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Creo la instancia de la BD
        database = new BDHelper(this).getReadableDatabase();
        //Ejecuto la consulta y la almaceno en el cursor
        cursor = database.rawQuery(consulta, null);

        /*Muevo el cursor al primer registro recuperado y lo recorro, en cada vuelta
        en cada vuelta llamo al metodo anyadirLinea*/
        if(cursor.moveToFirst()){
            do{//Mientras haya un siguiente registro llamo al metodo anyadirFila
                anyadirFila(cursor);
            }while (cursor.moveToNext());
        }

    }

    /**
     * Metodo que va añadiendo filas a la tabla
     * @param cursor-->cursor que contiene un registro para añadir a la tabla
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void anyadirFila(Cursor cursor){

        TableLayout tabla = (TableLayout) findViewById(R.id.tabla);
        TableRow fila = new TableRow(this);

        TextView id = new TextView(this);
        TextView team1 = new TextView(this);
        TextView team2 = new TextView(this);
        TextView result1 = new TextView(this);
        TextView result2 = new TextView(this);


        id.setText(cursor.getInt(0)+"");
        id.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
        id.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(id);

        team1.setText(cursor.getString(1));
        team1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
        team1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(team1);

        team2.setText(cursor.getString(2));
        team2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
        team2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(team2);


        result1.setText(cursor.getInt(3)+"");
        result1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
        result1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(result1);

        result2.setText(cursor.getInt(4)+"");
        result2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
        result2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        fila.addView(result2);

        tabla.addView(fila);



    }

}
