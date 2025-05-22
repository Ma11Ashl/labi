package com.example.lab7;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import kotlin.text.Regex;

public class RegistrationActivity extends AppCompatActivity {

    // Цвета для текста в зависимости от валидности данных
    ColorStateList colorForInvalidText;
    ColorStateList colorForValidText;

    // Поля для ввода данных
    EditText phoneNumber;
    EditText fullName;
    EditText login;
    EditText email;
    EditText password;
    EditText repeatPassword;
    EditText birthDate;

    // Элементы управления для выбора даты и закрытия датапикера
    DatePicker datePicker;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration); // Устанавливаем макет для этой активности

        // Инициализация цветов для текста
        colorForInvalidText = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_focused},
                        new int[]{android.R.attr.state_focused}
                },
                new int[]{
                        Color.GRAY,
                        Color.RED
                }
        );

        colorForValidText = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_focused},
                        new int[]{android.R.attr.state_focused}
                },
                new int[]{
                        Color.GRAY,
                        Color.GREEN
                }
        );

        // Получаем ссылки на элементы управления
        datePicker = findViewById(R.id.birth_date_picker);
        closeButton = findViewById(R.id.close_button);

        phoneNumber = findViewById(R.id.phone_number);
        fullName = findViewById(R.id.full_name);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeat_password);
        birthDate = findViewById(R.id.birth_date);

        // Валидация данных при их вводе
        loginValidation();
        fullNameValidation();
        emailValidation();
        passwordValidation();
        repeatPasswordValidation();
        phoneNumberValidation();
        birthDateValidation();

        // Запрещаем фокусировку на полях ввода
        phoneNumber.setFocusable(false);
        fullName.setFocusable(false);
        login.setFocusable(false);
        email.setFocusable(false);
        password.setFocusable(false);
        repeatPassword.setFocusable(false);
        birthDate.setFocusable(false);

        // Обработчик изменения даты в DatePicker
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = String.format("%02d.%02d.%04d", dayOfMonth, monthOfYear + 1, year);
                birthDate.setText(selectedDate);
            }
        });

        // Обработчик изменения состояния чекбокса согласия с условиями
        CheckBox agreementCheckBox = findViewById(R.id.agreement_checkbox);
        agreementCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Разрешаем фокусировку на полях ввода
                    phoneNumber.setFocusableInTouchMode(true);
                    fullName.setFocusableInTouchMode(true);
                    login.setFocusableInTouchMode(true);
                    email.setFocusableInTouchMode(true);
                    password.setFocusableInTouchMode(true);
                    repeatPassword.setFocusableInTouchMode(true);
                    birthDate.setFocusableInTouchMode(true);
                } else {
                    // Запрещаем фокусировку на полях ввода
                    phoneNumber.setFocusable(false);
                    fullName.setFocusable(false);
                    login.setFocusable(false);
                    email.setFocusable(false);
                    password.setFocusable(false);
                    repeatPassword.setFocusable(false);
                    birthDate.setFocusable(false);
                }
            }
        });
    }

    // Обработчик клика на кнопку
    public void onButtonClick(View v){
        // Показываем DatePicker и кнопку закрытия
        datePicker.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.VISIBLE);
    }

    // Обработчик клика на кнопку закрытия
    public void onCloseButtonClick(View v){
        // Скрываем DatePicker и кнопку закрытия
        datePicker.setVisibility(View.GONE);
        closeButton.setVisibility(View.GONE);
    }

    // Настройка Spinner
    public void spinnerAdaptation(){
        Spinner spinner = findViewById(R.id.spinner);
        String[] array = getResources().getStringArray(R.array.string_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Метод вызывается при выборе элемента в Spinner, но здесь нет действий
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Метод вызывается, если не выбран ни один элемент в Spinner, но здесь нет действий
            }
        });
    }

    // Валидация номера телефона
    public void phoneNumberValidation(){
        phoneNumber.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = phoneNumber.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = phoneNumber.getText().toString();
                if(!(string.matches(String.valueOf(new Regex("7-[0-9]{3}-[0-9]{3}-[0-9]{4}"))))){
                    phoneNumber.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is invalid pattern");
                }
                else{
                    phoneNumber.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid pattern");
                }
            }
        });
    }

    // Валидация полного имени
    public void fullNameValidation(){
        fullName.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = fullName.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = fullName.getText().toString();
                if(!(string.matches(String.valueOf(new Regex("[A-Za-zА-Яа-яЁё]{1,20} [A-Za-zА-Яа-яЁё]{1,20} [A-Za-zА-Яа-яЁё]{1,20}"))))){
                    fullName.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is invalid pattern");
                }
                else{
                    fullName.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid pattern");
                }
            }
        });
    }

    // Валидация логина
    public void loginValidation(){
        login.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = login.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = login.getText().toString();
                if(!(string.matches(String.valueOf(new Regex("[A-Za-z][A-Za-z0-9]{2,19}"))))){
                    login.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is invalid pattern");
                }
                else{
                    login.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid pattern");
                }
            }
        });
    }

    // Валидация электронной почты
    public void emailValidation(){
        email.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = email.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = email.getText().toString();
                if(!(string.matches(String.valueOf(new Regex("[A-Za-z][A-Za-z0-9_.]{0,20}@[a-z]{1,20}.[a-z]{1,10}"))))){
                    email.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is invalid pattern");
                }
                else{
                    email.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid pattern");
                }
            }
        });
    }

    // Валидация пароля
    public void passwordValidation(){
        password.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = password.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = password.getText().toString();
                if(!(string.matches(String.valueOf(new Regex("[A-Za-z0-9_]{8,40}"))))){
                    password.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is invalid pattern");
                }
                else{
                    password.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid pattern");
                }
            }
        });
    }

    // Валидация повторного ввода пароля
    public void repeatPasswordValidation(){
        repeatPassword.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = repeatPassword.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s){
                // Метод вызывается после изменения текста
                String string = repeatPassword.getText().toString();
                if(!(string.matches(String.valueOf(new Regex(password.getText().toString()))))){
                    repeatPassword.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is not same password");
                }
                else{
                    repeatPassword.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is same password");
                }
            }
        });
    }

    // Валидация даты рождения
    public void birthDateValidation(){
        birthDate.addTextChangedListener(new TextWatcher() {
            int len = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String string = birthDate.getText().toString();
                len = string.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Метод вызывается перед изменением текста, но здесь нет действий
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Метод вызывается после изменения текста
                String string = birthDate.getText().toString();
                if (!(string.matches(String.valueOf(new Regex("(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(19[0-9]{2}|20([01][0-9]|2[0-4]))"))))) {
                    birthDate.setBackgroundTintList(colorForInvalidText);
                    Log.i("Validation", string + " is not valid date pattern");
                } else {
                    birthDate.setBackgroundTintList(colorForValidText);
                    Log.i("Validation", string + " is valid date pattern");
                }
            }
        });
    }
}
