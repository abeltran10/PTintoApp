package com.abeltran10.ptinto;

import java.util.Vector;

public class Frase {
    public String frase;
    public String mp3;


    public Frase(String frase2, String mp32) {
        this.frase = frase2;
        this.mp3 = mp32;
    }

    public static Vector<Frase> buscar(String consulta, Vector<Frase> listaFrases) {
        Vector<Frase> resultado = new Vector<>();
        Vector<Frase> frases = listaFrases;
        for (int i = 0; i < frases.size(); i++) {
            if (frases.elementAt(i).frase.toLowerCase().contains(consulta.toLowerCase())) {
                resultado.add(new Frase(frases.elementAt(i).frase, frases.elementAt(i).mp3));
            }
        }
        return resultado;
    }
}
