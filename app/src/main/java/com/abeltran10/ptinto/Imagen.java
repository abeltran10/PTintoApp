package com.abeltran10.ptinto;

public class Imagen {
    private String[] img = {"automatico", "familia", "la_litro", "logo", "los_dos", "mariconasos", "mi_casa", "olivia_tinto", "ovni", "padremarci", "prefetro", "se_va_hartar", "tinto", "tralari", "usillos"};

    public String getImg(int pos) {
        return this.img[pos];
    }
}
