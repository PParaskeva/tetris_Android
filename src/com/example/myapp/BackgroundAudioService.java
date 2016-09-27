package com.example.myapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import android.media.MediaPlayer.OnCompletionListener;


public class BackgroundAudioService extends Service implements OnCompletionListener {
    MediaPlayer music;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        music = MediaPlayer.create(this, R.raw.originaltetristheme);// raw/s.mp3
        music.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!music.isPlaying()) {
            music.start();
        }
        return START_STICKY;
    }

    public void onDestroy() {
        if (music.isPlaying()) {
            music.stop();
        }
        music.release();
    }

    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }

}
