package com.example.a8lab;

// Импортируем необходимые классы
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

// Определение класса VideoActivity, который является наследником AppCompatActivity
public class VideoActivity extends AppCompatActivity {

    AudioManager audioManager;

    // Метод onCreate, который вызывается при создании активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Получаем максимальное и текущее значение громкости и устанавливаем их на SeekBar
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumeControl = findViewById(R.id.volumeControl1);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        // Устанавливаем слушатель изменения громкости на SeekBar
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Инициализация VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Получаем путь к видео из ресурсов приложения и создаем Uri
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        Uri uri = Uri.parse(videoPath);

        // Устанавливаем URI видео для воспроизведения
        videoView.setVideoURI(uri);

        // Создаем объект MediaController для управления воспроизведением видео
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // Устанавливаем якорь MediaController на VideoView
        mediaController.setAnchorView(videoView);
        videoView.start();
    }

    // Метод для обработки нажатия на кнопку
    public void onBtnClick(View v) {
        // Создаем интент для перехода на активность MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        // Запускаем активность MainActivity
        startActivity(intent);
    }
}
