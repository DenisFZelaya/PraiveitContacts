package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.getAllContactos();
        db.OpenDB();

    }

    public void onClickDb(View view) {
        super.onResume();
        System.out.println("Presionando boton");
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        db.getAllContactos();
        db.OpenDB();

    }




}