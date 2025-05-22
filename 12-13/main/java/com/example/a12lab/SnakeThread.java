package com.example.a12lab;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SnakeThread extends Thread {
    private boolean running;
    private final SurfaceHolder surfaceHolder;
    private final SnakeGameView gameView;

    public SnakeThread(SurfaceHolder surfaceHolder, SnakeGameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        running = true;
    }

    public void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        gameView.draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}