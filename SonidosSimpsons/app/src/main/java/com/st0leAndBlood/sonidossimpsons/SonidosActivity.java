package com.st0leAndBlood.sonidossimpsons;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class SonidosActivity extends Activity {

    private int soundID;
    boolean loaded = false;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidos);

        final Button button = (Button) findViewById(R.id.primerLboton);
        mediaPlayer = MediaPlayer.create(this, R.raw.beep);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer.start();
            }
        });
    }

}
