package com.example.yanirayanes.navbar_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.yanirayanes.navbar_sqlite.objeto.persona;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_usuarios";
    public static final String TABLA_USUARIOS = "Persona";
    public static final String CAMPO_ID = "carnet";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_NOTA = "nota";
    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIOS+"("+CAMPO_ID+" TEXT,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_NOTA+" TEXT)";

    public static DBhelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBhelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBhelper(ctx.getApplicationContext());
        }return myDB;
    }

    public DBhelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    public boolean add(persona p){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID,p.getCarnet());
        values.put(CAMPO_NOMBRE,p.getNombre());
        values.put(CAMPO_NOTA,p.getNota());
        db.insert(TABLA_USUARIOS, null, values);
        Toast.makeText(context,"Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList getUsers(){
        this.db = this.getWritableDatabase();
        SQLiteDatabase sql = this.db;
        ArrayList persona = new ArrayList();
        Cursor cursor = this.db != null ? this.db.rawQuery("SELECT * FROM Persona",(String[])null) : null;
        if(cursor != null && cursor.moveToFirst()){
            do{
                String carnet = cursor.getString(cursor.getColumnIndex("carnet"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                persona.add(new persona(carnet,nombre,cursor.getInt(cursor.getColumnIndex("nota"))));
            }while(cursor.moveToNext());
        }

        if(cursor != null){
            cursor.close();
        }

        if(this.db != null){
            sql.close();
        }

        return persona;
    }

    public persona findUser(String carnet){
        persona p;
        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOMBRE, CAMPO_NOTA};

        try{
            Cursor cursor = db.query(TABLA_USUARIOS,campos,CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            p = new persona(carnet,cursor.getString(cursor.getColumnIndex(CAMPO_NOMBRE)), cursor.getInt(cursor.getColumnIndex(CAMPO_NOTA)));
        }catch (Exception e){
            p = null;
        }
        return p;
    }

    public boolean editUser(persona p){
        String[] parametros = {p.getCarnet()};
        String[] campos = {CAMPO_NOMBRE, CAMPO_NOTA};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIOS,values,CAMPO_ID+"=?",parametros);
        Toast.makeText(context, "Usuario actualizado con exito",Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean deleteUser(String carnet){
        String[] parametros = {carnet};
        db.delete(TABLA_USUARIOS,CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Usuario eliminado",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOMBRE);
        onCreate(db);
    }

    public static final class Companion {
    }
}
