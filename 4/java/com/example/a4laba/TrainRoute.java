package com.example.a4laba;

// Класс, представляющий маршрут поезда
public class TrainRoute {

    // Переменные класса для хранения данных о маршруте
    public int _id; // ID маршрута
    public String route; // Название маршрута
    public String departureDate; // Дата отправления
    public String arrivalDate; // Дата прибытия

    // Конструктор класса для инициализации данных о маршруте
    public TrainRoute(int id, String rt, String depDate, String arrDate){
        _id = id; // Присвоение переданного значения ID маршрута
        route = rt; // Присвоение переданного названия маршрута
        departureDate = depDate; // Присвоение переданной даты отправления
        arrivalDate = arrDate; // Присвоение переданной даты прибытия
    }
}
