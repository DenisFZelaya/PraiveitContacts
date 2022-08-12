package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected EditText etUsu, etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etUsu = findViewById(R.id.tiusuario_login);
        etContrasena = findViewById(R.id.tipass_login);

        Button buttonLoguin = (Button) findViewById(R.id.btn_inicio_login);
        buttonLoguin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(etUsu.getText().length() < 1){
                        etUsu.setBackgroundColor(Color.parseColor("#ffb6c1"));
                    }
                    if(etContrasena.getText().length() < 1){
                        etContrasena.setBackgroundColor(Color.parseColor("#ffb6c1"));
                    }
                    System.out.println("Message: Boton presionado Usu: " + etUsu.getText() + ". Pass: " + etContrasena.getText());

                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}