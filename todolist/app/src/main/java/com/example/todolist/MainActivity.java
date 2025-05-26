package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ListView list1 = (ListView) findViewById(R.id.lvSehirler);
        TextView text1 = (TextView) findViewById(R.id.tvSehirAdi);

        String[] sehirler = {"istanbul","ankara","izmir","kocaeli"};
        ArrayAdapter<String> verilisetesi = new ArrayAdapter<>( context: MainActivity.this,android.R.layout.activity_list_item,sehirler);







    }
}