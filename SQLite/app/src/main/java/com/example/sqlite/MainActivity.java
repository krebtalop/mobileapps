package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button Kaydet, Goster, Sil, Guncelle;
    EditText ad, soyad, yas, sehir;
    TextView Bilgiler;
    private veritabani v1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = new veritabani(this);
        Kaydet = findViewById(R.id.button_kayıtekle);
        Goster = findViewById(R.id.button_kayitgoster);
        Sil = findViewById(R.id.button_kayitsil);
        Guncelle = findViewById(R.id.button_kayitguncelle);
        ad = findViewById(R.id.edittextad);
        soyad = findViewById(R.id.editTextsoyad);
        yas = findViewById(R.id.editTextyas);
        sehir = findViewById(R.id.editTextsehir);
        Bilgiler = findViewById(R.id.textView);

        Kaydet.setOnClickListener(view -> {
            Log.d(TAG, "Kayıt Ekle butonuna basıldı");
            KayitEkle(ad.getText().toString(), soyad.getText().toString(), yas.getText().toString(), sehir.getText().toString());
        });

        Goster.setOnClickListener(view -> {
            Log.d(TAG, "Kayıtları Göster butonuna basıldı");
            KayitGoster(KayitGetir());
        });

        Sil.setOnClickListener(view -> {
            Log.d(TAG, "Kayıt Sil butonuna basıldı");
            KayitSil(ad.getText().toString());
        });

        Guncelle.setOnClickListener(view -> {
            Log.d(TAG, "Kayıt Güncelle butonuna basıldı");
            KayitGuncelle(ad.getText().toString(), soyad.getText().toString(), yas.getText().toString(), sehir.getText().toString());
        });
    }

    private String[] sutunlar = {"ad", "soyad", "yas", "sehir"};

    private Cursor KayitGetir() {
        SQLiteDatabase db = v1.getWritableDatabase();
        return db.query("OgrenciBilgi", sutunlar, null, null, null, null, null);
    }
    private void KayitGoster(Cursor goster) {
        StringBuilder builder = new StringBuilder();
        try {
            while (goster.moveToNext()) {
                int columnIndexAd = goster.getColumnIndexOrThrow("ad");
                int columnIndexSoyad = goster.getColumnIndexOrThrow("soyad");
                int columnIndexYas = goster.getColumnIndexOrThrow("yas");
                int columnIndexSehir = goster.getColumnIndexOrThrow("sehir");

                String add = goster.getString(columnIndexAd);
                String soyadd = goster.getString(columnIndexSoyad);
                String yass = goster.getString(columnIndexYas);
                String sehrir = goster.getString(columnIndexSehir);

                builder.append("Ad: ").append(add).append("\n");
                builder.append("Soyad: ").append(soyadd).append("\n");
                builder.append("Yaş: ").append(yass).append("\n");
                builder.append("Şehir: ").append(sehrir).append("\n");
                builder.append("--------------").append("\n");
            }
            Bilgiler.setText(builder.toString());
        } catch (Exception e) {
            Log.e(TAG, "Kayıt gösterilirken hata oluştu: " + e.getMessage());
        } finally {
            goster.close();
        }
    }

    private void KayitEkle(String adi, String soyadi, String yasi, String sehri) {
        try {
            SQLiteDatabase db = v1.getWritableDatabase();
            ContentValues veriler = new ContentValues();
            veriler.put("ad", adi);
            veriler.put("soyad", soyadi);
            veriler.put("yas", Integer.parseInt(yasi)); // Yaşı integer olarak ekleyin
            veriler.put("sehir", sehri);
            db.insertOrThrow("OgrenciBilgi", null, veriler);
            db.close();
            Log.d(TAG, "Kayıt başarıyla eklendi.");
        } catch (Exception e) {
            Log.e(TAG, "Kayıt eklenirken hata oluştu: " + e.getMessage());
        }
    }

    private void KayitSil(String adi) {
        try {
            SQLiteDatabase db = v1.getWritableDatabase();
            int rows = db.delete("OgrenciBilgi", "ad=?", new String[]{adi});
            db.close();
            Log.d(TAG, rows + " kayıt silindi.");
        } catch (Exception e) {
            Log.e(TAG, "Kayıt silinirken hata oluştu: " + e.getMessage());
        }
    }

    private void KayitGuncelle(String adi, String soyadi, String yasi, String sehri) {
        try {
            SQLiteDatabase db = v1.getWritableDatabase();
            ContentValues cvGuncelle = new ContentValues();
            cvGuncelle.put("soyad", soyadi); // "ad" güncellenmemeli
            cvGuncelle.put("yas", Integer.parseInt(yasi)); // Yaşı integer olarak güncelleyin
            cvGuncelle.put("sehir", sehri);
            int rows = db.update("OgrenciBilgi", cvGuncelle, "ad=?", new String[]{adi});
            db.close();
            Log.d(TAG, rows + " kayıt güncellendi.");
        } catch (Exception e) {
            Log.e(TAG, "Kayıt güncellenirken hata oluştu: " + e.getMessage());
        }
    }
}