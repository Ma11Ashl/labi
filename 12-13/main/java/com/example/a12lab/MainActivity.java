package com.example.a12lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация GestureDetector
        gestureDetector = new GestureDetector(this, new GestureListener());

        Button buttonPlay = findViewById(R.id.button_play);
        Button buttonSettings = findViewById(R.id.button_settings);
        Button buttonScores = findViewById(R.id.button_scores);

        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        buttonScores.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScoresActivity.class);
            startActivity(intent);
        });

        // Устанавливаем обработчик касаний для корневого layout
        View rootView = findViewById(android.R.id.content);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    // Класс для обработки жестов
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                // Горизонтальный свайп
                if (diffX > 0) {
                    // Свайп вправо
                    Toast.makeText(MainActivity.this, "Право", Toast.LENGTH_SHORT).show();
                } else {
                    // Свайп влево
                    Toast.makeText(MainActivity.this, "Лево", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}