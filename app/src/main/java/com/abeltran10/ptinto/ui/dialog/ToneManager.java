package com.abeltran10.ptinto.ui.dialog;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.abeltran10.ptinto.data.Frase;
import com.abeltran10.ptinto.data.FraseRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ToneManager {

    public static void aplicarTono(Context context, Frase frase, int tipo) {
        // 1. Usar MediaStore para registrar el audio (Forma moderna)
        // 2. Usar ContentResolver para insertar los metadatos

        // Ejemplo de inserción moderna (Concepto)
        ContentValues values = new ContentValues();
        values.put(MediaStore.Audio.Media.TITLE, frase.getFrase());
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mpeg");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, tipo == 0);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, tipo == 1);
        values.put(MediaStore.Audio.Media.IS_ALARM, tipo == 2);

        Uri uri = context.getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);

        if (uri != null) {
            RingtoneManager.setActualDefaultRingtoneUri(context, getRingtoneType(tipo), uri);
            Toast.makeText(context, "Tono establecido", Toast.LENGTH_SHORT).show();
        }
    }

    private static File copiarArchivoDesdeRaw(Context context, int resId, String nombreArchivo) {
        File carpetaCache = new File(context.getCacheDir(), "ptinto_compartido");
        if (!carpetaCache.exists()) carpetaCache.mkdirs();

        File archivoDestino = new File(carpetaCache, nombreArchivo);

        try (InputStream in = context.getResources().openRawResource(resId);
             OutputStream out = new FileOutputStream(archivoDestino)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            return archivoDestino;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Dentro de tu clase ToneManager
    public static void compartirFrase(Context context, String nombreArchivo) {
       int resId = context.getResources().getIdentifier(nombreArchivo, "raw", context.getPackageName());

        // 1. Extraemos el archivo a un sitio accesible
        File archivoTemporal = copiarArchivoDesdeRaw(context, resId, nombreArchivo);

        if (archivoTemporal != null) {
            // 2. Ahora sí usamos el FileProvider con el archivo físico extraído
            Uri uri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileprovider",
                    archivoTemporal);

            // 3. Lanzamos el Intent de compartir
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("audio/mpeg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(intent, "Compartir vía"));
        }
    }

    private static int getRingtoneType(int tipo) {
        switch (tipo) {
            case 0: return RingtoneManager.TYPE_RINGTONE;
            case 1: return RingtoneManager.TYPE_NOTIFICATION;
            default: return RingtoneManager.TYPE_ALARM;
        }
    }
}
