package com;

import javax.swing.*;
import java.awt.*;

public class RaycasterProjectionPanel extends JPanel {

    /**
     * Root driver object to keep track of sizing.
     */
    private final RaycasterRunner RUNNER;

    private final double delta = 40.0;

    /**
     * Overhead panel to access the generated rays.
     */
    private final RaycasterPanel RAYCASTER_PANEL;

    public RaycasterProjectionPanel(final RaycasterRunner raycasterRunner, final RaycasterPanel raycasterPanel) {
        this.RUNNER = raycasterRunner;
        this.setPreferredSize(new Dimension(this.RUNNER.getWidth() / 2, this.RUNNER.getHeight()));
        this.RAYCASTER_PANEL = raycasterPanel;
    }

    public void update() {
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.projects(g2d);   
    }

    private void projects(Graphics2D g){
        Camera cam = RAYCASTER_PANEL.getCamera();
        for(int i = 0; i < cam.getRays().size(); i++){
            double wallX = RaycasterUtils.normalize(i, 0, 640, 0, this.getWidth());
            double wallHeight = RAYCASTER_PANEL.getHeight() * delta / cam.getRays().get(i).getDistance();
            double wallY = RAYCASTER_PANEL.getHeight() / 2 - wallHeight / 2;
            
            g.drawLine((int)wallX, (int)wallY, (int)wallX, (int)wallY + (int)wallHeight);
        }
        
        
    }
}
