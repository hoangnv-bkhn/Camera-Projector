package model;

import java.util.ArrayList;
import java.util.List;

import georegression.struct.point.Point3D_F32;

public class Room {
    private Point3D_F32[] coordinates;
    private List<Block> blocks = new ArrayList<>();
    private List<Camera> cameras = new ArrayList<>();

    public Room (Point3D_F32[] coordinates) {
        this.coordinates = coordinates;
    }

    public Point3D_F32[] getCoordinates() {
		return coordinates;
	}

	public List<Block> getBlocks() {
        return this.blocks;
    }

    public List<Camera> getCameras() {
        return this.cameras;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void addCamera(Camera camera) {
        cameras.add(camera);
    }
}
