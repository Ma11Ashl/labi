package com.example.a5labpop;

// Импортируем необходимые классы из библиотек Android
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

// Основной класс MainActivity, расширяющий AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Приватные переменные для работы с настройками и макетом RelativeLayout
    private SharedPreferences sharedPreferences;
    private RelativeLayout mainLayout;

    // Метод onCreate, вызывается при создании активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем макет для активности

        mainLayout = findViewById(R.id.main_layout); // Находим RelativeLayout по его ID

        // Находим кнопку настроек по ее ID
        Button settingsButton = findViewById(R.id.settings_button);

        // Получаем объект SharedPreferences для работы с настройками приложения
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        int defaultColor = Color.WHITE; // Устанавливаем белый цвет по умолчанию
        // Получаем цвет фона из настроек, если он там отсутствует, используем цвет по умолчанию
        int backgroundColor = sharedPreferences.getInt("background_color", defaultColor);
        // Устанавливаем цвет фона для RelativeLayout
        mainLayout.setBackgroundColor(backgroundColor);

        // Устанавливаем слушатель нажатий на кнопку настроек
        settingsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    // Метод onResume, вызывается перед тем, как активность становится видимой для пользователя
    @Override
    protected void onResume() {
        super.onResume();
        int defaultColor = Color.WHITE; // Устанавливаем белый цвет по умолчанию
        // Получаем цвет фона из настроек, если он там отсутствует, используем цвет по умолчанию
        int backgroundColor = sharedPreferences.getInt("background_color", defaultColor);
        // Устанавливаем цвет фона для RelativeLayout
        mainLayout.setBackgroundColor(backgroundColor);
    }
}
