package com.example.a12lab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

public class SnakeGameView extends SurfaceView implements SurfaceHolder.Callback {
    private SnakeThread gameThread;
    private Paint paint;
    private SnakeGame snakeGame;

    public SnakeGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        paint = new Paint();
        snakeGame = new SnakeGame();
    }

    public void startGame() {
        gameThread = new SnakeThread(getHolder(), this);
        gameThread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameThread != null && !gameThread.isAlive()) {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Может быть использовано для обработки изменения размеров.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.stopThread();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Здесь нужно нарисовать змейку.
        canvas.drawColor(Color.WHITE);

        // Пример: рисуем змейку
        paint.setColor(Color.GREEN);
        snakeGame.draw(canvas, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Обработка управления змейкой.
        return super.onTouchEvent(event);
    }
}