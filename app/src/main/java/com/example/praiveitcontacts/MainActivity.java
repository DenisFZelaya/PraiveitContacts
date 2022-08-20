package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.praiveitcontacts.Models.Contactos;
import com.example.praiveitcontacts.Models.Usuario;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  int idUsuario;
    private TextView txTitulo;
    private EditText etBuscar;

    public static final String MyPreferencesApp = "PrefsApp" ;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //Definicion de preferencias
        sharedpreferences = getSharedPreferences(MyPreferencesApp, Context.MODE_PRIVATE);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_menu_creditos:
                System.out.println("Item menu creditos");
                Intent intentCreditos = new Intent (MainActivity.this, CreditosActivity.class);
                startActivity(intentCreditos);
                return true;
            case R.id.item_menu_cerrar_sesion:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;

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