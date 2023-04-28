package com;

import javax.swing.*;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Displays and updates the logic for the top-down raycasting implementation.
 * This class is where the collision detection and movement occurs, whereas the
 * RaycasterPerspectivePanel just projects it to a pseudo-3d environment.
 */
public final class RaycasterPanel extends JPanel {

    /**
     * We need to keep a reference to the parent swing app for sizing and
     * other bookkeeping.
     */
    private final RaycasterRunner RUNNER;

    /**
     * Number of rays to fire from the camera.
     */
    private final int RESOLUTION;
    private final ArrayList<RectangleObject> objects;
    private Camera camera;

    public RaycasterPanel(final RaycasterRunner raycasterRunner) {
        this.RUNNER = raycasterRunner;
        this.setPreferredSize(new Dimension(this.RUNNER.getWidth() / 2, this.RUNNER.getHeight()));
        this.RESOLUTION = this.getPreferredSize().width;
        this.requestFocusInWindow(true);
        this.objects = new ArrayList<>();
        this.camera = new Camera(this, new Point2D.Double(50,50)); 
        this.addMouseMotionListener(this.camera.getCameraMotionListener());
        this.setBackground(Color.BLACK);
        Random rand = new Random();
        final int WIDTH = this.getPreferredSize().width;
        final int HEIGHT = this.getPreferredSize().height;

        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            int w = rand.nextInt((WIDTH - x)/2);
            int h = rand.nextInt((HEIGHT - y)/2);
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            RectangleObject rect = new RectangleObject(x, y, w, h, color);
            this.objects.add(rect);
        }

        this.addKeyListener(this.camera.getCameraAngleListener());    
        
    }
    
    public Camera getCamera() {
        return camera;
    }

    private void drawRays(final Graphics2D g2) {
        this.camera.computeRays();
        java.util.List<Ray> rays = this.camera.getRays();
        for (int i = 0; i < rays.size(); i++) {
            Ray ray = rays.get(i);
            ray.drawRay(g2);
        }
    }

    public void update() {
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (RectangleObject objs: this.objects){
            objs.drawObject(g2d);
        }
        drawRays(g2d);
        this.camera.drawCamera(g2d);
        
    }

    public ArrayList<RectangleObject> getRectangleObjects(){
        return objects;
    }
}