package com.example.a4laba;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    SQLHelper database; // Объект для работы с базой данных
    LinearLayout listLinearLayout; // Layout для списка маршрутов
    LinearLayout row; // Layout для строки маршрута

    Dialog addNewRouteDialog; // Диалоговое окно для добавления маршрута
    Dialog deleteRouteDialog; // Диалоговое окно для удаления маршрута
    Dialog updateRouteDialog; // Диалоговое окно для обновления маршрута
    Dialog showRouteByIDDialog; // Диалоговое окно для отображения маршрута по ID

    ArrayList<TrainRoute> trainRoutes; // Список маршрутов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Установка макета для активити
        database = new SQLHelper(this); // Инициализация объекта для работы с базой данных

        listLinearLayout = findViewById(R.id.list); // Поиск Layout для списка маршрутов по ID

        // Инициализация диалоговых окон
        addNewRouteDialog = new Dialog(this);
        addNewRouteDialog.setContentView(R.layout.add_new_route_dialog);

        showRouteByIDDialog = new Dialog(this);
        showRouteByIDDialog.setContentView(R.layout.show_by_id_dialog);

        updateRouteDialog = new Dialog(this);
        updateRouteDialog.setContentView(R.layout.update_route_dialog);

        deleteRouteDialog = new Dialog(this);
        deleteRouteDialog.setContentView(R.layout.delete_route_dialog);
    }

    // Метод для вывода записи по id
    public void showTrainRoute(int id){

        TrainRoute t = trainRoutes.get(id-1); // Получение маршрута из списка по его ID

        row = new LinearLayout(this); // Создание нового Layout для строки маршрута
        row.setOrientation(LinearLayout.HORIZONTAL); // Установка горизонтальной ориентации для Layout

        // Создание TextView для отображения информации о маршруте
        TextView Id = new TextView(this);
        Id.setText( Integer.toString(t._id));
        Id.setWidth(120);

        TextView route = new TextView(this);
        route.setText(t.route);
        route.setWidth(500);

        TextView departureDate = new TextView(this);
        departureDate.setText(t.departureDate);
        departureDate.setWidth(120);

        TextView arrivalDate = new TextView(this);
        arrivalDate.setText(t.arrivalDate);
        arrivalDate.setWidth(120);

        // Добавление TextView в Layout
        row.addView(Id);
        row.addView(route);
        row.addView(departureDate);
        row.addView(arrivalDate);

        listLinearLayout.addView(row); // Добавление Layout в список маршрутов
    }

    // Обработчик для вывода всех записей на экран
    public void showAllRoutes(View view){
        trainRoutes = getList(); // Получение списка всех маршрутов из базы данных
        listLinearLayout.removeAllViews(); // Удаление всех представлений маршрутов из списка
        for(int i = 1; i < trainRoutes.size()+1; i++) {
            showTrainRoute(i); // Вывод каждого маршрута на экран
        }
    }

    // Обработчик для открытия диалогового окна добавления записи
    public void onClickAddButton(View v){
        addNewRouteDialog.show(); // Отображение диалогового окна для добавления маршрута
    }

    public void onClickAddConfirmationButton(View v){
        // Получение данных о новом маршруте из полей ввода диалогового окна
        EditText routeNameEditText = addNewRouteDialog.findViewById(R.id.route_name_edittext);
        EditText departureDateEditText = addNewRouteDialog.findViewById(R.id.departure_date_edittext);
        EditText arrivalDateEditText = addNewRouteDialog.findViewById(R.id.arrival_date_edittext);
        String routeName = routeNameEditText.getText().toString();
        String departureDate = departureDateEditText.getText().toString();
        String arrivalDate = arrivalDateEditText.getText().toString();
        // Добавление нового маршрута в базу данных
        database.addTrainRoute(routeName, departureDate, arrivalDate);
        // Очистка полей ввода
        routeNameEditText.setText("");
        departureDateEditText.setText("");
        arrivalDateEditText.setText("");
        // Обновление списка маршрутов на экране
        showAllRoutes(v);
    }

    // Другие методы обработки нажатий кнопок имеют аналогичную структуру и выполняют аналогичные действия

    // Метод для получения списка всех маршрутов из базы данных
    public ArrayList<TrainRoute> getList(){
        ArrayList<TrainRoute> trainRoutes = new ArrayList<>();
        Cursor cursor = database.getFullTable(); // Получение всех записей из базы данных
        if (cursor != null){
            // Проход по всем записям и добавление их в список маршрутов
            while (cursor.moveToNext()){
                trainRoutes.add(
                        new TrainRoute(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)));
            }
        }
        return trainRoutes; // Возврат списка маршрутов
    }
}
