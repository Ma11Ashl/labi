package com.example.a5labpop;

// Импортируем необходимые классы из библиотек Android
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

// Класс SettingsActivity, расширяющий AppCompatActivity
public class SettingsActivity extends AppCompatActivity {

    // Приватные переменные для работы с настройками
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Метод onCreate, вызывается при создании активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Устанавливаем макет для активности

        // Получаем объект SharedPreferences для работы с настройками приложения
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Получаем объект Editor для редактирования настроек
        editor = sharedPreferences.edit();

        // Находим RadioButton по их ID
        final RadioButton whiteButton = findViewById(R.id.white_button);
        final RadioButton blueButton = findViewById(R.id.blue_button);
        final RadioButton greenButton = findViewById(R.id.green_button);

        // Находим кнопку сохранения по ее ID
        Button saveButton = findViewById(R.id.save_button);

        // Устанавливаем слушатель нажатий на кнопку сохранения
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor;
                // Проверяем, какая из кнопок RadioButton выбрана
                if (whiteButton.isChecked()) {
                    backgroundColor = Color.WHITE;
                } else if (blueButton.isChecked()) {
                    backgroundColor = Color.BLUE;
                } else if (greenButton.isChecked()) {
                    backgroundColor = Color.GREEN;
                } else {
                    backgroundColor = Color.WHITE; // Устанавливаем белый цвет по умолчанию
                }

                // Сохраняем выбранный цвет фона в настройках
                editor.putInt("background_color", backgroundColor);
                editor.apply(); // Применяем изменения
                finish(); // Завершаем текущую активность и возвращаемся к предыдущей
            }
        });
    }
}
