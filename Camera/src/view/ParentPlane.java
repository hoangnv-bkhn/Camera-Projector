package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public abstract class ParentPlane extends JPanel {

    protected boolean[][][] pointState;
    protected int x,y,z;
    protected String name;
    protected int scale;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public ParentPlane(boolean[][][] state) {
        this.pointState = state;
        this.x = pointState.length;
        this.y = pointState[0].length;
        this.z = pointState[0][0].length;
        this.name = "";
        int max = x>y?x:y;
        max = max>z?max:z;
        scale = (int)Math.floor(400/max);
        scale = scale<1?1:scale;
        this.setPreferredSize(new Dimension(400, 400));
    }

    public void drawPlane() {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
