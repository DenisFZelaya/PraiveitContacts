package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.praiveitcontacts.Models.Contactos;
import com.example.praiveitcontacts.Models.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  int idUsuario;
    private TextView txTitulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.getAllContactos();
        db.OpenDB();

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idUsuario = recibirIdUsuario.getInt("idUsuario");
        }

        Usuario usuarioLogueado = db.getUsuarioById(idUsuario);

        System.out.println("Usuario logueado : " + idUsuario);
        txTitulo = findViewById(R.id.txTitulo);
        txTitulo.setText("Contactos de " + usuarioLogueado.getNombre());

    }


    public void onClickDb(View view) {
        super.onResume();
        System.out.println("Presionando boton");
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.getAllContactos();
        db.OpenDB();
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("Ejecutando onStart");
        listContactos();
    }

    public void listContactos(){
        ListView lv_contactos = findViewById(R.id.lv_contactos_list);
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Contactos> arrayContactos = (ArrayList<Contactos>) db.getAllContactos(idUsuario);
        ListViewAdapter adapter = new ListViewAdapter(this, arrayContactos);
        lv_contactos.setAdapter(adapter);
    }

    public  void irCrearContactos(View v){
        Bundle datoenvia = new Bundle();
        datoenvia.putInt("Id", idUsuario);
        Intent intentCrear = new Intent (MainActivity.this, activity_crear_contacto.class);
        intentCrear.putExtras(datoenvia);
        startActivity(intentCrear);
    }

}