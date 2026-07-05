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

    public static void compartirFrase(Context context, String nombreFrase) {
        Frase f = FraseRepository.buscarPorNombre(nombreFrase);
        if (f == null) return;

        File cachePath = new File(context.getCacheDir(), "compartir");
        if (!cachePath.exists()) cachePath.mkdirs();
        File archivo = new File(cachePath, f.getMp3() + ".mp3");

        try {
            // Obtenemos el ID del recurso de forma segura usando el nombre del mp3
            int resId = context.getResources().getIdentifier(f.getMp3(), "raw", context.getPackageName());

            // Copiamos el archivo usando un buffer (más eficiente que is.available())
            try (InputStream is = context.getResources().openRawResource(resId);
                 OutputStream os = new FileOutputStream(archivo)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
            }

            // Crear URI con FileProvider
            Uri uri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileprovider", archivo);

            // Lanzar Intent de compartir
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("audio/mpeg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(intent, "Compartir con"));

        } catch (Exception e) {
            Log.e("ToneManager", "Error al preparar archivo para compartir", e);
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
