package com.example.a6labyah;

import android.os.Handler;
import android.util.Log;
import java.util.TimerTask;

public class Scheduler extends TimerTask {

    float x; // Хранит x из main
    private Handler handler;
    private MainActivity activity;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void run() { // Вычисляет значение функции F в зависимости от x
        float a = 2, b = 3, c = 4;
        float F;

        if (x < 5) {
            F = (float) (-a * Math.pow(x, 2) - b);
        } else if (x > 5) {
            F = (x - a) / x;
        } else {
            F = -x / c;
        }

        Log.i("Вывод", "x = " + x + ", F = " + F);

        // Обновляем текст на экране
        activity.updateResultText(x, F);

        if (handler != null && x < 50) {
            handler.post(() -> activity.onClick(null));
        }
    }
}