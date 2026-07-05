package com.abeltran10.ptinto.data;

public class Frase {
    private final String frase;
    private final String mp3;

    public Frase(String frase, String mp3) {
        this.frase = frase;
        this.mp3 = mp3;
    }

    public String getFrase() { return frase; }
    public String getMp3() { return mp3; }
}
