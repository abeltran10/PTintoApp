package com.abeltran10.ptinto.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

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
                    Frase encontrada = FraseRepository.buscarPorNombre(titulo);

                    if (encontrada != null) {
                        // Delegamos toda la lógica técnica al ToneManager
                        if (pos == 3) {
                            ToneManager.compartirFrase(requireContext(), encontrada);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(requireContext())) {
                                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                    intent.setData(Uri.parse("package:" + requireContext().getPackageName()));
                                    requireContext().startActivity(intent);
                            } else {
                                ToneManager.aplicarTono(requireContext(), encontrada, pos);
                            }


                        }
                    }
                })
                .create();
    }
}
