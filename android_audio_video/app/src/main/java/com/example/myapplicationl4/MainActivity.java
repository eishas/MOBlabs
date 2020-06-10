package com.example.myapplicationl4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent_audio;
    private Intent intent_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Video(View view) {
        if(intent_video == null)
            intent_video = new Intent(this, VideoActivity.class);

        startActivity(intent_video);
    }

    public void Audio(View view) {
        if(intent_audio == null)
            intent_audio = new Intent(this, AudioActivity.class);

        startActivity(intent_audio);

    }

}
