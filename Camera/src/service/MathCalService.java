package service;

import java.util.List;

import georegression.struct.line.LineParametric3D_F32;
import georegression.struct.point.Point3D_F32;
import model.Block;

public interface MathCalService {
	
	/**
	 * Check room or block is rectangular prism or not
	 */
	boolean rectangularCheck(Point3D_F32[] coordinates);
	
	/**
	 * Calculate volume rectangular prism
	 */
	float volumeRecCal(Point3D_F32[] coordinates);

	/**
	 * Get intersection between Block and line
	 */
	List<Point3D_F32> getInterBlockAndSegment(Block block, LineParametric3D_F32 line);
	
	/**
	 * Make line from two point
	 */
	LineParametric3D_F32 getLineParametric(Point3D_F32 p1, Point3D_F32 p2);
}
