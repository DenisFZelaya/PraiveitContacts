package com.example.praiveitcontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.praiveitcontacts.Models.Login;
import com.example.praiveitcontacts.Models.Usuario;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    protected EditText etUsu, etContrasena;
    public static final String MyPreferencesApp = "PrefsApp" ;
    SharedPreferences sharedpreferences;
    SwitchMaterial sesionIniciadaComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etUsu = findViewById(R.id.tiusuario_login);
        etContrasena = findViewById(R.id.tipass_login);
        sesionIniciadaComponent = findViewById(R.id.switch_sesion_iniciada);

        //Definicion de preferencias
        sharedpreferences = getSharedPreferences(MyPreferencesApp, Context.MODE_PRIVATE);

        // cambiar estado del switch segun las preferencias
        boolean mantSesion = sharedpreferences.getBoolean("MantenerSesionIniciada", false);
        sesionIniciadaComponent.setChecked(mantSesion);

        //Redirigir al menu si el usuario ya esta logueado
        UsuarioLogueado();

        sesionIniciadaComponent.setOnCheckedChangeListener(new
            CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  SharedPreferences.Editor editor = sharedpreferences.edit();
                  editor.putBoolean("MantenerSesionIniciada", b);
                  editor.commit();
               }
            });

        Button buttonLoguin = (Button) findViewById(R.id.btn_inicio_login);
        buttonLoguin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    List<String> listErrores = new ArrayList<String>();

                    if(etUsu.getText().length() < 1){
                        etUsu.setBackgroundColor(Color.parseColor("#ffb6c1"));
                        listErrores.add("Usuario vacío");
                    }else {
                        etUsu.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    if(etContrasena.getText().length() < 1){
                        etContrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));
                        listErrores.add("Pass vacío");
                    }else {
                        etContrasena.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    if(listErrores.size() > 0){
                        // Datos incompletos
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Por favor llene todos los campos!" )
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });

                        builder.show();
                    }else {
                        // datos llenados por el usuario
                        System.out.println("Yendo a la db");
                        DatabaseHelper db = new DatabaseHelper(LoginActivity.this);

                        Login loginRequest = new Login();
                        loginRequest.setUsuario(etUsu.getText().toString());
                        loginRequest.setContrasena(etContrasena.getText().toString());
                        Usuario usuarioResponse = db.Login(loginRequest);

                        if(usuarioResponse.getId() == 0){
                            System.out.println("incorrectos");
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Credenciales incorrectas", Snackbar.LENGTH_LONG);
                            // snackbar.setBackgroundTint(color(android.R.color.));
                            snackbar.setBackgroundTint(Color.parseColor("#FF0000"));
                            snackbar.show();
                        } else {
                            // Todos bien

                            //Ver si guardar sesion
                            if(sesionIniciadaComponent.isChecked()){
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean("MantenerSesionIniciada", true);
                                editor.putInt("idUsuario", usuarioResponse.getId());
                                editor.commit();
                            }else {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean("MantenerSesionIniciada", false);
                                editor.commit();
                            }

                            Bundle datoenvia = new Bundle();
                            datoenvia.putInt ("idUsuario",usuarioResponse.getId());

                            Intent MainPage = new Intent (LoginActivity.this, MainActivity.class);
                            MainPage.putExtras(datoenvia);
                            startActivity(MainPage);
                        }
                    }

                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public void Registro (View v){
        Intent RegistroPage = new Intent (LoginActivity.this, RegistroActivity.class);
        startActivity(RegistroPage);
    }

    public void UsuarioLogueado (){
        try {
            int idUsuario = sharedpreferences.getInt("idUsuario", 0);
            System.out.println("Verificar si el usuario esta logueado " + idUsuario);
                if(idUsuario != 0){
                    Bundle datoenvia = new Bundle();
                    datoenvia.putInt ("idUsuario",idUsuario);

                    Intent MainPage = new Intent (LoginActivity.this, MainActivity.class);
                    MainPage.putExtras(datoenvia);
                    startActivity(MainPage);
                }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}