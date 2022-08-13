package com.example.praiveitcontacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.praiveitcontacts.Models.Contactos;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //1.1 - Contructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "PraivateContactsDB", null, 1);
    }

    //1.2 - Crear tablas en db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsuarios = "" +
                "                  CREATE TABLE Usuarios ( " +
                "                ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "                Nombre TEXT NOT NULL," +
                "                Apellido TEXT NOT NULL," +
                "                Genero TEXT NOT NULL, " +
                "                Usuario TEXT NOT NULL UNIQUE, " +
                "                Correo TEXT NOT NULL," +
                "                Password TEXT NOT NULL" +
                "                )";

        String createTableContactos = "" +
                "                CREATE TABLE Contactos ( " +
                "                ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "                Nombre TEXT NOT NULL," +
                "                Apellido TEXT NOT NULL," +
                "                Genero TEXT NOT NULL, " +
                "                Correo TEXT NOT NULL UNIQUE, " +
                "                Telefono TEXT NOT NULL," +
                "                TelefonoFijo TEXT NOT NULL," +
                "                Direccion TEXT NOT NULL," +
                "                IdUserMaster INTEGER NOT NULL" +
                "                )";

        //Ejecutar consulta SQL
        db.execSQL(createTableUsuarios);
        db.execSQL(createTableContactos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Contactos> getAllContactos(){
        List<Contactos> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM Contactos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                Contactos ContactosModel = new Contactos();
                ContactosModel.setId(cursor.getInt(0));
                ContactosModel.setNombre(cursor.getString(1));
                ContactosModel.setApellido(cursor.getString(2));
                ContactosModel.setGenero(cursor.getString(3));
                ContactosModel.setCorreo(cursor.getString(4));
                ContactosModel.setTelefono(cursor.getString(5));
                ContactosModel.setTelefonoFijo(cursor.getString(6));
                ContactosModel.setDireccion(cursor.getString(7));
                ContactosModel.setIdUsuarioMaster(cursor.getInt(8));

                returnList.add(ContactosModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public void OpenDB(){
        List<Contactos> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM Contactos";
        SQLiteDatabase db = this.getWritableDatabase();

        db.close();

    }




}
