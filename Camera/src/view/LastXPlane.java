package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import controller.RoomController;

public class LastXPlane extends ParentPlane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = z-1; i >= 0; i--) {
            for (int j = 0; j < y; j++) {
                if (pointState[x-1][j][i] == RoomController.OBSERVABLE) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j*scale, (z-i)*scale, scale, scale);
            }
         }
    }

    public LastXPlane(boolean[][][] state) {
        super(state);
        this.name = "LastXPlane";
        this.setPreferredSize(new Dimension(y*scale, z*scale));
    }
}
