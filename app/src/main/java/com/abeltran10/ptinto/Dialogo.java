package com.abeltran10.ptinto;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class Dialogo extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String titulo = getArguments().getString("titulo");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_NoActionBar);
        builder.setTitle(titulo);
        builder.setItems(new String[]{"Tono de llamada", "Tono de notificación", "Tono de alarma", "Compartir"}, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int posicion) {
                if (posicion != 3) {
                    Dialogo.this.establecerTono(titulo, posicion);
                } else {
                    Dialogo.this.compartirFrase(titulo);
                }
            }
        });
        return builder.create();
    }

    public void establecerTono(String frase, int tipo) {
        File ruta;
        Vector<Frase> listadoFrases = Frase.listaFrases;
        String audio = "";
        String mp3 = "";
        int i = 0;
        while (i < listadoFrases.size()) {
            if (listadoFrases.elementAt(i).frase.equals(frase)) {
                audio = listadoFrases.elementAt(i).frase;
                mp3 = listadoFrases.elementAt(i).mp3;
                break;
            }

            ++i;
        }
        switch (tipo) {
            case 0:
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
                break;
            case 1:
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS);
                break;
            case 2:
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);
                break;
            default:
                ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
                break;
        }
        File archivo = new File(ruta, mp3 + ".mp3");
        if ("mounted".equals(Environment.getExternalStorageState())) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                escribirSDcard(ruta, archivo, mp3);

                Uri uri = insertarContent(archivo, audio, tipo);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(getContext())) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getActivity().getApplicationInfo().packageName));
                        startActivity(intent);
                    } else {
                        setRingstone(tipo, uri);
                        Toast.makeText(getActivity(), "Tono " + frase + " establecido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (ContextCompat
                            .checkSelfPermission(getActivity(), Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED) {
                        setRingstone(tipo, uri);
                        Toast.makeText(getActivity(), "Tono " + frase + " establecido", Toast.LENGTH_SHORT).show();
                    } else
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_SETTINGS}, 2);
                }
            } else
                ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        }

    }

    public void escribirSDcard(File ruta, File archivo, String mp3) {
        try {
            ruta.mkdirs();
            InputStream is = getResources().openRawResource(getResources().getIdentifier(mp3,
                    "raw", getActivity().getApplicationInfo().packageName));
            OutputStream os = new FileOutputStream(archivo);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + archivo, e);
        }
    }

    public Uri insertarContent(File archivo, String audio, int tipo) {
        Cursor mCursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{"_id", "_data"}, "_data=?", new String[]{archivo.getAbsolutePath().toString()}, (String) null);
        if (mCursor == null || mCursor.getCount() < 1) {
            ContentValues values = new ContentValues();
            values.put("mime_type", "audio/mpeg3");
            values.put("title", audio);
            values.put("_data", archivo.getAbsolutePath());
            switch (tipo) {
                case 0:
                    values.put("is_ringtone", true);
                    break;
                case 1:
                    values.put("is_notification", true);
                    break;
                case 2:
                    values.put("is_alarm", true);
                    break;
            }
            return getActivity().getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
        }
        mCursor.moveToNext();
        return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                (long) Integer.parseInt(mCursor.getString(mCursor.getColumnIndex("_id"))));
    }

    public void compartirFrase(String frase) {
        Vector<Frase> listadoFrases = Frase.listaFrases;
        String mp3 = "";
        int i = 0;
        while (i < listadoFrases.size()) {
            if (listadoFrases.elementAt(i).frase.equals(frase)) {
                mp3 = listadoFrases.elementAt(i).mp3;
                break;
            }

            ++i;
        }
        File ruta = new File(getActivity().getCacheDir(), "ptinto_compartido");
        File archivo = new File(ruta, mp3 + ".mp3");
        escribirSDcard(ruta, archivo, mp3);
        Uri uriMp3 = FileProvider.getUriForFile(getActivity().getApplicationContext(), "com.tinto.p.ptinto.fileprovider", archivo);
        Intent sendIntent = new Intent();
        sendIntent.setAction("android.intent.action.SEND");
        sendIntent.putExtra("android.intent.extra.STREAM", uriMp3);
        sendIntent.setData(uriMp3);
        sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sendIntent.setType("audio/mpeg3");
        startActivity(Intent.createChooser(sendIntent, frase));
    }

    void setRingstone (int tipo, Uri uri) {
        switch (tipo) {
            case 0:
                RingtoneManager.setActualDefaultRingtoneUri(getActivity().getApplicationContext(), RingtoneManager.TYPE_RINGTONE, uri);
                break;
            case 1:
                RingtoneManager.setActualDefaultRingtoneUri(getActivity().getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, uri);
                break;
            case 2:
                RingtoneManager.setActualDefaultRingtoneUri(getActivity().getApplicationContext(), RingtoneManager.TYPE_ALARM, uri);
                break;
        }

    }

}
