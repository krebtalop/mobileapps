package com.example.gitar;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private SesYoneticisi sesYoneticisi;
    private Gitar gitar;
    private int lastPlayedButtonId = -1; // Aynı buton üst üste çalınmasın

    private Button[] teller; // Telleri diziye alacağız ki kolayca kontrol edelim

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Şarkı çalma butonuna tıklanınca SongActivity'ye geçiş
        Button buttonSongActivity = findViewById(R.id.button_song_activity);
        buttonSongActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SongActivity.class);
            startActivity(intent);
        });

        gitar = new Gitar();
        sesYoneticisi = new SesYoneticisi(this);
        sesYoneticisi.sesleriYukle(gitar);

        // Gitar tellerine karşılık gelen butonları tanımlıyoruz
        Button buttonE = findViewById(R.id.button_e);
        Button buttonA = findViewById(R.id.button_a);
        Button buttonD = findViewById(R.id.button_d);
        Button buttonG = findViewById(R.id.buttonn_bm);
        Button buttonB = findViewById(R.id.button_b);
        Button buttonE2 = findViewById(R.id.button_e2);

        // Butonlara tıklama olayları ekliyoruz
        buttonE.setOnClickListener(v -> sesYoneticisi.sesCal(0));
        buttonA.setOnClickListener(v -> sesYoneticisi.sesCal(1));
        buttonD.setOnClickListener(v -> sesYoneticisi.sesCal(2));
        buttonG.setOnClickListener(v -> sesYoneticisi.sesCal(3));
        buttonB.setOnClickListener(v -> sesYoneticisi.sesCal(4));
        buttonE2.setOnClickListener(v -> sesYoneticisi.sesCal(5));

        // Butonları bir diziye alıyoruz
        teller = new Button[]{buttonE, buttonA, buttonD, buttonG, buttonB, buttonE2};

        // Çıkış butonuna tıklanınca Firebase çıkışı yapılır
        Button cikisButton = findViewById(R.id.button_cikis_yap);
        cikisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); // Firebase çıkış işlemi
                Intent intent = new Intent(MainActivity.this, girisekrani.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Geri tuşuyla geri dönülmesin
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getRawX();
            float y = event.getRawY();

            for (Button tel : teller) {
                Rect rect = new Rect();
                tel.getGlobalVisibleRect(rect);

                if (rect.contains((int) x, (int) y)) {
                    if (tel.getId() != lastPlayedButtonId) {
                        tel.performClick(); // Sesi çal
                        lastPlayedButtonId = tel.getId();
                    }
                    break;
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            lastPlayedButtonId = -1; // Parmağı kaldırınca sıfırla
        }

        return super.dispatchTouchEvent(event);
    }
}
