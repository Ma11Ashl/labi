package com.example.a12lab;

import android.content.SharedPreferences; // Для SharedPreferences
import java.util.HashSet; // Для HashSet
import java.util.Set; // Для Set
import java.util.List; // Для List
import java.util.ArrayList; // Для ArrayList
import android.widget.ArrayAdapter; // Для ArrayAdapter
import android.widget.ListView; // Для ListView
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ListView listView = findViewById(R.id.list_scores);

        // Загружаем сохраненные рекорды
        SharedPreferences preferences = getSharedPreferences("snake_prefs", MODE_PRIVATE);
        Set<String> scoresSet = preferences.getStringSet("scores", new HashSet<>());

        List<String> scoresList = new ArrayList<>(scoresSet);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresList);

        listView.setAdapter(adapter);
    }
}