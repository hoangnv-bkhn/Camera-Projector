package service.impl;

import java.util.List;

import georegression.struct.point.Point3D_F32;
import model.Block;
import model.Camera;
import model.Room;
import service.MathCalService;
import service.RoomService;

public class RoomServiceImpl implements RoomService {

	MathCalService mathCalService = new MathCalServiceImpl();

	@Override
	public boolean isValidBlockLocation(Room room, Block block) {
		Point3D_F32[] coordinates = room.getCoordinates();
		Point3D_F32[] location = block.getCoordinates();

		for (Point3D_F32 point3d_F32 : location) {
			if (coordinates[0].getX() <= point3d_F32.getX() && point3d_F32.getX() <= coordinates[1].getX()) {
				if (coordinates[0].getY() <= point3d_F32.getY() && point3d_F32.getY() <= coordinates[3].getY()) {
					if (coordinates[0].getZ() <= point3d_F32.getZ() && point3d_F32.getZ() <= coordinates[4].getZ()) {
						
					}else {
						return false;
					}
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isValidCameraLocation(Room room, Camera camera) {
		Point3D_F32[] coordinates = room.getCoordinates();
		Point3D_F32 location = camera.getLocation();

		if (location.getX() == coordinates[1].getX()) {
			if (coordinates[1].getY() < location.getY() && location.getY() < coordinates[2].getY()) {
				if (coordinates[1].getZ() < location.getZ() && location.getZ() < coordinates[5].getZ()) {
					return true;
				}
			}
		}
		
		if (location.getX() == coordinates[0].getX()) {
			if (coordinates[0].getY() < location.getY() && location.getY() < coordinates[3].getY()) {
				if (coordinates[0].getZ() < location.getZ() && location.getZ() < coordinates[4].getZ()) {
					return true;
				}
			}
		}
		
		if (location.getY() == coordinates[0].getY()) {
			if (coordinates[0].getX() < location.getX() && location.getX() < coordinates[1].getX()) {
				if (coordinates[0].getZ() < location.getZ() && location.getZ() < coordinates[4].getZ()) {
					return true;
				}
			}
		}
		
		if (location.getY() == coordinates[3].getY()) {
			if (coordinates[3].getX() < location.getX() && location.getX() < coordinates[2].getX()) {
				if (coordinates[3].getZ() < location.getZ() && location.getZ() < coordinates[7].getZ()) {
					return true;
				}
			}
		}
		
		if (location.getZ() == coordinates[4].getZ()) {
			if (coordinates[4].getX() < location.getX() && location.getX() < coordinates[5].getX()) {
				if (coordinates[4].getY() < location.getY() && location.getY() < coordinates[7].getY()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean checkValidRoom(Room room) {
		Point3D_F32[] coordinates = room.getCoordinates();
		if (coordinates.length != 8) {
			return false;
		}else {
			return mathCalService.rectangularCheck(coordinates);
		}
	}

	@Override
	public boolean isBlockOnGround(Room room, List<Block> blocks, Block block) {
		if (block.getCoordinates()[0].getZ() != room.getCoordinates()[0].getZ()) {
			return false;
		}
		return true;
	}

	@Override
	public Point3D_F32[] findFieldOfView(Room room, Camera camera) {
		Point3D_F32[] points = new Point3D_F32[3];
		int x = (int)camera.getLocation().getX();
		int y = (int)camera.getLocation().getY();
		int z = (int)camera.getLocation().getZ();
		int cameraDistance = camera.getDistance();
		if (x == room.getCoordinates()[0].getX()) {
			int roomDistance = (int)(room.getCoordinates()[6].getX() - room.getCoordinates()[0].getX());
			cameraDistance = cameraDistance > roomDistance ? roomDistance : cameraDistance;

			int cameraHalfHeight = (int)(Math.tan(Math.PI*camera.getAngleHeight()/360)*cameraDistance);
			int cameraHalfWidth = (int)(Math.tan(Math.PI*camera.getAngleWidth()/360)*cameraDistance);

			points[0] = new Point3D_F32(x + cameraDistance, y - cameraHalfWidth, z - cameraHalfHeight);
			points[1] = new Point3D_F32(x + cameraDistance, y + cameraHalfWidth, z - cameraHalfHeight);
			points[2] = new Point3D_F32(x + cameraDistance, y - cameraHalfWidth, z + cameraHalfHeight);
		} else if (x == room.getCoordinates()[6].getX()) {
			int roomDistance = (int)(room.getCoordinates()[6].getX() - room.getCoordinates()[0].getX());
			cameraDistance = cameraDistance > roomDistance ? roomDistance : cameraDistance;
			
			int cameraHalfHeight = (int)(Math.tan(Math.PI*camera.getAngleHeight()/360)*cameraDistance);
			int cameraHalfWidth = (int)(Math.tan(Math.PI*camera.getAngleWidth()/360)*cameraDistance);

			points[0] = new Point3D_F32(x - cameraDistance, y - cameraHalfWidth, z - cameraHalfHeight);
			points[1] = new Point3D_F32(x - cameraDistance, y + cameraHalfWidth, z - cameraHalfHeight);
			points[2] = new Point3D_F32(x - cameraDistance, y - cameraHalfWidth, z + cameraHalfHeight);
		} else if (y == room.getCoordinates()[0].getY()) {
			int roomDistance = (int)(room.getCoordinates()[6].getY() - room.getCoordinates()[0].getY());
			cameraDistance = cameraDistance > roomDistance ? roomDistance : cameraDistance;
			
			int cameraHalfHeight = (int)(Math.tan(Math.PI*camera.getAngleHeight()/360)*cameraDistance);
			int cameraHalfWidth = (int)(Math.tan(Math.PI*camera.getAngleWidth()/360)*cameraDistance);

			points[0] = new Point3D_F32(x - cameraHalfWidth, y + cameraDistance, z - cameraHalfHeight);
			points[1] = new Point3D_F32(x + cameraHalfWidth, y + cameraDistance, z - cameraHalfHeight);
			points[2] = new Point3D_F32(x - cameraHalfWidth, y + cameraDistance, z + cameraHalfHeight);
		} else if (y == room.getCoordinates()[6].getY()) {
			int roomDistance = (int)(room.getCoordinates()[6].getY() - room.getCoordinates()[0].getY());
			cameraDistance = cameraDistance > roomDistance ? roomDistance : cameraDistance;
			
			int cameraHalfHeight = (int)(Math.tan(Math.PI*camera.getAngleHeight()/360)*cameraDistance);
			int cameraHalfWidth = (int)(Math.tan(Math.PI*camera.getAngleWidth()/360)*cameraDistance);

			points[0] = new Point3D_F32(x - cameraHalfWidth, y - cameraDistance, z - cameraHalfHeight);
			points[1] = new Point3D_F32(x + cameraHalfWidth, y - cameraDistance, z - cameraHalfHeight);
			points[2] = new Point3D_F32(x - cameraHalfWidth, y - cameraDistance, z + cameraHalfHeight);
		} else if (z == room.getCoordinates()[6].getZ()) {
			int roomDistance = (int)(room.getCoordinates()[6].getZ() - room.getCoordinates()[0].getZ());
			cameraDistance = cameraDistance > roomDistance ? roomDistance : cameraDistance;
			
			int cameraHalfHeight = (int)(Math.tan(Math.PI*camera.getAngleHeight()/360)*cameraDistance);
			int cameraHalfWidth = (int)(Math.tan(Math.PI*camera.getAngleWidth()/360)*cameraDistance);

			points[0] = new Point3D_F32(x - cameraHalfWidth, y - cameraHalfHeight, z - cameraDistance);
			points[1] = new Point3D_F32(x + cameraHalfWidth, y - cameraHalfHeight, z - cameraDistance);
			points[2] = new Point3D_F32(x - cameraHalfWidth, y + cameraHalfHeight, z - cameraDistance);
		} else {
			throw new RuntimeException("Invalid camera location");
		}
		return points;
	}

}
