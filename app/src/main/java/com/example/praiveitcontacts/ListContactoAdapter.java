package com.example.praiveitcontacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praiveitcontacts.Models.Contactos;

import java.util.ArrayList;

public class ListContactoAdapter extends ArrayAdapter<Contactos> {
    //1.1 Constructor
    public ListContactoAdapter(Context context, ArrayList<Contactos> contactos) {
        super(context, 0, contactos);
        System.out.println("ctor  adapter");
    }

    //1.2 Metodo para obtener el layout de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        System.out.println("getView adapter");

        // Obtener xml de la vista
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            System.out.println("convertViewId " + convertView.getId());
        }
        System.out.println("convertView " + convertView.toString());
        ImageView imgContacto;

        // Get the data item for this position
        Contactos contactos = getItem(position);

        // Declarar variables y igualarlas al xml
        TextView textViewNombreContacto = (TextView) convertView.findViewById(R.id.tvNombre);
        TextView textViewNumeroContacto = (TextView) convertView.findViewById(R.id.tvNumero);
        Button btnItem = (Button) convertView.findViewById(R.id.btnList);
        imgContacto = (ImageView) convertView.findViewById(R.id.mtrl_list_item_icon);

        // Setear textview con datos de la charla
        textViewNombreContacto.setText(contactos.getNombre() + " " + contactos.getApellido());
        textViewNumeroContacto.setText(contactos.getTelefono());

        if(contactos.getGenero().equals("H")){
            imgContacto.setBackgroundResource(R.drawable.donatello);
        } else {
            imgContacto.setBackgroundResource(R.drawable.pucca);
        }

        if(contactos.getNombre().toLowerCase().equals("aaron")){
            imgContacto.setBackgroundResource(R.drawable.pocoyo);
        }

        // Return the completed view to render on screen
        textViewNombreContacto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("Click en el texto para mostrar los detalles");
                cargarDetallesContacto(contactos.getId());
            }
        });

        // Asignar evento a boton
        btnItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                cargarPageEditar(contactos.getId());
            }
        });

        //Retornar la vista
        return convertView;
    }

    //1.3 Método para cargar una nueva actividad editar
    protected void cargarPageEditar(int idContacto) {
        try {
            System.out.println("Funcion editar charla: " + idContacto);
            Bundle datoenvia = new Bundle();
            datoenvia.putInt("idContacto", idContacto);

            Intent intentEditarCharla = new Intent(getContext(), activity_modificar_contactos.class);
            intentEditarCharla.putExtras(datoenvia);
            getContext().startActivity(intentEditarCharla);
        } catch (Exception ex) {
            System.out.println("Error cargando EditarCharla: " + ex.getMessage());
        }
    }

    protected void cargarDetallesContacto(int idContacto) {
        try {
            System.out.println("Funcion editar contacto: " + idContacto);
            Bundle datoenvia = new Bundle();
            datoenvia.putInt("idContacto", idContacto);

            Intent intent = new Intent(getContext(), DetallesContactoActivity.class);
            intent.putExtras(datoenvia);
            getContext().startActivity(intent);
        } catch (Exception ex) {
            System.out.println("Error cargando detalles Contacto: " + ex.getMessage());
        }
    }

}