package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.praiveitcontacts.Models.Usuario;

public class MainActivity extends AppCompatActivity {

    private  int idUsuario;
    private TextView idTextoEntrada;

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
        idTextoEntrada = findViewById(R.id.idTextoEntrada);
        idTextoEntrada.setText("Id usuario logueado " + usuarioLogueado.getNombre());
    }

    public void onClickDb(View view) {
        super.onResume();
        System.out.println("Presionando boton");
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.getAllContactos();
        db.OpenDB();
    }
}