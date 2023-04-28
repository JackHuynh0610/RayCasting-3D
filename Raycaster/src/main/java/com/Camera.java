package com;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Camera {
    private Point2D position;
    private List<Ray> rays;
    private double angle;
    private CameraMotionListener cameraMotionListener;
    private RaycasterPanel panel;

    public Camera(RaycasterPanel panel, Point2D position) {
        this.position = position;
        this.rays = new ArrayList<>();
        this.angle = 90;
        this.panel = panel;
        this.cameraMotionListener = new CameraMotionListener();
    }

    public void computeRays(){
        rays.clear();
        for (int i = 0; i < this.getProjectionWidth(); i++ ){
            double currentAngle = RaycasterUtils.normalize(i, 0, this.getProjectionWidth(), this.getAngle() - 70 / 2, this.getAngle() + 70 / 2);
            double endPointX = 1280 * Math.cos(Math.toRadians(currentAngle));
            double endPointY = 1280 * Math.sin(Math.toRadians(currentAngle));
            Ray ray = new Ray(this.getX(), this.getY(), endPointX, endPointY, currentAngle);
            

            double minDist = Integer.MAX_VALUE;
            Point2D.Double minPt = null;
            for (RectangleObject rect : this.panel.getRectangleObjects()) {
                Point2D.Double currCollisionPt = rect.rectIntersection(ray);
                if (currCollisionPt != null && this.position.distance(currCollisionPt) < minDist) {
                    minDist = this.position.distance(currCollisionPt);
                    minPt = currCollisionPt;
                }
            }

            if (minPt != null) {
                ray.setEnd(new Point2D.Double(minPt.x, minPt.y));
                currentAngle = RaycasterUtils.normalize(currentAngle, this.getAngle() - 70 / 2, this.getAngle() + 70 / 2, -35, 35);
                ray.setDistance(minDist * Math.cos(Math.toRadians(currentAngle)));
            }

            rays.add(ray);
        }
    }

    public void drawCamera(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.drawOval(getX(), getY(), 10, 10);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
    public Point2D getStart() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }


    public List<Ray> getRays() {
        return rays;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getProjectionWidth() {
        return 640; // Example value, adjust as needed
    }

    public CameraMotionListener getCameraMotionListener() {
        return cameraMotionListener;
    }

    public void addRay(Ray ray) {
        rays.add(ray);
    }

    private class CameraMotionListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            position = new Point(e.getX(), e.getY());
        }
    }

    private class CameraAngleListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                angle --;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                angle ++;
            }

            
            
        }
    }

    private CameraAngleListener camList = new CameraAngleListener();
    
    public CameraAngleListener getCameraAngleListener(){
        return camList;
    }



   
}