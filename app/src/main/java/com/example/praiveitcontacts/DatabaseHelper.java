package com.example.praiveitcontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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




}
