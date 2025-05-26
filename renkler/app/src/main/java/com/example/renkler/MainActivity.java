package com.example.renkler;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final int[] oran1 = new int[1];
        final int[] oran2 = {0};
        int oran3 = 0;

        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBar1);
        SeekBar sb2 = (SeekBar) findViewById(R.id.seekBar2);
        SeekBar sb3 = (SeekBar) findViewById(R.id.seekBar3);

        TextView txt1 = (TextView) findViewById(R.id.textView);
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        TextView txt3 = (TextView) findViewById(R.id.textView3);


        ConstraintLayout arkarenk=(ConstraintLayout) findViewById(R.id.main);



        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sb1.setMax(255);
                int oran= Color.rgb(oran3, oran2[0],i);
                arkarenk.setBackgroundColor(oran);
                txt1.setText(String.valueOf(i));
                oran1[0] =i;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}


        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sb1.setMax(255);
                int oran= Color.rgb(oran3, oran2[0],i);
                arkarenk.setBackgroundColor(oran);
                txt2.setText(String.valueOf(i));
                oran1[0] =i;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}


        });

        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sb1.setMax(255);
                int oran= Color.rgb(oran3, oran2[0],i);
                arkarenk.setBackgroundColor(oran);
                txt3.setText(String.valueOf(i));
                oran1[0] =i;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}


        });
    }
}