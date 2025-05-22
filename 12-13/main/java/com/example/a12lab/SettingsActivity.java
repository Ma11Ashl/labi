
package com.example.a12lab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SeekBar speedSeekBar = findViewById(R.id.seekbar_speed);
        SharedPreferences preferences = getSharedPreferences("snake_prefs", MODE_PRIVATE);

        speedSeekBar.setProgress(preferences.getInt("game_speed", 50));
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Обновление настройки скорости
                preferences.edit().putInt("game_speed", progress).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}