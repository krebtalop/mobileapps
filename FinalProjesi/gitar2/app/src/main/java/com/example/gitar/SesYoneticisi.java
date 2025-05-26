package com.example.gitar;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import java.util.HashMap;
import java.util.Map;

public class SesYoneticisi {
    private SoundPool sesHavuzu;  // Sesleri yönetmek için kullanılan SoundPool nesnesi
    private Map<Integer, Integer> sesHaritasi;  // Hangi telin hangi ses kaynağını kullanacağını belirten harita
    private Context baglam;  // Uygulama bağlamı, ses kaynaklarının yüklenmesinde kullanılır

    // Constructor: SesYoneticisi sınıfı bir context alarak başlatılır
    public SesYoneticisi(Context context) {
        this.baglam = context;  // Context'in atanması
        sesHaritasi = new HashMap<>();  // Harita başlatılıyor, seslerin id'lerini saklamak için

        // AudioAttributes, ses özelliklerini belirler: müzik için kullanılacak ve medya için ayarlanacak
        AudioAttributes ozellikler = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)  // Seslerin medya kullanımına uygun olarak ayarlanması
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)  // İçerik tipi müzik olarak belirleniyor
                .build();

        // SoundPool, seslerin yüklenip çalınmasını sağlayan bir sınıf, burada özellikleriyle başlatılıyor
        sesHavuzu = new SoundPool.Builder()
                .setAudioAttributes(ozellikler)  // Ses özellikleri ekleniyor
                .setMaxStreams(6)  // En fazla 6 ses aynı anda çalınabilir
                .build();
    }

    // Gitar nesnesindeki her bir teli ve onun ses kaynağını yükler
    public void sesleriYukle(Gitar gitar) {
        // Gitarın tüm tellerini döngüyle gezerek seslerini yükler
        for (int i = 0; i < gitar.tumTelleriGetir().size(); i++) {
            GitarTeli tel = gitar.getTel(i);  // Her bir telin bilgisi alınıyor
            // Ses kaynağını yükleyip, ses id'sini haritaya ekler
            int sesId = sesHavuzu.load(baglam, tel.getSesKaynagiId(), 1);  // Ses kaynağını yükle
            sesHaritasi.put(i, sesId);  // Haritaya id'yi ekle
        }
    }

    // İlgili notayı çalar
    public void sesCal(int notIndeksi) {
        Integer sesId = sesHaritasi.get(notIndeksi);  // Haritadan ses id'sini alır
        if (sesId != null) {
            // Ses varsa, ses havuzunda bu sesi çalar
            sesHavuzu.play(sesId, 1, 1, 0, 0, 1);  // Ses çal
        }
    }
}
