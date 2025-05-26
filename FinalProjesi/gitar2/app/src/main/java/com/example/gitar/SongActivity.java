package com.example.gitar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {

    private SesYoneticisi sesYoneticisi;
    private Gitar gitar;
    private TextView textViewAkorlariGoster;
    private Spinner spinnerSongs;
    private Button playSongButton;

    // Akorlar ve her akorun süresi
    private List<String> akorlar = new ArrayList<>();
    private List<Integer> akorSuresi = new ArrayList<>(); // Akorların çalınma süreleri (ms)

    // SoundPool tanımlaması
    private SoundPool soundPool;
    private int sounddC, sounddG, sounddAm, sounddF, sounddEm, sounddBm, sounddD, sounddA, sounddE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song); // activity_song.xml

        gitar = new Gitar();
        sesYoneticisi = new SesYoneticisi(this);

        // Akustik gitar ile sesleri yükle
        sesYoneticisi.sesleriYukle(gitar);

        // Spinner (dropdown list) ile şarkı seçim
        spinnerSongs = findViewById(R.id.spinner_songs);
        textViewAkorlariGoster = findViewById(R.id.textView_akorlari_goster);
        playSongButton = findViewById(R.id.button_play_song);

        // SoundPool yapılandırması
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(5)
                .build();

        // Akorların seslerini yükleme
        loadSounds();

        // Şarkı listesi
        List<String> songs = new ArrayList<>();
        songs.add("Knockin' on Heaven's Door");
        songs.add("Twinkle Twinkle Little Star");
        songs.add("Let It Be");
        songs.add("With or Without You");

        // Spinner için adapter ayarları
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSongs.setAdapter(adapter);

        // Spinner item seçildiğinde yapılacak işlem
        spinnerSongs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSong = (String) parentView.getItemAtPosition(position);
                // Şarkıyı seçtikten sonra, o şarkının akorları ve sürelerini ayarlayalım
                setSong(selectedSong);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Hiçbir şey seçilmediğinde yapılacak işlem
            }
        });

        // Play Song butonuna tıklanınca şarkıyı başlatacak
        playSongButton.setOnClickListener(v -> {
            Log.d("SongActivity", "Şarkıyı Çalma Başladı");
            calMuzik();
        });

        // Akor butonlarına tıklanma olayları
        Button buttonC = findViewById(R.id.buttonn_c);
        buttonC.setOnClickListener(v -> playSound("C"));

        Button buttonG = findViewById(R.id.buttonn_g);
        buttonG.setOnClickListener(v -> playSound("G"));

        Button buttonAm = findViewById(R.id.buttonn_am);
        buttonAm.setOnClickListener(v -> playSound("Am"));

        Button buttonF = findViewById(R.id.buttonn_f);
        buttonF.setOnClickListener(v -> playSound("F"));

        Button buttonDm = findViewById(R.id.buttonn_em);  // Burada buttonn_em kullanıyoruz, yani 'Em' akoru
        buttonDm.setOnClickListener(v -> playSound("Em"));

        Button buttonBm = findViewById(R.id.buttonn_bm);
        buttonBm.setOnClickListener(v -> playSound("Bm"));

        Button buttonD = findViewById(R.id.buttonn_d);
        buttonD.setOnClickListener(v -> playSound("D"));

        Button buttonA = findViewById(R.id.buttonn_a);
        buttonA.setOnClickListener(v -> playSound("A"));

        Button buttonE = findViewById(R.id.buttonn_e); // E akoru butonunu ekliyoruz
        buttonE.setOnClickListener(v -> playSound("E"));
    }

    // Akor seslerini yükleme
    private void loadSounds() {
        sounddC = soundPool.load(this, R.raw.s_c, 1);
        sounddG = soundPool.load(this, R.raw.s_g, 1);
        sounddAm = soundPool.load(this, R.raw.s_amajor, 1);
        sounddF = soundPool.load(this, R.raw.s_f, 1);
        sounddEm = soundPool.load(this, R.raw.s_emajor, 1);
        sounddBm = soundPool.load(this, R.raw.s_bmajor, 1);
        sounddD = soundPool.load(this, R.raw.s_d, 1);
        sounddA = soundPool.load(this, R.raw.s_a, 1);
        sounddE = soundPool.load(this, R.raw.s_e, 1); // 'E' akorunu da yükledik
    }

    // Şarkıyı seçtikten sonra akorları ve sürelerini ayarlayalım
    private void setSong(String songName) {
        // Akorları ve sürelerini sırasıyla belirleyelim
        akorlar.clear();
        akorSuresi.clear();

        switch (songName) {
            case "Knockin' on Heaven's Door":
                // Uzun versiyon: G, D, A, tekrarlar
                akorlar.add("G");
                akorlar.add("D");
                akorlar.add("A");
                akorlar.add("G");
                akorlar.add("D");
                akorlar.add("A");
                for (int i = 0; i < 6; i++) {
                    akorSuresi.add(1000); // Her akor için 1 saniye
                }
                break;

            case "Twinkle Twinkle Little Star":
                // Uzun versiyon: C, G, F, tekrarlar
                akorlar.add("C");
                akorlar.add("G");
                akorlar.add("C");
                akorlar.add("F");
                akorlar.add("C");
                akorlar.add("G");
                for (int i = 0; i < 6; i++) {
                    akorSuresi.add(1000); // Her akor için 1 saniye
                }
                break;

            case "Let It Be":
                // Uzun versiyon: C, G, Am, F, tekrarlar
                akorlar.add("C");
                akorlar.add("G");
                akorlar.add("Am");
                akorlar.add("F");
                akorlar.add("C");
                akorlar.add("G");
                for (int i = 0; i < 6; i++) {
                    akorSuresi.add(1000); // Her akor için 1 saniye
                }
                break;

            case "With or Without You":
                // Uzun versiyon: D, A, Bm, G, tekrarlar
                akorlar.add("D");
                akorlar.add("A");
                akorlar.add("Bm");
                akorlar.add("G");
                akorlar.add("D");
                akorlar.add("A");
                for (int i = 0; i < 6; i++) {
                    akorSuresi.add(1000); // Her akor için 1 saniye
                }
                break;
        }

        // Akorları TextView'de göstermek
        textViewAkorlariGoster.setText("Akorlar: " + String.join(", ", akorlar)); // Akorları yazdır
    }

    private void playSound(String akor) {
        switch (akor) {
            case "C":
                soundPool.play(sounddC, 1, 1, 0, 0, 1); // C
                break;
            case "G":
                soundPool.play(sounddG, 1, 1, 0, 0, 1); // G
                break;
            case "Am":
                soundPool.play(sounddAm, 1, 1, 0, 0, 1); // Am
                break;
            case "F":
                soundPool.play(sounddF, 1, 1, 0, 0, 1); // F
                break;
            case "Dm":
                soundPool.play(sounddEm, 1, 1, 0, 0, 1); // Dm
                break;
            case "Bm":
                soundPool.play(sounddBm, 1, 1, 0, 0, 1); // Bm
                break;
            case "D":
                soundPool.play(sounddD, 1, 1, 0, 0, 1); // D
                break;
            case "A":
                soundPool.play(sounddA, 1, 1, 0, 0, 1); // A
                break;
            case "E": // E akoru için ses çalma
                soundPool.play(sounddE, 1, 1, 0, 0, 1); // E akoru
                break;
        }
    }

    // Şarkıyı çalma fonksiyonu
    private void calMuzik() {
        new Thread(() -> {
            try {
                for (int i = 0; i < akorlar.size(); i++) {
                    String akor = akorlar.get(i);
                    Log.d("SongActivity", "Çalınan Akor: " + akor); // Log ile akorları takip et
                    runOnUiThread(() -> textViewAkorlariGoster.setText(akor)); // TextView'de akorları sırasıyla göster

                    // Akor sesini çal
                    playSound(akor);

                    // Akor süresi kadar bekle
                    Thread.sleep(akorSuresi.get(i));  // Akor süresi kadar bekle
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Nota adından index elde etme fonksiyonu
    private int getNotaIndex(String nota) {
        switch (nota) {
            case "C": return 0;
            case "G": return 1;
            case "Am": return 2;
            case "F": return 3;
            case "Dm": return 4;
            case "Bm": return 5;
            case "D": return 6;
            case "A": return 7;
            case "E": return 8;  // E akoru
            default: return -1;
        }
    }
}
