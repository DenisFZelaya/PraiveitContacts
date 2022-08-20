package com.example.praiveitcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.praiveitcontacts.Models.Contactos;
import com.example.praiveitcontacts.Models.Login;
import com.example.praiveitcontacts.Models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //1.1 - Contructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "PraivateContactsDB_DEV_umh", null, 1);
    }

    //1.2 - Crear tablas en db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsuarios = "" +
                "                  CREATE TABLE Usuarios ( " +
                "                ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                Nombre TEXT NOT NULL," +
                "                Apellido TEXT NOT NULL," +
                "                Genero TEXT NOT NULL, " +
                "                Usuario TEXT NOT NULL UNIQUE, " +
                "                Correo TEXT NOT NULL," +
                "                Password TEXT NOT NULL" +
                "                )";

        String createTableContactos = "" +
                "                CREATE TABLE Contactos ( " +
                "                ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
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


    public void OpenDB(){
        List<Contactos> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM Contactos";
        SQLiteDatabase db = this.getWritableDatabase();

        // db.close();
    }

    public Usuario Login(Login login){
        Usuario usuario = new Usuario();
        String queryString = "SELECT * FROM Usuarios WHERE Usuario = '" + login.getUsuario() + "' AND Password = '" + login.getContrasena() + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                usuario.setId(cursor.getInt(0));

            }while (cursor.moveToNext());
        } else {
            usuario.setId(0);
        }
        // cursor.close();
        // db.close();

        return usuario;

    }

    //Insertar Usuario
    public boolean InsertarUsuario(Usuario usuariosModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ID", (Integer) null);
        cv.put("Nombre", usuariosModel.getNombre());
        cv.put("Apellido", usuariosModel.getApellido());
        cv.put("Genero", usuariosModel.getGenero());
        cv.put("Usuario", usuariosModel.getUsuario());
        cv.put("Correo", usuariosModel.getCorreo());
        cv.put("Password", usuariosModel.getContrasena());

        long insert = db.insert("Usuarios", null, cv);
        if(insert == -1){
            return  false;
        }else{
            return  true;
        }
    }

    //Crear Contactos
    public boolean InsertarContacto(Contactos contactosModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Id", (Integer) null);
        cv.put("Nombre", contactosModel.getNombre());
        cv.put("Apellido", contactosModel.getApellido());
        cv.put("Genero", contactosModel.getGenero());
        cv.put("Correo", contactosModel.getCorreo());
        cv.put("Telefono", contactosModel.getTelefono());
        cv.put("TelefonoFijo", contactosModel.getTelefonoFijo());
        cv.put("Direccion", contactosModel.getDireccion());
        cv.put("IdUserMaster",contactosModel.getIdUsuarioMaster());

        long insert = db.insert("Contactos", null, cv);
        if(insert == -1){
            return  false;
        }else{
            return  true;
        }
    }

    //Editar Contactos
    public boolean EditarContacto(Contactos EditarContacto){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE Contactos " +
                "SET Nombre = '" + EditarContacto.getNombre() + "', "+
                " Apellido = '" + EditarContacto.getApellido() + "', "+
                " Genero = '" + EditarContacto.getGenero() + "', "+
                " Correo = '" + EditarContacto.getCorreo() + "', "+
                " Telefono = '" + EditarContacto.getTelefono() + "' "+
                " TelefonoFijo = '" + EditarContacto.getTelefonoFijo() + "' "+
                " Direccion = '" + EditarContacto.getDireccion() + "' "+
                " IdUserMaster = '" + EditarContacto.getIdUsuarioMaster() + "' "+
                " WHERE Id = " + EditarContacto.getId() + ";";

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return  false;
        }else  {
            return  true;
        }
    }

    //Eliminar Contactos
    public boolean deleteContactoById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM Contactos WHERE Id = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return  false;
        }else  {
            return  true;
        }
    }

    //Obtener Listado de Contactos
    public List<Contactos> getAllContactos(int id){
        List<Contactos> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM Contactos WHERE idUserMaster =" + id;
        System.out.println(queryString);
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
                System.out.println(ContactosModel.getNombre());
                ContactosModel.setIdUsuarioMaster(cursor.getInt(8));


                System.out.println("MisUsuarios: " + ContactosModel.getNombre());
                returnList.add(ContactosModel);
            }while (cursor.moveToNext());
        } else {

        }
        //cursor.close();
        //db.close();
        return returnList;
    };

    //Traer contacto por ID
    public Contactos getContactoModelById(int id){
        Contactos contactoSelected = new Contactos();

        String queryString = "SELECT * FROM Contactos WHERE Id = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                contactoSelected.setId(cursor.getInt(0));
                contactoSelected.setNombre(cursor.getString(1));
                contactoSelected.setApellido(cursor.getString(2));
                contactoSelected.setGenero(cursor.getString(3));
                contactoSelected.setCorreo(cursor.getString(4));
                contactoSelected.setTelefono(cursor.getString(5));
                contactoSelected.setTelefonoFijo(cursor.getString(6));
                contactoSelected.setDireccion(cursor.getString(7));
                contactoSelected.setIdUsuarioMaster(cursor.getInt(8));
            }while (cursor.moveToNext());
        } else {
            contactoSelected.setNombre("null");
        }
        cursor.close();
        db.close();
        return contactoSelected;
    };

    //Traer contacto por ID
    public Usuario getUsuarioById(int id){
        Usuario usuarioSeleccionado = new Usuario();

        String queryString = "SELECT * FROM Usuarios WHERE ID = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                usuarioSeleccionado.setId(cursor.getInt(0));
                usuarioSeleccionado.setNombre(cursor.getString(1));
                usuarioSeleccionado.setApellido(cursor.getString(2));
                usuarioSeleccionado.setGenero(cursor.getString(3));
                usuarioSeleccionado.setUsuario(cursor.getString(4));
                usuarioSeleccionado.setCorreo(cursor.getString(5));
                usuarioSeleccionado.setContrasena(cursor.getString(6));
            }while (cursor.moveToNext());
        } else {
            usuarioSeleccionado.setNombre("null");
        }
        cursor.close();
        db.close();
        return usuarioSeleccionado;
    };

}
