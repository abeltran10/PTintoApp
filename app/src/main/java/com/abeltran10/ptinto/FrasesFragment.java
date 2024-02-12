package com.abeltran10.ptinto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FrasesFragment extends Fragment {

    private Activity actividad;
    private AdaptadorFrases adaptador;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.actividad = activity;
        if (getArguments() != null) {
            this.adaptador = new AdaptadorFrases(this.actividad.getApplication().getApplicationContext(),
                    Frase.buscar(getArguments().getString("consulta"), ((Aplicacion) this.actividad.getApplication()).getListaFrases()));
            return;
        }
        this.adaptador = ((Aplicacion) this.actividad.getApplication()).getAdaptador();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.activity_main, container, false);
        AppCompatActivity compat = (AppCompatActivity) this.actividad;
        Toolbar toolbar = (Toolbar)  vista.findViewById(R.id.toolbar);
        compat.setSupportActionBar(toolbar);

        ListView listview = (ListView) vista.findViewById(android.R.id.list);
        listview.setAdapter(this.adaptador);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView fraseview = (TextView) view.findViewById(R.id.frase);
                Toast.makeText(FrasesFragment.this.actividad, fraseview.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FrasesFragment.this.actividad, Reproductor.class);
                intent.putExtra("frase", fraseview.getText().toString());
                FrasesFragment.this.actividad.stopService(intent);
                FrasesFragment.this.actividad.startService(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String frase = ((TextView) view.findViewById(R.id.frase)).getText().toString();
                Dialogo d = new Dialogo();
                Bundle titulo = new Bundle();
                titulo.putString("titulo", frase);
                d.setArguments(titulo);
                d.show(((AppCompatActivity)FrasesFragment.this.actividad).getSupportFragmentManager(), "opciones");
                return true;
            }
        });
        return vista;
    }
}
