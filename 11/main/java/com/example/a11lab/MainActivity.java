package com.example.a11lab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private ProgressBar progressBar;
    private ImageView[] imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        progressBar = findViewById(R.id.progressBar);



        calculateButton.setOnClickListener(v -> {
            new CalculationTask(resultTextView).execute();

        });
    }

    private static class CalculationTask extends AsyncTask<Void, Void, String> {
        private final TextView resultTextView;

        CalculationTask(TextView resultTextView) {
            this.resultTextView = resultTextView;
        }

        @Override
        protected String doInBackground(Void... voids) {
            int[] array = {1, 2, 3, 0, 4, 5, 7, 0, 7, 8};

            int product = 1;
            for (int i = 0; i < array.length; i += 2) {
                product *= array[i];
            }

            int firstZeroIndex = -1;
            int lastZeroIndex = -1;

            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0) {
                    if (firstZeroIndex == -1) {
                        firstZeroIndex = i;
                    }
                    lastZeroIndex = i;
                }
            }

            int sumBetweenZeros = 0;
            if (firstZeroIndex != -1 && lastZeroIndex > firstZeroIndex) {
                for (int i = firstZeroIndex + 1; i < lastZeroIndex; i++) {
                    sumBetweenZeros += array[i];
                }
            }

            return String.format(
                    "Произведение четных индексов: %d\nСумма между первым и последним нулевыми: %d",
                    product,
                    sumBetweenZeros
            );
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.append("\n" + result);
        }
    }

    private static class ImageLoadingTask extends AsyncTask<String, Integer, Bitmap[]> {
        private final ProgressBar progressBar;
        private final ImageView[] imageViews;

        ImageLoadingTask(ProgressBar progressBar, ImageView[] imageViews) {
            this.progressBar = progressBar;
            this.imageViews = imageViews;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap[] doInBackground(String... urls) {
            Bitmap[] bitmaps = new Bitmap[urls.length];

            for (int i = 0; i < urls.length; i++) {
                try {
                    URL url = new URL(urls[i]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    bitmaps[i] = BitmapFactory.decodeStream(inputStream);
                    connection.disconnect();
                    publishProgress((int) ((i + 1) * 100.0 / urls.length));
                } catch (Exception e) {
                    publishProgress(0);
                    cancel(true);
                }
            }

            return bitmaps;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmaps) {
            progressBar.setVisibility(View.GONE);

            for (int i = 0; i < bitmaps.length; i++) {
                imageViews[i].setImageBitmap(bitmaps[i]);
            }
        }

        @Override
        protected void onCancelled() {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(progressBar.getContext(), "Ошибка при загрузке изображений", Toast.LENGTH_SHORT).show();
        }
    }
}
