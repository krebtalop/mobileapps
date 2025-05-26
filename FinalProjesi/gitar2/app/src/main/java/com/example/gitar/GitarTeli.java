package com.example.gitar;

public class GitarTeli {
    // Private erişim düzeyine sahip özellikler
    private String isim;
    private int sesKaynagiId;

    // Constructor
    public GitarTeli(String isim, int sesKaynagiId) {
        this.isim = isim;
        this.sesKaynagiId = sesKaynagiId;
    }

    // Getter metodu: Bu metod dışarıdan 'isim' değerine erişmek için kullanılır
    public String getIsim() {
        return isim;
    }

    // Getter metodu: Bu metod dışarıdan 'sesKaynagiId' değerine erişmek için kullanılır
    public int getSesKaynagiId() {
        return sesKaynagiId;
    }

    // Setter metodları: Eğer gereksinim duyuluyorsa, dışarıdan bu özelliklere yeni değer atamak için setterlar eklenebilir
    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setSesKaynagiId(int sesKaynagiId) {
        this.sesKaynagiId = sesKaynagiId;
    }
}
