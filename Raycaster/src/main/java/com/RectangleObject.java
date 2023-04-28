package com;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class RectangleObject implements Drawable {
    private int x, y, width, height;
    private Color color;

    public RectangleObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }


    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Point2D.Double rectIntersection(final Ray ray) {
        final Line2D.Double top = new Line2D.Double(x, y, x + width, y);
        final Line2D.Double right = new Line2D.Double(x + width, y, x + width, y + height);
        final Line2D.Double bottom = new Line2D.Double(x, y + height, x + width, y + height);
        final Line2D.Double left = new Line2D.Double(x, y, x, y + height);

        double minDistance = Double.POSITIVE_INFINITY;
        Point2D.Double closestIntersection = null;

        for (final Line2D.Double line : new Line2D.Double[]{top, right, bottom, left}) {
            final Point2D.Double intersection = RaycasterUtils.intersection(ray, line);
            if (intersection != null) {
                final double distance = intersection.distance(ray.getStart());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestIntersection = intersection;
                }
            }
        }

        return closestIntersection;
    }
    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        g2d.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
