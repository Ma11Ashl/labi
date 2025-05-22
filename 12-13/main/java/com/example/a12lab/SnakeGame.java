package com.example.a12lab;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame {
    private List<SnakeSegment> snake;
    private SnakeDirection currentDirection;

    public SnakeGame() {
        snake = new ArrayList<>();
        snake.add(new SnakeSegment(10, 10));  // Начальная позиция змейки
        currentDirection = SnakeDirection.RIGHT;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (SnakeSegment segment : snake) {
            canvas.drawRect(segment.x * 20, segment.y * 20, (segment.x + 1) * 20, (segment.y + 1) * 20, paint);
        }
    }

    public void update() {
        // Логика обновления змейки
    }

    private static class SnakeSegment {
        int x;
        int y;

        SnakeSegment(int x, int y) {
            this.x = x;
            this.y = x;
        }
    }

    private enum SnakeDirection {
        UP, DOWN, LEFT, RIGHT
    }
}