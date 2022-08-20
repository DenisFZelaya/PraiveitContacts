package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.praiveitcontacts.Models.Contactos;

public class activity_modificar_contactos extends AppCompatActivity {

    private  int idCharla;
    Contactos contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contactos);

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idCharla = recibirIdUsuario.getInt("idCharla");
        }

        System.out.println("idCharla editar: " + idCharla);

        DatabaseHelper db = new DatabaseHelper(activity_modificar_contactos.this);
        contacto = db.getContactoModelById(idCharla);

        Log.i("Modificar una charla", contacto.getNombre());

    }
}