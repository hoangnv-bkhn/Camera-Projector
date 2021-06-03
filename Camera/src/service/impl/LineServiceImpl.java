package service.impl;

import java.util.ArrayList;
import java.util.List;

import georegression.struct.line.LineParametric3D_F32;
import georegression.struct.point.Point3D_F32;
import model.Camera;
import service.LineService;

public class LineServiceImpl implements LineService {

    @Override
	public Point3D_F32 findNearestPointBaseX(Camera camera, List<Point3D_F32> points){
		float x = camera.getLocation().getX();
		Point3D_F32 tempPoint = points.get(0);
		for (Point3D_F32 point : points) {
			if (Math.abs(point.getX() - x) < Math.abs(tempPoint.getX() - x)) {
				tempPoint = point;
			}
		}
		return tempPoint;
	}

    @Override
    public Point3D_F32 getPointFromX(LineParametric3D_F32 line, float x) {
        if (line.getSlopeX() == 0) {
            throw new RuntimeException("Can't get point from x.");
        }
        float t = (x - line.getX())/line.getSlopeX();
        float y = line.getY() + t*line.getSlopeY();
        float z = line.getZ() + t*line.getSlopeZ();
        return new Point3D_F32(x,y,z);
    }

    @Override
    public List<Point3D_F32> getPointListInRangeBaseX(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2) {
        List<Point3D_F32> points = new ArrayList<>();
        if (point1.getX() > point2.getX()) {
            for (int i = (int)point1.getX()-1; i > (int)point2.getX(); i--) {
                points.add(this.getPointFromX(line, i));
            }
        } else {
            for (int i = (int)point1.getX()+1; i < (int)point2.getX(); i++) {
                points.add(this.getPointFromX(line, i));
            }
        }
        return points;
    }

    @Override
	public Point3D_F32 findNearestPointBaseY(Camera camera, List<Point3D_F32> points){
		float y = camera.getLocation().getY();
		Point3D_F32 tempPoint = points.get(0);
		for (Point3D_F32 point : points) {
			if (Math.abs(point.getY() - y) < Math.abs(tempPoint.getY() - y)) {
				tempPoint = point;
			}
		}
		return tempPoint;
	}

    @Override
    public Point3D_F32 getPointFromY(LineParametric3D_F32 line, float y) {
        if (line.getSlopeY() == 0) {
            throw new RuntimeException("Can't get point from y.");
        }
        float t = (y - line.getX())/line.getSlopeY();
        float x = line.getX() + t*line.getSlopeX();
        float z = line.getZ() + t*line.getSlopeZ();
        return new Point3D_F32(x,y,z);
    }

    @Override
    public List<Point3D_F32> getPointListInRangeBaseY(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2) {
        List<Point3D_F32> points = new ArrayList<>();
        if (point1.getY() > point2.getY()) {
            for (int i = (int)point1.getY()-1; i > (int)point2.getY(); i--) {
                points.add(this.getPointFromY(line, i));
            }
        } else {
            for (int i = (int)point1.getY()+1; i < (int)point2.getY(); i++) {
                points.add(this.getPointFromY(line, i));
            }
        }
        return points;
    }

    @Override
	public Point3D_F32 findNearestPointBaseZ(Camera camera, List<Point3D_F32> points){
		float z = camera.getLocation().getZ();
		Point3D_F32 tempPoint = points.get(0);
		for (Point3D_F32 point : points) {
			if (Math.abs(point.getZ() - z) < Math.abs(tempPoint.getZ() - z)) {
				tempPoint = point;
			}
		}
		return tempPoint;
	}

    @Override
    public Point3D_F32 getPointFromZ(LineParametric3D_F32 line, float z) {
        if (line.getSlopeZ() == 0) {
            throw new RuntimeException("Can't get point from z.");
        }
        float t = (z - line.getZ())/line.getSlopeZ();
        float y = line.getY() + t*line.getSlopeY();
        float x = line.getX() + t*line.getSlopeX();
        return new Point3D_F32(x,y,z);
    }

    @Override
    public List<Point3D_F32> getPointListInRangeBaseZ(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2) {
        List<Point3D_F32> points = new ArrayList<>();
        if (point1.getZ() > point2.getZ()) {
            for (int i = (int)point1.getZ()-1; i > (int)point2.getZ(); i--) {
                points.add(this.getPointFromZ(line, i));
            }
        } else {
            for (int i = (int)point1.getZ()+1; i < (int)point2.getZ(); i++) {
                points.add(this.getPointFromZ(line, i));
            }
        }
        return points;
    }
}
