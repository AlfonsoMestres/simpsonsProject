package com.st0leAndBlood.sonidossimpsons;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class SonidosActivity extends Activity {
    int flag = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidos);

        final Button button = (Button) findViewById(R.id.primerLboton);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag == 0) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),
                            R.raw.beep);
                    mediaPlayer.start();
                    flag++;
                } else {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = 0;
                }
            }
        });
    }

    @Override
    protected void onDestroy(){
        mediaPlayer.release();
        super.onDestroy();
    }


}
