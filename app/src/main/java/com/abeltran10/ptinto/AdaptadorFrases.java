package com.abeltran10.ptinto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Vector;

public class AdaptadorFrases extends BaseAdapter {
    private LayoutInflater inflador;
    private Vector<Frase> listaFrases;

    public AdaptadorFrases(Context contexto, Vector<Frase> listaFrases2) {
        this.inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaFrases = listaFrases2;
    }

    public int getCount() {
        return this.listaFrases.size();
    }

    public Object getItem(int position) {
        return this.listaFrases.elementAt(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Frase frase = this.listaFrases.elementAt(position);
        if (convertView == null) {
            convertView = this.inflador.inflate(R.layout.elemento_selector, (ViewGroup) null);
        }
        ((TextView) convertView.findViewById(R.id.frase)).setText(frase.frase);
        return convertView;
    }
}
