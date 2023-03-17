package com.example.kg2_2;

import javafx.scene.paint.Color;

import static java.lang.Math.*;
import static java.lang.Math.round;

public class Line {
    private final int x1, y1, x2, y2;
    private final Color color1,  color2;

    public Line(PixelInfo firstPoint, PixelInfo secondPoint) {
        this.x1 = firstPoint.getX();
        this.y1 = firstPoint.getY();
        this.x2 = secondPoint.getX();
        this.y2 = secondPoint.getY();
        this.color1 = firstPoint.getColor();
        this.color2 = secondPoint.getColor();
    }

    public void drawLine(PixelDrawer pd) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(abs(dx), abs(dy));

        if (steps == 0) {
            pd.putPixel(x1, y1, Color.BLACK);
        }

        float Xinc = dx / (float) steps;
        float Yinc = dy / (float) steps;

        int x = x1;
        int y = y1;

        for (int i = 0; i <= steps; i++) {
            pd.putPixel(round(x), round(x), getInterpolationColor(x, y));
            x += Xinc;
            y += Yinc;
        }
    }

    private javafx.scene.paint.Color getInterpolationColor(int currentX, int currentY) {
        double alpha, beta;

        double centerY = currentY >> 1;
        double centerX = currentX >> 1;

        double numerator = x1 * (currentY - y2) + currentX * (y2 - y1) + x2 * (y1 - currentY);

        double denominator = x1 * (centerY - y2) + centerX * (y2 - y1) + x2 * (y1 - centerY);

        beta = numerator / denominator;

        if (x1 == x2) {
            alpha = (currentY - y2 + beta * (y2 - centerY)) / (y1 - y2);
        } else {
            alpha = (currentX - x2 - beta * (centerX - x2)) / (x1 - x2);
        }

        float r = (float) ((alpha * color1.getRed()) + (beta * ((color1.getRed() + color2.getRed())/2)) + ((1 - alpha - beta) * color2.getRed()));
        r = getGoodValue(r);

        float g = (float) ((alpha * color1.getGreen()) + (beta * ((color1.getGreen() + color2.getGreen())/2)) + ((1 - alpha - beta) * color2.getGreen()));
        g = getGoodValue(g);

        float b = (float) ((alpha * color1.getBlue()) + (beta * ((color1.getBlue() + color2.getBlue())/2)) + ((1 - alpha - beta) * color2.getBlue()));
        b = getGoodValue(b);

        return new javafx.scene.paint.Color(r, g, b,1);
    }

    private float getGoodValue(float color) {
        if (color > 1) {
            return 1;
        }

        return Math.max(color, 0);
    }
}