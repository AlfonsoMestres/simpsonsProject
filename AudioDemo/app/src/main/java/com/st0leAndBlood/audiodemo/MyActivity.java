package com.st0leAndBlood.audiodemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity implements OnClickListener {
    private static final String TAG = "AudioDemo";
    private static final String isPlaying = "Media is Playing";
    private static final String notPlaying = "Media has stopped Playing";

    MediaPlayer player;
    Button playerButton;

    public void onClick(View v) {
        if (v.getId() == R.id.play) {
            playPause();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        player = MediaPlayer.create(this, R.raw.beep);
        player.setLooping(false); // Set looping

        // Get the button from the view
        playerButton = (Button) this.findViewById(R.id.play);
        playerButton.setText(R.string.stop_label);
        playerButton.setOnClickListener(this);

        // Begin playing selected media
        demoPlay();

    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }

    // Initiate media player pause
    private void demoPause(){
        player.pause();
        playerButton.setText(R.string.play_label);
    }

    // Initiate playing the media player
    private void demoPlay(){
        player.start();
        playerButton.setText(R.string.stop_label);
    }

    // Toggle between the play and pause
    private void playPause() {
        if(player.isPlaying()) {
            demoPause();
        } else {
            demoPlay();
        }
    }

    @Override
    public void onDestroy() {
        super.onPause();
        player.release();
    }

}
