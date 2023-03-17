package com.example.kg2_2;

import javafx.scene.paint.Color;

public record PixelInfo(int x, int y, Color color) {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

}