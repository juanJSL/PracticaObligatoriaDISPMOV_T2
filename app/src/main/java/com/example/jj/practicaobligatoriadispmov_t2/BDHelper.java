package com.example.jj.practicaobligatoriadispmov_t2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JJ on 07/02/2018.
 */

public class BDHelper extends SQLiteOpenHelper {
    //CONSTANTES
    //Nombre de la tabla
    public static final String TABLE_NAME = "resultados";
    //Columnas para la BD
    public static final String COL_ID = "id_encuentro";
    public static final String COL_T1 = "team1";
    public static final String COL_T2 = "team2";
    public static final String COL_R1 = "result1";
    public static final String COL_R2 = "result2";
    public static final String TIPO_ENCUENTRO = "tipo_encuentro";
    public static final int FUTBOL = 1;
    public static final int BALONCESTO = 2;
    public static final int TENIS = 3;
    public static final int BALONMANO = 4;


    //}
    //Instruccion sql para crear la tabla
    public static final String CREAR_TABLA =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_T1 + " TEXT," +
                    COL_T2 + " TEXT," +
                    COL_R1 + " INTEGER," +
                    COL_R2 + " INTEGER," +
                    TIPO_ENCUENTRO + " INTEGER)";

    //Instruccion sql para borrar la tabla
    public static final String BORRAR_TABLA =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    //Nombre para la base de datos
    public static final String DATABASE_NAME = "Resultados.db";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Ejecuto la sentencia para crear la BD
        db.execSQL(CREAR_TABLA);
        //Creo las sentencias para insertar datos
        String insertEncuentrosFutbol =
                "INSERT INTO " + TABLE_NAME + " VALUES " +
                        "(1, 'Real Madrid', 'FC Barcelona',4,0," + FUTBOL + ")," +
                        "(2, 'Valladolid' , 'Numanncia',0,0," + FUTBOL + ")," +
                        "(3, 'Betis', 'Sevilla', 2,3," + FUTBOL + ")";

        String insertEncuentrosBaloncesto =
                "INSERT INTO " + TABLE_NAME + " VALUES " +
                        "(4, 'Unicaja', 'Estudiantes',63,49," + BALONCESTO + ")," +
                        "(5, 'Baskonia' , 'Delteco',72,53," + BALONCESTO + ")," +
                        "(6, 'Barcelona lassa', 'Tenerife', 58,69," + BALONCESTO + ")";

        String insertarEncuentrosTenis =
                "INSERT INTO " + TABLE_NAME + " VALUES " +
                        "(7, 'Nadal', 'Federer',3,2," + TENIS + ")," +
                        "(8, 'Cilic' , 'Almagro',1,3," + TENIS + ")," +
                        "(9, 'Del Potro', 'Dimitrov', 3,4," + TENIS + ")";

        String insertarEncuentrosBalonamano =
                "INSERT INTO " + TABLE_NAME + " VALUES " +
                        "(10, 'Guadalajara', 'Huesca',32,31," + BALONCESTO + ")," +
                        "(11, 'Zamora' , 'Abanca',30,25," + BALONCESTO + ")," +
                        "(12, 'Helvetia', 'Benidorm', 21,30," + BALONCESTO + ")";

        //Ejecuto las sentencias de insercion de datos
        db.execSQL(insertEncuentrosFutbol);
        db.execSQL(insertEncuentrosBaloncesto);
        db.execSQL(insertarEncuentrosTenis);
        db.execSQL(insertarEncuentrosBalonamano);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BORRAR_TABLA);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
