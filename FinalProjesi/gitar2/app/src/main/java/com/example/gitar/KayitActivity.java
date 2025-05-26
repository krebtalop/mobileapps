package com.example.gitar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class KayitActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnKayitOl, btnGirisEkraninaGit;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        // Layout'tan gerekli alanlar alınıyor
        editEmail = findViewById(R.id.edit_yeni_kullanici_adi);
        editPassword = findViewById(R.id.edit_yeni_sifre);
        btnKayitOl = findViewById(R.id.btn_kayit_tamamla);
        btnGirisEkraninaGit = findViewById(R.id.btnGirisEkraninaGit);

        // Firebase Authentication başlatılıyor
        auth = FirebaseAuth.getInstance();

        // Kayıt ol butonuna tıklandığında kullanıcı kaydını yapıyoruz
        btnKayitOl.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            // Firebase ile kullanıcı oluşturuluyor
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Kayıt başarısız: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Giriş ekranına git butonuna tıklandığında girisekrani'ne yönlendiriliyor
        btnGirisEkraninaGit.setOnClickListener(v -> {
            startActivity(new Intent(this, girisekrani.class));
            finish();
        });
    }
}
