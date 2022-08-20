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


        // Get the data item for this position
        Contactos contactos = getItem(position);

        // Declarar variables y igualarlas al xml
        TextView textViewNombreContacto = (TextView) convertView.findViewById(R.id.tvNombre);
        TextView textViewNumeroContacto = (TextView) convertView.findViewById(R.id.tvNumero);
        Button btnItem = (Button) convertView.findViewById(R.id.btnList);

        // Setear textview con datos de la charla
        textViewNombreContacto.setText(contactos.getNombre() + " " + contactos.getApellido());
        textViewNumeroContacto.setText(contactos.getTelefono());

        // Return the completed view to render on screen

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
    protected void cargarPageEditar(int idCharla) {
        try {
            System.out.println("Funcion editar charla: " + idCharla);
            Bundle datoenvia = new Bundle();
            datoenvia.putInt("idCharla", idCharla);

            Intent intentEditarCharla = new Intent(getContext(), activity_modificar_contactos.class);
            intentEditarCharla.putExtras(datoenvia);
            getContext().startActivity(intentEditarCharla);
        } catch (Exception ex) {
            System.out.println("Error cargando EditarCharla: " + ex.getMessage());
        }

    }

}