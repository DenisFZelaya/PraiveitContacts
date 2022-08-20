package com.example.praiveitcontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praiveitcontacts.Models.Contactos;

public class DetallesContactoActivity extends AppCompatActivity {

    private  int idContacto;
    private TextView tvNombreCompleto, direccionMaps, enviarMensajeWhats, enviarCorreoElec, LlamarCel, LlamarFijo, msjTexto ;
    private ImageView imgContacto;
    private Drawable imgMujer;
    private Drawable imgHombre;



    Contactos contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);
        getSupportActionBar().hide();

        Bundle recibirIdUsuario= this.getIntent().getExtras();
        if (recibirIdUsuario != null) {
            idContacto = recibirIdUsuario.getInt("idContacto");
        }
        System.out.println("Id contado a mostrar detalles " + idContacto);

        tvNombreCompleto = (TextView)findViewById(R.id.tv_nombre_detalles_contacto);

        direccionMaps = (TextView)findViewById(R.id.tv_direccion_detalles);
        enviarMensajeWhats = (TextView)findViewById(R.id.tv_whatsapp_detalles);
        enviarCorreoElec = (TextView)findViewById(R.id.tv_enviar_correo_detalles);
        LlamarCel = (TextView)findViewById(R.id.tv_llamar_detalles);
        LlamarFijo = (TextView)findViewById(R.id.tv_llamar_fijo_detalles);
        msjTexto = (TextView)findViewById(R.id.tv_msj_texto_detalles_contacto);
        imgContacto = (ImageView) findViewById(R.id.imageViewDetallesContacto);



        DatabaseHelper db = new DatabaseHelper(DetallesContactoActivity.this);
        contacto = db.getContactoModelById(idContacto);

        tvNombreCompleto.setText(contacto.getNombre() + " " + contacto.getApellido());
        direccionMaps.setText(contacto.getDireccion());
        enviarMensajeWhats.setText(contacto.getTelefono());
        enviarCorreoElec.setText(contacto.getCorreo());
        LlamarCel.setText(contacto.getTelefono());
        LlamarFijo.setText(contacto.getTelefonoFijo());
        msjTexto.setText(contacto.getTelefono());

        // Get imgs
        //imgMujer = getDrawable("pucca.png");
        //imgHombre = getDrawable("donatello.png");

        Drawable imagen =  ContextCompat.getDrawable(DetallesContactoActivity.this, R.drawable.pucca);

        if(contacto.getGenero().equals("H")){
            imgContacto.setImageResource(R.drawable.donatello);
        } else {
            imgContacto.setImageResource(R.drawable.pucca);
        }



        direccionMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/place/" + contacto.getDireccion());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        LlamarCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:+504" + contacto.getTelefono());
                System.out.println(contacto.getTelefono());
                System.out.println(uri);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        LlamarFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:" + contacto.getTelefonoFijo());
                System.out.println(contacto.getTelefonoFijo());
                System.out.println(uri);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        enviarMensajeWhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=+504"+contacto.getTelefono());
                System.out.println(contacto.getTelefonoFijo());
                System.out.println(uri);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        msjTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", "+504"+contacto.getTelefono(), null));
                sendIntent.putExtra("sms_body", "Este es un mensaje enviado desde Praiveit App");
                startActivity(sendIntent);
            }
        });


        enviarCorreoElec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[] { contacto.getCorreo() });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Correo desde Praiveit App");
                intent.putExtra(Intent.EXTRA_TEXT, "Este es un mensaje enviado a " + contacto.getNombre() + " desde la app de Praiveit Contact");

                // Establezco el tipo de Intent
                intent.setType("message/rfc822");

                // Lanzo el selector de cliente de Correo
                startActivity(intent.createChooser(intent, "Elije un cliente de Correo:"));
                //startActivity(intent);
            }
        });
    }
}