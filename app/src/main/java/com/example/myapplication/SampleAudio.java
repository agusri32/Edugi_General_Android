package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;

public class SampleAudio extends AppCompatActivity {

    MediaPlayer audioplayer;
    AudioManager audiomanager;

    public void klikkembalimenu(View view){
        Intent intent = new Intent(SampleAudio.this, MenuActivity.class);
        startActivityForResult(intent,0);
    }

    public void playaudio(View view){
        audioplayer = MediaPlayer.create(this,R.raw.tilawah);
        audioplayer.start();
    }

    public void stopaudio(View view){
        audioplayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //menghilangkan action bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_audio);

        //implementasi volume sound controller
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        timesTableSeekBar.setMax(maxVolume);
        timesTableSeekBar.setProgress(curVolume);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}