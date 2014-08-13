package com.st0leAndBlood.sonidossimpsons;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SonidosActivity extends Activity {

    private int soundID;
    boolean loaded = false;
    SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidos);
        final Button button = (Button) findViewById(R.id.primerLboton);
        setSoundPool();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float volume = actualVolume / maxVolume;

                if (loaded) {
                    soundPool.stop(soundID);
                    soundPool.play(soundID, volume, volume, 1, 0, 1f);

                }

            }
        });
    }

    private void setSoundPool(){
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                loaded= true;
            }
        });

        soundID = soundPool.load(this, R.raw.beep, 1);
    }

}
