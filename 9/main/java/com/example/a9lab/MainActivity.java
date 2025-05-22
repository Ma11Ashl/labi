package com.example.a9lab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.LinearLayout.LayoutParams; // Импортируем правильный класс LayoutParams

public class MainActivity extends AppCompatActivity {
    private ImageView circleImage; // Объявляет приватную переменную для хранения ссылки на объект ImageView.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleImage = findViewById(R.id.circle_image);

        setSupportActionBar(findViewById(R.id.toolbar)); // Устанавливает настраиваемую панель инструментов (Toolbar) для поддержки действий.

        registerForContextMenu(circleImage); // Регистрирует контекстное меню для элемента circleImage.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Переопределяет метод для создания меню в панели инструментов.
        getMenuInflater().inflate(R.menu.main_menu, menu); // Загружает меню из ресурса main_menu.xml в объект Menu.
        return true; // Возвращает true, чтобы указать, что меню создано.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // Переопределяет метод, который вызывается при выборе элемента меню.
        int itemId = item.getItemId();
        if (itemId == R.id.move_left_option) {
            moveCircle(-20);
            return true;
        } else if (itemId == R.id.move_right_option) {
            moveCircle(20);
            return true;
        } else {
            return super.onOptionsItemSelected(item); // В противном случае вызывает базовый метод для обработки других элементов.
        }
    }

    private void moveCircle(int offsetX) {
        LayoutParams layoutParams = (LayoutParams) circleImage.getLayoutParams(); // Получает параметры макета текущего изображения.
        layoutParams.leftMargin += offsetX; // Смещает левый отступ на указанное значение.
        circleImage.setLayoutParams(layoutParams); // Применяет новые параметры макета к изображению.
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // Переопределяет метод для создания контекстного меню.
        super.onCreateContextMenu(menu, v, menuInfo); // Вызывает базовую реализацию метода.
        menu.setHeaderTitle("Изменить размер круга"); // Устанавливает заголовок контекстного меню.
        menu.add(0, 1, 0, "Маленький круг");
        menu.add(0, 2, 0, "Средний круг");
        menu.add(0, 3, 0, "Большой круг");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 1) {
            resizeCircle(100);
            return true;
        } else if (itemId == 2) {
            resizeCircle(200);
            return true;
        } else if (itemId == 3) {
            resizeCircle(300);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }


    private void resizeCircle(int size) { // Приватный метод для изменения размера круга.
        LayoutParams layoutParams = (LayoutParams) circleImage.getLayoutParams(); // Получает параметры макета текущего изображения.
        layoutParams.width = size;
        layoutParams.height = size;
        circleImage.setLayoutParams(layoutParams); // Применяет новые параметры макета к изображению.
    }
}
