package model;

import georegression.struct.point.Point3D_F32;

public class Camera {
    private Point3D_F32 location;
    private int angleWidth;
    private int angleHeight;
    private int distance;


    public Camera(Point3D_F32 location, int angleWidth, int angleHeight, int distance) {
        this.location = location;
        this.angleWidth = angleWidth;
        this.angleHeight = angleHeight;
        this.distance = distance;
    }

    public Point3D_F32 getLocation() {
        return this.location;
    }

    public int getAngleWidth() {
        return this.angleWidth;
    }

    public int getAngleHeight() {
        return this.angleHeight;
    }

    public int getDistance() {
        return this.distance;
    }
}