package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import controller.RoomController;

public class ZPlane extends ParentPlane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = y-1; i >= 0; i--) {
            for (int j = 0; j < x; j++) {
                if (pointState[j][i][z-1] == RoomController.OBSERVABLE) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j*scale, (y-i)*scale, scale, scale);
            }
         }
    }

    public ZPlane(boolean[][][] state) {
        super(state);
        this.name = "ZPlane";
        this.setPreferredSize(new Dimension(x*scale, y*scale));
    }
}
