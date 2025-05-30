package com.example.gitar;

import java.util.ArrayList;
import java.util.List;

public class Gitar {
    private List<GitarTeli> teller;

    public Gitar() {
        teller = new ArrayList<>();
        // 6 telli klasik gitar tanımı (örnek sesKaynagiId'leri placeholder)
        teller.add(new GitarTeli("E", R.raw.e));
        teller.add(new GitarTeli("A", R.raw.a));
        teller.add(new GitarTeli("D", R.raw.d));
        teller.add(new GitarTeli("G", R.raw.g));
        teller.add(new GitarTeli("B", R.raw.b));
        teller.add(new GitarTeli("e", R.raw.e2));
    }

    public GitarTeli getTel(int indeks) {
        return teller.get(indeks);
    }

    public List<GitarTeli> tumTelleriGetir() {
        return teller;
    }
}
