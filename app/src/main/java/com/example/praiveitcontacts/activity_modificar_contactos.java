package com.example.praiveitcontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.praiveitcontacts.Models.Contactos;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class activity_modificar_contactos extends AppCompatActivity {

    private  int idContacto;
    Contactos contacto;

    private EditText Nombre,Apellido, Email, Telefono, TelefonoFijo, Genero, Direccion, RepeatContrasena;
    private RadioButton Hombre, Mujer;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contactos);
        

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idContacto = recibirIdUsuario.getInt("idCharla");
        }

        System.out.println("idCharla editar: " + idContacto);

        DatabaseHelper db = new DatabaseHelper(activity_modificar_contactos.this);
        contacto = db.getContactoModelById(idContacto);

        Log.i("Modificar una charla", contacto.getNombre());

        Nombre = findViewById(R.id.inputNombreContactoEditar);
        Apellido = findViewById(R.id.inputApellidoContactoEditar);
        Email = findViewById(R.id.inputEmailContactoEditar);
        Hombre = findViewById(R.id.radio_button_HombreContactoEditar);
        Mujer = findViewById(R.id.radio_button_MujerContactoEditar);
        Telefono = findViewById(R.id.inputTelefonoContactoEditar);
        TelefonoFijo = findViewById(R.id.inputTelefonoFijoContactoEditar);
        Direccion = findViewById(R.id.inputDireccionContactoEditar);
        radioGroup = findViewById(R.id.radioGroupGeneroContactoEditar);

        //
        Nombre.setText(contacto.getNombre());
        Apellido.setText(contacto.getApellido());
        Email.setText(contacto.getCorreo());
        Telefono.setText(contacto.getTelefono());
        TelefonoFijo.setText(contacto.getTelefonoFijo());
        Direccion.setText(contacto.getDireccion());

        if(contacto.getGenero().equals("H")){
            Hombre.setChecked(true);
            Mujer.setChecked(false);
        }else {
            Mujer.setChecked(true);
            Hombre.setChecked(false);
        }

    }

    public void editarContacto(View v){
        List<String> listErrores = new ArrayList<String>();


        if(Nombre.getText().length() < 1){
            Nombre.setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Nombre vacío");
        }else {
            Nombre.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if(Apellido.getText().length() < 1){
            Apellido.setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Apellido vacío");
        }else {
            Apellido.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if(Email.getText().length() < 1){
            Email.setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Email vacío");
        }else {
            Email.setBackgroundColor(Color.parseColor("#ffffff"));
        }


        if(Hombre.isChecked() == false && Mujer.isChecked() == false){
            // Genero vacio
            findViewById(R.id.fr_layout_editar_contacto).setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Genero vacío");
        }else {
            findViewById(R.id.fr_layout_editar_contacto).setBackgroundColor(Color.parseColor("#ffffff"));
        }

        //Insertar Genero

        if(Mujer.isChecked()){
            contacto.setGenero("M");
        }else {
            contacto.setGenero("H");
            // Hombre.setChecked(true);
        }


        if(listErrores.size() > 0){
            // Datos incompletos
            AlertDialog.Builder builder = new AlertDialog.Builder(activity_modificar_contactos.this);
            builder.setMessage("Por favor llene todos los campos! " + listErrores.get(0) + " datos vacíos" )
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            builder.show();
        }else {

            // Las cosas andan bien

            contacto.setNombre(Nombre.getText().toString());
            contacto.setApellido(Apellido.getText().toString());
            contacto.setCorreo(Email.getText().toString());
            contacto.setTelefono(Telefono.getText().toString());
            contacto.setTelefonoFijo(TelefonoFijo.getText().toString());
            contacto.setDireccion(Direccion.getText().toString());
            contacto.setId(idContacto);



            // Imprimir el genero
            System.out.println(contacto.getId());
            System.out.println(contacto.getNombre());
            System.out.println(contacto.getApellido());
            System.out.println(contacto.getCorreo());
            System.out.println(contacto.getGenero());
            System.out.println(contacto.getTelefono());
            System.out.println(contacto.getTelefonoFijo());
            System.out.println(contacto.getDireccion());
            System.out.println(contacto.getIdUsuarioMaster());



            DatabaseHelper db = new DatabaseHelper(activity_modificar_contactos.this);

            boolean resultado = db.EditarContacto(contacto);

            if(resultado){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Su Contacto ha sido actualizado con éxito.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                            }
                        });

                builder.show();

            }else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha podido actualizar su usuario", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.parseColor("#63a4ff"));
                snackbar.show();
            }

        }

    }

    public void eliminarContacto(View v){
        DatabaseHelper db = new DatabaseHelper(activity_modificar_contactos.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea eliminar el contacto de " + contacto.getNombre() + "!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean resultado = db.deleteContactoById(idContacto);

                        if(resultado){
                            Snackbar snackbarSuc= Snackbar.make(findViewById(android.R.id.content), "Se ha eliminado el contacto de " + contacto.getNombre() + "!", Snackbar.LENGTH_LONG);
                            snackbarSuc.setBackgroundTint(Color.parseColor("#cc3300"));
                            snackbarSuc.show();
                            onBackPressed();
                        }else {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha podido eliminar el contacto de" + contacto.getNombre() + "!", Snackbar.LENGTH_LONG);
                            snackbar.setBackgroundTint(Color.parseColor("#ff9966"));
                            snackbar.show();
                        }
                    }
                });
        builder.show();
    }
}