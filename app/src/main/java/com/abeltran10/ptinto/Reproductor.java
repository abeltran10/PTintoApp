package com.abeltran10.ptinto;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import java.util.Vector;

public class Reproductor extends Service implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaplayer = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.mediaplayer = MediaPlayer.create(getApplicationContext(), getResources()
                .getIdentifier(buscarFraseMp3(intent.getStringExtra("frase")), "raw",
                        getApplicationInfo().packageName));
        this.mediaplayer.setOnPreparedListener(this);
        return Service.START_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        this.mediaplayer.stop();
        super.onDestroy();
    }


    public String buscarFraseMp3(String frase) {
        Vector<Frase> listFrase = ((Aplicacion)getApplication()).getListaFrases();
        for (int i = 0; i < listFrase.size(); i++) {
            if (frase.equals(listFrase.elementAt(i).frase)) {
                return listFrase.elementAt(i).mp3;
            }
        }
        return "";
    }
}
