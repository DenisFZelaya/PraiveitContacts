package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.praiveitcontacts.Models.Contactos;
import com.example.praiveitcontacts.Models.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  int idUsuario;
    private TextView txTitulo;
    private EditText etBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idUsuario = recibirIdUsuario.getInt("idUsuario");
        }

        Usuario usuarioLogueado = db.getUsuarioById(idUsuario);

        System.out.println("Usuario logueado : " + idUsuario);
        txTitulo = findViewById(R.id.txTitulo);
        txTitulo.setText("Contactos de " + usuarioLogueado.getNombre());

        etBuscar = (EditText)findViewById(R.id.etBuscar);

        etBuscar.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    System.out.println("onTextChangued " + s.toString().trim());

                    listContactosBusqueda(s.toString().trim());
                }else {
                    listContactos();
                }


            }
        });

    }



    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("Ejecutando onStart");
        listContactos();
    }

    public void listContactos(){
        ListView lv_contactos = (ListView)findViewById(R.id.lv_contactos_list);
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        ArrayList<Contactos> arrayContactos = (ArrayList<Contactos>) db.getAllContactos(idUsuario);
        ListContactoAdapter adapter = new ListContactoAdapter(MainActivity.this, arrayContactos);
        lv_contactos.setAdapter(adapter);

    }

    public void listContactosBusqueda(String buscarNombre){
        ListView lv_contactos = (ListView)findViewById(R.id.lv_contactos_list);
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        ArrayList<Contactos> arrayContactos = (ArrayList<Contactos>) db.getBuscarContactos(idUsuario, buscarNombre);
        ListContactoAdapter adapter = new ListContactoAdapter(MainActivity.this, arrayContactos);
        lv_contactos.setAdapter(adapter);

    }

    public  void irCrearContactos(View v){
        Bundle datoenvia = new Bundle();
        datoenvia.putInt("idUsuario", idUsuario);
        Intent intentCrear = new Intent (MainActivity.this, activity_crear_contacto.class);
        intentCrear.putExtras(datoenvia);
        startActivity(intentCrear);
    }

}