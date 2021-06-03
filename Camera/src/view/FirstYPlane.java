package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import controller.RoomController;

public class FirstYPlane extends ParentPlane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = z-1; i >= 0; i--) {
            for (int j = 0; j < x; j++) {
                if (pointState[j][0][i] == RoomController.OBSERVABLE) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j*scale, (z-i)*scale, scale, scale);
            }
         }
    }

    public FirstYPlane(boolean[][][] state) {
        super(state);
        this.name = "FirstYPlane";
        this.setPreferredSize(new Dimension(x*scale, z*scale));
    }
}
