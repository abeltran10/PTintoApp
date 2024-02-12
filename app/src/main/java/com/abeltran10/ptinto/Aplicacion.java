package com.abeltran10.ptinto;

import android.app.Application;
import java.util.Vector;

public class Aplicacion extends Application {
    private AdaptadorFrases adaptador;
    private Vector<Frase> listaFrases;

    public void onCreate() {
        super.onCreate();
        this.listaFrases = Frase.listaFrases;
        this.adaptador = new AdaptadorFrases(this, this.listaFrases);
    }

    public AdaptadorFrases getAdaptador() {
        return this.adaptador;
    }

    public Vector<Frase> getVectorFrase() {
        return this.listaFrases;
    }
}
