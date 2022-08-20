package com.example.praiveitcontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.praiveitcontacts.Models.Contactos;
import com.example.praiveitcontacts.Models.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class activity_crear_contacto extends AppCompatActivity {

    private  int idUsuario;
    private EditText Nombre,Apellido, Email, Telefono, TelefonoFijo, Genero, Direccion, RepeatContrasena;
    private RadioButton Hombre, Mujer;
    private Button btnNuevoContacto;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);
        getSupportActionBar().hide();

        //recibir un ID de Usuario
        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idUsuario = recibirIdUsuario.getInt("idUsuario");
        }
        System.out.println("idUsuario crear contacto: " + idUsuario);


        //codigo agregado por Aaron
        Nombre = findViewById(R.id.inputNombreContacto);
        Apellido = findViewById(R.id.inputApellidoContacto);

        Email = findViewById(R.id.inputEmailContacto);
        Hombre = findViewById(R.id.radio_button_HombreContacto);
        Mujer = findViewById(R.id.radio_button_MujerContacto);
        Telefono = findViewById(R.id.inputTelefonoContacto);
        TelefonoFijo = findViewById(R.id.inputTelefonoFijo);
        Direccion = findViewById(R.id.inputDireccion);
        radioGroup = findViewById(R.id.radioGroupGeneroRegistro);

    }

    public void crearContactoNuevo(View v){
        Contactos nuevoContacto = new Contactos();

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
            findViewById(R.id.fr_layout_contacto).setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Genero vacío");
        }else {
            findViewById(R.id.fr_layout_contacto).setBackgroundColor(Color.parseColor("#ffffff"));
        }

        //Insertar Genero

        if(Mujer.isChecked()){
            nuevoContacto.setGenero("M");
        }else {
            nuevoContacto.setGenero("H");
            // Hombre.setChecked(true);
        }


        String contactoShow =  "";
        String contactoName = "";

        if(Apellido.getText().length() > 0){
            contactoShow = Apellido.getText().toString();
        }

        if(Nombre.getText().length() > 0){
            contactoShow = Nombre.getText().charAt(0) + "";
        }


       //User.setText(contactoShow.toLowerCase(Locale.ROOT) + "" + contactoName.toLowerCase(Locale.ROOT));

       // if(User.getText().length() < 1 ){
            //
          //  listErrores.add("Contacto vacío");
       // }


        if(listErrores.size() > 0){
            // Datos incompletos
            AlertDialog.Builder builder = new AlertDialog.Builder(activity_crear_contacto.this);
            builder.setMessage("Por favor llene todos los campos! " + listErrores.get(0) + " datos vacíos" )
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            builder.show();
        }else {

            // Las cosas andan bien

            nuevoContacto.setNombre(Nombre.getText().toString());
            nuevoContacto.setApellido(Apellido.getText().toString());
            nuevoContacto.setCorreo(Email.getText().toString());
            nuevoContacto.setTelefono(Telefono.getText().toString());
            nuevoContacto.setTelefonoFijo(TelefonoFijo.getText().toString());
            nuevoContacto.setDireccion(Direccion.getText().toString());
            nuevoContacto.setIdUsuarioMaster(idUsuario);


            // Imprimir el genero
            System.out.println(nuevoContacto.getNombre());
            System.out.println(nuevoContacto.getApellido());
            System.out.println(nuevoContacto.getCorreo());
            System.out.println(nuevoContacto.getGenero());
            System.out.println(nuevoContacto.getTelefono());
            System.out.println(nuevoContacto.getTelefonoFijo());
            System.out.println(nuevoContacto.getDireccion());
            System.out.println(idUsuario);


            DatabaseHelper db = new DatabaseHelper(activity_crear_contacto.this);

            boolean resultado = db.InsertarContacto(nuevoContacto);

            if(resultado){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Su Contacto ha sido creado con éxito.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                            }
                        });

                builder.show();

            }else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No se ha podido crear su usuario", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.parseColor("#63a4ff"));
                snackbar.show();
            }

        }


    }
}