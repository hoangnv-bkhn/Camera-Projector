package service;

import java.util.List;

import georegression.struct.point.Point3D_F32;
import model.Block;
import model.Camera;
import model.Room;

public interface RoomService {
	/**
	 * check if block is in room
	 */
	boolean isValidBlockLocation(Room room, Block block);
	/**
	 * check if camera is in wall
	 */
	boolean isValidCameraLocation(Room room, Camera camera);
	/**
	 * check if room is valid
	 */
	boolean checkValidRoom(Room room);
	/**
	 * check is block is on ground
	 */
	boolean isBlockOnGround(Room room, List<Block> blocks, Block block);
	/**
	 * find and return points of camera field of view
	 */
	Point3D_F32[] findFieldOfView(Room room, Camera camera);
}
