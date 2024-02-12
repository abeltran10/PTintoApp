package com.abeltran10.ptinto;

import java.util.Vector;

public class Frase {
    public String frase;
    public String mp3;
    public static final Vector<Frase> listaFrases = listaFrases();

    public Frase(String frase2, String mp32) {
        this.frase = frase2;
        this.mp3 = mp32;
    }

    public static Vector<Frase> listaFrases() {
        Vector<Frase> frases = new Vector<>();
        frases.add(new Frase("Abducido", "abducido"));
        frases.add(new Frase("Alayuyu", "alayuyu"));
        frases.add(new Frase("A lo Brunelesqui", "a_lo_brunelesqui"));
        frases.add(new Frase("¿Apartada de donde?", "apartada"));
        frases.add(new Frase("El automático", "automatico"));
        frases.add(new Frase("Vamos a ver si nos centramos", "a_ver_si_nos_centramos"));
        frases.add(new Frase("Bujía campagnolo", "bujia_campagnolo"));
        frases.add(new Frase("Búsquese a otro capullo", "busquese_otro_capullo"));
        frases.add(new Frase("100% español", "cien_espanyol"));
        frases.add(new Frase("Crujiente", "crujiente"));
        frases.add(new Frase("Cuala herramienta", "cuala_herramienta"));
        frases.add(new Frase("Daltónico", "daltonico"));
        frases.add(new Frase("Con los dos agujeritos claro", "el_enchufe"));
        frases.add(new Frase("El padre es Dios", "el_padre_es_dios"));
        frases.add(new Frase("El pasao es una mierda", "el_pasao_es_una_mierda"));
        frases.add(new Frase("Eres negro", "eres_negro"));
        frases.add(new Frase("Eurovisión", "eurovision"));
        frases.add(new Frase("Full de negros-chinos", "full_negros_chinos"));
        frases.add(new Frase("Gibraltar español", "gibraltar_espanyol"));
        frases.add(new Frase("Inconscientes", "inconscientes"));
        frases.add(new Frase("Jerusalen", "jerusalen"));
        frases.add(new Frase("La almohada", "la_almohada"));
        frases.add(new Frase("Que pasote la de litro", "la_de_litro"));
        frases.add(new Frase("Peazo de invento", "la_gaseosa"));
        frases.add(new Frase("Me cagüen la renfe", "larenfe"));
        frases.add(new Frase("Me cagüen la vaca que rie", "la_vaca_que_rie"));
        frases.add(new Frase("Espera que va a ser mandinga", "mandinga"));
        frases.add(new Frase("Extranjero", "marciano_extranjero"));
        frases.add(new Frase("Mariconasos", "mariconasos"));
        frases.add(new Frase("Me cagüen mi calavera", "mi_calavera"));
        frases.add(new Frase("Mi casa", "mi_casa"));
        frases.add(new Frase("Que moderno todo", "moderno_todo"));
        frases.add(new Frase("La nave nodriza", "nave_nodriza"));
        frases.add(new Frase("Los negritos se comen", "negritos_se_comen"));
        frases.add(new Frase("NODO", "nodo"));
        frases.add(new Frase("No te puedo", "no_te_puedo"));
        frases.add(new Frase("No te quiero", "no_te_quiero"));
        frases.add(new Frase("Oblea cuatro estaciones", "oblea_cuatro_estaciones"));
        frases.add(new Frase("Olivia y el sol", "olivia_y_el_sol"));
        frases.add(new Frase("Pan apenao", "pan_apenao"));
        frases.add(new Frase("Papi papito", "papi_papito"));
        frases.add(new Frase("Pero mirala que no la miras", "pero_mirala"));
        frases.add(new Frase("Personaje triste", "personaje_triste"));
        frases.add(new Frase("¡¡Poque!!", "poque"));
        frases.add(new Frase("Prefetro", "prefetro"));
        frases.add(new Frase("Pringao", "pringao"));
        frases.add(new Frase("Profesionales", "profesionales"));
        frases.add(new Frase("Que hija puta", "que_hija_puta"));
        frases.add(new Frase("Que lechezitas hace aqui un tio de negro", "que_lechezitas_tio_de_negro"));
        frases.add(new Frase("Repartiendo ostias", "repartiendo_ostias"));
        frases.add(new Frase("La pena de no haber repartido alguna que otra ostia más", "repartiendo_ostias2"));
        frases.add(new Frase("Los romanos una mierda al lado de los etruscos", "romanos_etruscos"));
        frases.add(new Frase("Si hay que sanear se sanea", "sanear"));
        frases.add(new Frase("San Nicolas", "san_nicolas"));
        frases.add(new Frase("Se va a hartar", "se_va_a_hartar"));
        frases.add(new Frase("Sufre", "sufre"));
        frases.add(new Frase("Tralari-tralari", "tarari"));
        frases.add(new Frase("Trenecito", "trenecito"));
        frases.add(new Frase("Tres en uno", "tres_en_uno"));
        frases.add(new Frase("Trompetero de dos metros", "trompetero"));
        frases.add(new Frase("Un pollo que solo somos cinco", "un_pollo_pa_cinco"));
        frases.add(new Frase("Un P.Tinto", "un_ptinto"));
        frases.add(new Frase("Yo soy minero", "yo_soy_minero"));
        return frases;
    }

    public static Vector<Frase> buscar(String consulta) {
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
