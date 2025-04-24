package com.example.a6labyah;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    float x;
    private Handler handler = new Handler();
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Инициализирует активность и устанавливает начальное значение x = -30
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = -30;
        resultText = findViewById(R.id.result_text);
        resultText.setText("Результат: x = " + x + ", F = 0");
    }

    public void onClick(View v) { // Передаёт текущее значение x, handler и саму активность (this) в Scheduler. Запускает задачу через 1 секунду увеличивает x на 1.
        Timer timer = new Timer();
        Scheduler scheduler = new Scheduler();
        scheduler.x = x;
        scheduler.setHandler(handler);
        scheduler.setActivity(this);
        timer.schedule(scheduler, 1000);
        x++;
    }

    // Новый метод для обновления текста на экране
    public void updateResultText(float x, float F) {
        runOnUiThread(() -> {
            resultText.setText("Результат: x = " + x + ", F = " + F);
        });
    }
}