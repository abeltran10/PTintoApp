package com.abeltran10.ptinto;

import android.app.Application;
import java.util.Vector;

public class Aplicacion extends Application {
    private AdaptadorFrases adaptador;
    private Vector<Frase> listaFrases;

    public void onCreate() {
        super.onCreate();

        this.listaFrases = new Vector<>();
        this.listaFrases.add(new Frase("Abducido", "abducido"));
        this.listaFrases.add(new Frase("Alayuyu", "alayuyu"));
        this.listaFrases.add(new Frase("A lo Brunelesqui", "a_lo_brunelesqui"));
        this.listaFrases.add(new Frase("¿Apartada de donde?", "apartada"));
        this.listaFrases.add(new Frase("El automático", "automatico"));
        this.listaFrases.add(new Frase("Vamos a ver si nos centramos", "a_ver_si_nos_centramos"));
        this.listaFrases.add(new Frase("Bujía campagnolo", "bujia_campagnolo"));
        this.listaFrases.add(new Frase("Búsquese a otro capullo", "busquese_otro_capullo"));
        this.listaFrases.add(new Frase("100% español", "cien_espanyol"));
        this.listaFrases.add(new Frase("Crujiente", "crujiente"));
        this.listaFrases.add(new Frase("Cuala herramienta", "cuala_herramienta"));
        this.listaFrases.add(new Frase("Daltónico", "daltonico"));
        this.listaFrases.add(new Frase("Con los dos agujeritos claro", "el_enchufe"));
        this.listaFrases.add(new Frase("El padre es Dios", "el_padre_es_dios"));
        this.listaFrases.add(new Frase("El pasao es una mierda", "el_pasao_es_una_mierda"));
        this.listaFrases.add(new Frase("Eres negro", "eres_negro"));
        this.listaFrases.add(new Frase("Eurovisión", "eurovision"));
        this.listaFrases.add(new Frase("Full de negros-chinos", "full_negros_chinos"));
        this.listaFrases.add(new Frase("Gibraltar español", "gibraltar_espanyol"));
        this.listaFrases.add(new Frase("Inconscientes", "inconscientes"));
        this.listaFrases.add(new Frase("Jerusalen", "jerusalen"));
        this.listaFrases.add(new Frase("La almohada", "la_almohada"));
        this.listaFrases.add(new Frase("Que pasote la de litro", "la_de_litro"));
        this.listaFrases.add(new Frase("Peazo de invento", "la_gaseosa"));
        this.listaFrases.add(new Frase("Me cagüen la renfe", "larenfe"));
        this.listaFrases.add(new Frase("Me cagüen la vaca que rie", "la_vaca_que_rie"));
        this.listaFrases.add(new Frase("Espera que va a ser mandinga", "mandinga"));
        this.listaFrases.add(new Frase("Extranjero", "marciano_extranjero"));
        this.listaFrases.add(new Frase("Mariconasos", "mariconasos"));
        this.listaFrases.add(new Frase("Me cagüen mi calavera", "mi_calavera"));
        this.listaFrases.add(new Frase("Mi casa", "mi_casa"));
        this.listaFrases.add(new Frase("Que moderno todo", "moderno_todo"));
        this.listaFrases.add(new Frase("La nave nodriza", "nave_nodriza"));
        this.listaFrases.add(new Frase("Los negritos se comen", "negritos_se_comen"));
        this.listaFrases.add(new Frase("NODO", "nodo"));
        this.listaFrases.add(new Frase("No te puedo", "no_te_puedo"));
        this.listaFrases.add(new Frase("No te quiero", "no_te_quiero"));
        this.listaFrases.add(new Frase("Oblea cuatro estaciones", "oblea_cuatro_estaciones"));
        this.listaFrases.add(new Frase("Olivia y el sol", "olivia_y_el_sol"));
        this.listaFrases.add(new Frase("Pan apenao", "pan_apenao"));
        this.listaFrases.add(new Frase("Papi papito", "papi_papito"));
        this.listaFrases.add(new Frase("Pero mirala que no la miras", "pero_mirala"));
        this.listaFrases.add(new Frase("Personaje triste", "personaje_triste"));
        this.listaFrases.add(new Frase("¡¡Poque!!", "poque"));
        this.listaFrases.add(new Frase("Prefetro", "prefetro"));
        this.listaFrases.add(new Frase("Pringao", "pringao"));
        this.listaFrases.add(new Frase("Profesionales", "profesionales"));
        this.listaFrases.add(new Frase("Que hija puta", "que_hija_puta"));
        this.listaFrases.add(new Frase("Que lechezitas hace aqui un tio de negro", "que_lechezitas_tio_de_negro"));
        this.listaFrases.add(new Frase("Repartiendo ostias", "repartiendo_ostias"));
        this.listaFrases.add(new Frase("La pena de no haber repartido alguna que otra ostia más", "repartiendo_ostias2"));
        this.listaFrases.add(new Frase("Los romanos una mierda al lado de los etruscos", "romanos_etruscos"));
        this.listaFrases.add(new Frase("Si hay que sanear se sanea", "sanear"));
        this.listaFrases.add(new Frase("San Nicolas", "san_nicolas"));
        this.listaFrases.add(new Frase("Se va a hartar", "se_va_a_hartar"));
        this.listaFrases.add(new Frase("Sufre", "sufre"));
        this.listaFrases.add(new Frase("Tralari-tralari", "tarari"));
        this.listaFrases.add(new Frase("Trenecito", "trenecito"));
        this.listaFrases.add(new Frase("Tres en uno", "tres_en_uno"));
        this.listaFrases.add(new Frase("Trompetero de dos metros", "trompetero"));
        this.listaFrases.add(new Frase("Un pollo que solo somos cinco", "un_pollo_pa_cinco"));
        this.listaFrases.add(new Frase("Un P.Tinto", "un_ptinto"));
        this.listaFrases.add(new Frase("Yo soy minero", "yo_soy_minero"));
        
        this.adaptador = new AdaptadorFrases(this, this.listaFrases);
    }

    public AdaptadorFrases getAdaptador() {
        return this.adaptador;
    }

    public Vector<Frase> getListaFrases() {
        return this.listaFrases;
    }
}
