package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем макет для этой активности

        // Получаем массив строк из ресурсов
        String[] array = getResources().getStringArray(R.array.string_array);
        // Создаем адаптер массива строк
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        // Устанавливаем адаптер для ListView
        ((ListView) findViewById(R.id.list)).setAdapter(adapter);

        // Устанавливаем слушатель кликов для элементов ListView
        ((ListView) findViewById(R.id.list)).setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                ((TextView)findViewById(R.id.for_text)).setText(String.valueOf(parent.getItemAtPosition(position)));
            }
        });
    }

    // Метод обработки клика на кнопку
    public void onBtnClick(View v){
        // Создаем интент для перехода на активность регистрации
        Intent intent = new Intent(this, RegistrationActivity.class);
        // Запускаем активность регистрации
        startActivity(intent);
    }
}
