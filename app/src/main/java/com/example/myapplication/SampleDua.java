package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class SampleDua extends AppCompatActivity {

    MediaPlayer audioplayer;
    AudioManager audiomanager;

    public void klikkembalidua(View view){
        Intent intent = new Intent(SampleDua.this, WelcomeActivity.class);
        startActivityForResult(intent,0);
    }

    public void playaudio(View view){
        audioplayer = MediaPlayer.create(this,R.raw.alfatihah);
        audioplayer.start();
    }

    public void stopaudio(View view){
        audioplayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampledua);

        //implementasi volume sound controller
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seeking value",Integer.toString(progress));
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