package model;


import georegression.struct.point.Point3D_F32;

public class Block {
    private Point3D_F32 coordinates[];

    public Block(Point3D_F32 coordinates[]) {
        this.coordinates = coordinates;
    }
    
	public Point3D_F32[] getCoordinates() {
		return coordinates;
	}
 
}
