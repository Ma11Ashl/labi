package com.example.a4laba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Класс для работы с базой данных SQLite
public class SQLHelper extends SQLiteOpenHelper{

    // Конструктор класса
    public SQLHelper(Context context){
        // Вызов конструктора суперкласса для создания или открытия базы данных "Routes"
        super(context, "Routes", null, 1);
    }

    // Метод вызывается при создании базы данных
    @Override
    public void onCreate(SQLiteDatabase database){
        // Создание таблицы "table1" с четырьмя столбцами
        database.execSQL("create table table1 " +
                "(_id integer primary key autoincrement, route text not null, " +
                "departureDate text not null, arrivalDate text not null)");
    }

    // Метод вызывается при обновлении базы данных
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        // Удаление старой версии таблицы и создание новой
        database.execSQL("DROP TABLE IF EXISTS table1");
        onCreate(database);
    }

    // Метод для получения всей таблицы маршрутов
    public Cursor getFullTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        // Выполнение запроса на получение всех строк таблицы
        return database.query("table1", new String[] {"_id", "route", "departureDate", "arrivalDate"},
                null, null, null, null, null);
    }

    // Метод для добавления нового маршрута в таблицу
    public void addTrainRoute(String route, String departureDate, String arrivalDate) {
        SQLiteDatabase db = getWritableDatabase();
        int lastId = 0;
        // Получение последнего ID из таблицы
        Cursor cursor = db.rawQuery("SELECT MAX(_id) FROM table1", null);
        if (cursor.moveToFirst()) {
            lastId = cursor.getInt(0);
        }
        cursor.close();
        // Вставка новой записи с увеличенным на 1 ID
        db.execSQL("INSERT INTO table1 (_id, route, departureDate, arrivalDate) VALUES (?, ?, ?, ?)", new String[]{String.valueOf(lastId + 1), route, departureDate, arrivalDate});
        db.close();
    }

    // Метод для обновления маршрута
    public void updateRoute(int id, String newRoute, String newDepartureDate, String newArrivalDate){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("route", newRoute);
        cv.put("departureDate", newDepartureDate);
        cv.put("arrivalDate", newArrivalDate);
        // Обновление записи с указанным ID
        db.update("table1", cv, "_id = ?", new String[]{Integer.toString(id)});
    }

    // Метод для удаления маршрута
    public void deleteRoute(int id) {
        SQLiteDatabase db = getWritableDatabase();
        // Удаление записи с указанным ID
        db.delete("table1", "_id = ?", new String[]{Integer.toString(id)});
        // Обновление ID записей, чей ID больше удаленного
        db.execSQL("UPDATE table1 SET _id = _id - 1 WHERE _id > ?", new String[]{Integer.toString(id)});
    }
}
