package com.st0leAndBlood.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MyActivity extends Activity {
    MediaPlayer mp;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //mp=MediaPlayer.create(getApplicationContext(), R.raw.beep);

        Button button = (Button) findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag == 0) {
                    mp = MediaPlayer.create(getApplicationContext(),
                            R.raw.beep);
                    mp.start();
                    flag++;
                } else {
                    mp.stop();
                    mp.release();
                    flag = 0;
                }
            }
        });
    }

}
