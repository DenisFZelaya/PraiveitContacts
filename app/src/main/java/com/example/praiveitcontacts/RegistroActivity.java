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

import com.example.praiveitcontacts.Models.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegistroActivity extends AppCompatActivity {

    private EditText Nombre,Apellido,User, Email, Telefono, Genero, Contrasena, RepeatContrasena;
    private RadioButton Hombre, Mujer;
    private Button btnCrearUsuario;
    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();

        Nombre = findViewById(R.id.inputNombreContacto);
        Apellido = findViewById(R.id.inputApellidoContacto);
        User = findViewById(R.id.inputUsuarioRegistro);
        Email = findViewById(R.id.inputEmailContacto);
        Hombre = findViewById(R.id.radio_button_HombreContacto);
        Mujer = findViewById(R.id.radio_button_MujerContacto);
        Contrasena = findViewById(R.id.inputContrasenaRegistro);
        RepeatContrasena = findViewById(R.id.inputRepeatContraRegistro);
        radioGroup = findViewById(R.id.radioGroupGeneroRegistro);
    }

    public void crearUsuarioRegistro(View v){
        Usuario nuevoUsuario = new Usuario();

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

        if(User.getText().length() < 1){
            User.setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Email vacío");
        }else {
            User.setBackgroundColor(Color.parseColor("#ffffff"));
        }


        //Validar contraseña
        if(Contrasena.getText().toString().equals(RepeatContrasena.getText().toString())){
            if(Contrasena.getText().toString().length() > 0 && RepeatContrasena.getText().toString().length() > 0){

                Contrasena.setBackgroundColor(Color.WHITE);
                RepeatContrasena.setBackgroundColor(Color.WHITE);
                nuevoUsuario.setContrasena(Contrasena.getText().toString());

            } else {
                Contrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));
                RepeatContrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));

                System.out.println("Contrasena " + Contrasena.getText().toString());
                System.out.println("ContrasenaRepeat " + RepeatContrasena.getText().toString());
                listErrores.add("Contrasena vacío");
            }
        }else {
            Contrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));
            RepeatContrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Pass no coincide");
        }


        if(Hombre.isChecked() == false && Mujer.isChecked() == false){
            // Genero vacio
            findViewById(R.id.fr_layout_registro).setBackgroundColor(Color.parseColor("#ffb6c1"));
            listErrores.add("Genero vacío");
        }else {
            findViewById(R.id.fr_layout_registro).setBackgroundColor(Color.parseColor("#ffffff"));
        }

        //Insertar Genero

        if(Mujer.isChecked()){
            nuevoUsuario.setGenero("M");
        }else {
            nuevoUsuario.setGenero("H");
            // Hombre.setChecked(true);
        }


        if(listErrores.size() > 0){
            // Datos incompletos
            AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
            builder.setMessage("Por favor llene todos los campos! " + listErrores.get(0) + " datos vacíos" )
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            builder.show();
        }else {
            // Las cosas andan bien
            nuevoUsuario.setId(0);
            nuevoUsuario.setNombre(Nombre.getText().toString());
            nuevoUsuario.setApellido(Apellido.getText().toString());
            nuevoUsuario.setUsuario(User.getText().toString());
            nuevoUsuario.setCorreo(Email.getText().toString());
            nuevoUsuario.setContrasena(Contrasena.getText().toString());

            System.out.println(nuevoUsuario.getGenero());

            DatabaseHelper db = new DatabaseHelper(RegistroActivity.this);

            boolean resultado = db.InsertarUsuario(nuevoUsuario);

            if(resultado){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Su usuario " + nuevoUsuario.getUsuario() + "  ha sido creado, será redirigido al login")
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