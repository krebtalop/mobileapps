package com.example.guitar_app;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // MediaPlayer nesnesi
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tel 1 Button
        Button buttonString1 = findViewById(R.id.buttonString1);
        buttonString1.setOnClickListener(v -> playSound(R.raw.relax));

        // Tel 2 Button
        Button buttonString2 = findViewById(R.id.buttonString2);
        buttonString2.setOnClickListener(v -> playSound(R.raw.relax));

        // Tel 3 Button
        Button buttonString3 = findViewById(R.id.buttonString3);
        buttonString3.setOnClickListener(v -> playSound(R.raw.relax));

        // Tel 4 Button
        Button buttonString4 = findViewById(R.id.buttonString4);
        buttonString4.setOnClickListener(v -> playSound(R.raw.relax));

        // Tel 5 Button
        Button buttonString5 = findViewById(R.id.buttonString5);
        buttonString5.setOnClickListener(v -> playSound(R.raw.relax));

        // Tel 6 Button
        Button buttonString6 = findViewById(R.id.buttonString6);
        buttonString6.setOnClickListener(v -> playSound(R.raw.relax));
    }

    // Ses çalma fonksiyonu
    private void playSound(int soundResource) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, soundResource);
        } else {
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(MainActivity.this, soundResource);
        }
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
