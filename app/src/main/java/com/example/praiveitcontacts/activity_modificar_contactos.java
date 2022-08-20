package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class activity_modificar_contactos extends AppCompatActivity {

    private  int idCharla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contactos);

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idCharla = recibirIdUsuario.getInt("idCharla");
        }

        System.out.println("idCharla editar: " + idCharla);
    }
}