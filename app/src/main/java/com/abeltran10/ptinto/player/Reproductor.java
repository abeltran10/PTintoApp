package com.abeltran10.ptinto.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.abeltran10.ptinto.data.Frase;
import com.abeltran10.ptinto.data.FraseRepository;

public class Reproductor extends Service implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String nombreFrase = (intent != null) ? intent.getStringExtra("frase") : null;

        if (nombreFrase != null) {
            // Buscamos el nombre del archivo MP3 en el repositorio
            Frase f = FraseRepository.buscarPorNombre(nombreFrase);

            if (f != null) {
                // Liberamos el player anterior si existe antes de crear uno nuevo
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                int resId = getResources().getIdentifier(f.getMp3(), "raw", getPackageName());
                if (resId != 0) {
                    mediaPlayer = MediaPlayer.create(this, resId);
                    mediaPlayer.setOnPreparedListener(this);
                }
            }
        }
        return START_NOT_STICKY; // START_STICKY es para servicios que deben reiniciarse si mueren, no para sonidos cortos
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
