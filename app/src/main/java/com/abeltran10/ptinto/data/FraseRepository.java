package com.abeltran10.ptinto.data;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FraseRepository {
    private static final List<Frase> listaFrases = new ArrayList<>();

    static {
        listaFrases.add(new Frase("Abducido", "abducido"));
        listaFrases.add(new Frase("Alayuyu", "alayuyu"));
        listaFrases.add(new Frase("A lo Brunelesqui", "a_lo_brunelesqui"));
        listaFrases.add(new Frase("¿Apartada de donde?", "apartada"));
        listaFrases.add(new Frase("El automático", "automatico"));
        listaFrases.add(new Frase("Vamos a ver si nos centramos", "a_ver_si_nos_centramos"));
        listaFrases.add(new Frase("Bujía campagnolo", "bujia_campagnolo"));
        listaFrases.add(new Frase("Búsquese a otro capullo", "busquese_otro_capullo"));
        listaFrases.add(new Frase("100% español", "cien_espanyol"));
        listaFrases.add(new Frase("Crujiente", "crujiente"));
        listaFrases.add(new Frase("Cuala herramienta", "cuala_herramienta"));
        listaFrases.add(new Frase("Daltónico", "daltonico"));
        listaFrases.add(new Frase("Con los dos agujeritos claro", "el_enchufe"));
        listaFrases.add(new Frase("El padre es Dios", "el_padre_es_dios"));
        listaFrases.add(new Frase("El pasao es una mierda", "el_pasao_es_una_mierda"));
        listaFrases.add(new Frase("Eres negro", "eres_negro"));
        listaFrases.add(new Frase("Eurovisión", "eurovision"));
        listaFrases.add(new Frase("Full de negros-chinos", "full_negros_chinos"));
        listaFrases.add(new Frase("Gibraltar español", "gibraltar_espanyol"));
        listaFrases.add(new Frase("Inconscientes", "inconscientes"));
        listaFrases.add(new Frase("Jerusalen", "jerusalen"));
        listaFrases.add(new Frase("La almohada", "la_almohada"));
        listaFrases.add(new Frase("Que pasote la de litro", "la_de_litro"));
        listaFrases.add(new Frase("Peazo de invento", "la_gaseosa"));
        listaFrases.add(new Frase("Me cagüen la renfe", "larenfe"));
        listaFrases.add(new Frase("Me cagüen la vaca que rie", "la_vaca_que_rie"));
        listaFrases.add(new Frase("Espera que va a ser mandinga", "mandinga"));
        listaFrases.add(new Frase("Extranjero", "marciano_extranjero"));
        listaFrases.add(new Frase("Mariconasos", "mariconasos"));
        listaFrases.add(new Frase("Me cagüen mi calavera", "mi_calavera"));
        listaFrases.add(new Frase("Mi casa", "mi_casa"));
        listaFrases.add(new Frase("Que moderno todo", "moderno_todo"));
        listaFrases.add(new Frase("La nave nodriza", "nave_nodriza"));
        listaFrases.add(new Frase("Los negritos se comen", "negritos_se_comen"));
        listaFrases.add(new Frase("NODO", "nodo"));
        listaFrases.add(new Frase("No te puedo", "no_te_puedo"));
        listaFrases.add(new Frase("No te quiero", "no_te_quiero"));
        listaFrases.add(new Frase("Oblea cuatro estaciones", "oblea_cuatro_estaciones"));
        listaFrases.add(new Frase("Olivia y el sol", "olivia_y_el_sol"));
        listaFrases.add(new Frase("Pan apenao", "pan_apenao"));
        listaFrases.add(new Frase("Papi papito", "papi_papito"));
        listaFrases.add(new Frase("Pero mirala que no la miras", "pero_mirala"));
        listaFrases.add(new Frase("Personaje triste", "personaje_triste"));
        listaFrases.add(new Frase("¡¡Poque!!", "poque"));
        listaFrases.add(new Frase("Prefetro", "prefetro"));
        listaFrases.add(new Frase("Pringao", "pringao"));
        listaFrases.add(new Frase("Profesionales", "profesionales"));
        listaFrases.add(new Frase("Que hija puta", "que_hija_puta"));
        listaFrases.add(new Frase("Que lechezitas hace aqui un tio de negro", "que_lechezitas_tio_de_negro"));
        listaFrases.add(new Frase("Repartiendo ostias", "repartiendo_ostias"));
        listaFrases.add(new Frase("La pena de no haber repartido alguna que otra ostia más", "repartiendo_ostias2"));
        listaFrases.add(new Frase("Los romanos una mierda al lado de los etruscos", "romanos_etruscos"));
        listaFrases.add(new Frase("Si hay que sanear se sanea", "sanear"));
        listaFrases.add(new Frase("San Nicolas", "san_nicolas"));
        listaFrases.add(new Frase("Se va a hartar", "se_va_a_hartar"));
        listaFrases.add(new Frase("Sufre", "sufre"));
        listaFrases.add(new Frase("Tralari-tralari", "tarari"));
        listaFrases.add(new Frase("Trenecito", "trenecito"));
        listaFrases.add(new Frase("Tres en uno", "tres_en_uno"));
        listaFrases.add(new Frase("Trompetero de dos metros", "trompetero"));
        listaFrases.add(new Frase("Un pollo que solo somos cinco", "un_pollo_pa_cinco"));
        listaFrases.add(new Frase("Un P.Tinto", "un_ptinto"));
        listaFrases.add(new Frase("Yo soy minero", "yo_soy_minero"));

    }

    public static List<Frase> getListaFrases() {
        return new ArrayList<>(listaFrases); // Retornamos copia para proteger la original
    }

    public static List<Frase> buscar(String query) {
        if (query == null || query.isEmpty()) {
            return getListaFrases();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Opción moderna para API 24+
            return listaFrases.stream()
                    .filter(f -> f.getFrase().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            // Opción tradicional para versiones antiguas
            List<Frase> resultados = new ArrayList<>();
            String queryLower = query.toLowerCase();

            for (Frase f : listaFrases) {
                if (f.getFrase().toLowerCase().contains(queryLower)) {
                    resultados.add(f);
                }
            }
            return resultados;
        }
    }

    public static Frase buscarPorNombre(String nombre) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Búsqueda moderna usando Stream API (disponible desde API 24)
            return listaFrases.stream()
                    .filter(f -> f.getFrase().equals(nombre))
                    .findFirst()
                    .orElse(null);
        } else {
            // Búsqueda tradicional para versiones anteriores (API < 24)
            for (Frase f : listaFrases) {
                if (f.getFrase().equals(nombre)) {
                    return f;
                }
            }
            return null;
        }
    }
}

