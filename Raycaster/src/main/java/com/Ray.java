package com;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ray extends Line2D.Double {

    private double angle;
    private double distance;

    public Ray(double x1, double y1, double x2, double y2, double angle) {
        super(x1, y1, x2, y2);
        this.angle = angle;
        this.distance = java.lang.Float.POSITIVE_INFINITY;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Point2D.Double getStart() {
        return new Point2D.Double(getX1(), getY1());
    }

    public void drawRay(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.draw(this);
    }

    public void setEnd(Point2D p) {
        setLine(getStart(), p);
    }
}