package service;

import java.util.List;

import georegression.struct.line.LineParametric3D_F32;
import georegression.struct.point.Point3D_F32;
import model.Camera;

/**
 * LineService
 */
public interface LineService {
    /**
	 * find nearest point form camera, base X
	 */
	Point3D_F32 findNearestPointBaseX(Camera camera, List<Point3D_F32> points);
    /**
     * get Point from x
     */
    Point3D_F32 getPointFromX(LineParametric3D_F32 line, float x);
    /**
     * get Point List in range 2 point, order from point1 to point2, base x
     */
    List<Point3D_F32> getPointListInRangeBaseX(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2);
    /**
	 * find nearest point form camera, base Y
	 */
	Point3D_F32 findNearestPointBaseY(Camera camera, List<Point3D_F32> points);
    /**
     * get Point from y
     */
    Point3D_F32 getPointFromY(LineParametric3D_F32 line, float y);
    /**
     * get Point List in range 2 point, order from point1 to point2, base y
     */
    List<Point3D_F32> getPointListInRangeBaseY(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2);
    /**
	 * find nearest point form camera, base z
	 */
	Point3D_F32 findNearestPointBaseZ(Camera camera, List<Point3D_F32> points);
    /**
     * get Point from z
     */
    Point3D_F32 getPointFromZ(LineParametric3D_F32 line, float z);
    /**
     * get Point List in range 2 point, order from point1 to point2, base z
     */
    List<Point3D_F32> getPointListInRangeBaseZ(LineParametric3D_F32 line, Point3D_F32 point1, Point3D_F32 point2);
}