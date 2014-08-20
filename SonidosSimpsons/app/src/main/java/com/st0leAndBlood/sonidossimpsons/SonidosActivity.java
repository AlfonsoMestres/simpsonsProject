package com.st0leAndBlood.sonidossimpsons;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;

public class SonidosActivity extends Activity {
    private int flag;
    MediaPlayer mediaPlayer;
    ImageButton button, button2;
    Animation animation;
    SoundHandlerTask soundHandlerTask;
    private long mLastClickTime1, mLastClickTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidos);

        button = (ImageButton) findViewById(R.id.btn1);
        button2 = (ImageButton) findViewById(R.id.btn2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        animation.setInterpolator(new LinearInterpolator());
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime1 < 300){
                    return;
                }
                mLastClickTime1 = SystemClock.elapsedRealtime();
                if (flag == 0) {
                    button.startAnimation(animation);
                    soundHandlerTask = new SoundHandlerTask(button, R.raw.beep, getApplicationContext());
                    soundHandlerTask.execute();
                    flag++;
                } else {
                    soundHandlerTask.btnThread.clearAnimation();
                    mediaPlayer.stop();
                    flag = 0;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime2 < 300){
                    return;
                }
                mLastClickTime2 = SystemClock.elapsedRealtime();
                if (flag == 0) {
                    button2.startAnimation(animation);
                    soundHandlerTask = new SoundHandlerTask(button2, R.raw.beep, getApplicationContext());
                    soundHandlerTask.execute();
                    flag++;
                } else {
                    soundHandlerTask.btnThread.clearAnimation();
                    mediaPlayer.stop();
                    flag = 0;
                }
            }
        });
    }

    private class SoundHandlerTask extends AsyncTask<Object, Void, Void> {

        ImageButton btnThread;
        Integer songId;
        Context context;

        public SoundHandlerTask(ImageButton btnThread, Integer songId, Context context){
            this.btnThread = btnThread;
            this.songId = songId;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Object... arg0) {
            mediaPlayer = MediaPlayer.create(context,songId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                    btnThread.clearAnimation();
                    flag = 0;
                    return;
                }
            });
            return null;
        }
    }

        @Override
        protected void onDestroy(){
            mediaPlayer.release();
            super.onDestroy();
        }
    }
