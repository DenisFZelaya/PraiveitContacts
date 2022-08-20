package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class activity_crear_contacto extends AppCompatActivity {

    private  int idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idUsuario = recibirIdUsuario.getInt("idUsuario");
        }

        System.out.println("idUsuario crear contacto: " + idUsuario);
    }
}