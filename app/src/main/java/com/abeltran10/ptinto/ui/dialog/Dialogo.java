package com.abeltran10.ptinto.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.abeltran10.ptinto.data.Frase;
import com.abeltran10.ptinto.data.FraseRepository;

public class Dialogo extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Obtenemos el título de forma segura
        String titulo = (getArguments() != null) ? getArguments().getString("titulo") : "";

        return new AlertDialog.Builder(requireContext())
                .setTitle(titulo)
                .setItems(new String[]{"Tono llamada", "Tono notificación", "Tono alarma", "Compartir"}, (dialog, pos) -> {
                    // Delegamos toda la lógica técnica al ToneManager
                    if (pos == 3) {
                        ToneManager.compartirFrase(requireContext(), titulo);
                    } else {
                        // Buscamos la frase en el repositorio centralizado
                        Frase encontrada = FraseRepository.buscarPorNombre(titulo);
                        if (encontrada != null) {
                            ToneManager.aplicarTono(requireContext(), encontrada, pos);
                        }
                    }
                })
                .create();
    }
}
