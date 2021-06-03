package service.impl;

import java.util.ArrayList;
import java.util.List;

import georegression.metric.Intersection3D_F32;
import georegression.struct.line.LineParametric3D_F32;
import georegression.struct.line.LineSegment3D_F32;
import georegression.struct.plane.PlaneNormal3D_F32;
import georegression.struct.point.Point3D_F32;
import georegression.struct.point.Vector3D_F32;
import model.Block;
import service.MathCalService;

public class MathCalServiceImpl implements MathCalService{

	@Override
	public boolean rectangularCheck(Point3D_F32[] coordinates) {
		Point3D_F32 O = new Point3D_F32(0, 0, 0);
		Point3D_F32 x = new Point3D_F32(1, 0, 0);
		
		
		LineSegment3D_F32 s1 = new LineSegment3D_F32(coordinates[0], coordinates[1]);
		LineSegment3D_F32 s2 = new LineSegment3D_F32(coordinates[2], coordinates[3]);
		LineSegment3D_F32 s3 = new LineSegment3D_F32(coordinates[4], coordinates[5]);
		LineSegment3D_F32 s4 = new LineSegment3D_F32(coordinates[6], coordinates[7]);
		
		LineSegment3D_F32 s5 = new LineSegment3D_F32(coordinates[0], coordinates[3]);
		LineSegment3D_F32 s6 = new LineSegment3D_F32(coordinates[4], coordinates[7]);
		LineSegment3D_F32 s7 = new LineSegment3D_F32(coordinates[1], coordinates[2]);
		LineSegment3D_F32 s8 = new LineSegment3D_F32(coordinates[5], coordinates[6]);
		
		LineSegment3D_F32 s9 = new LineSegment3D_F32(coordinates[0], coordinates[4]);
		LineSegment3D_F32 s10 = new LineSegment3D_F32(coordinates[3], coordinates[7]);
		LineSegment3D_F32 s11 = new LineSegment3D_F32(coordinates[1], coordinates[5]);
		LineSegment3D_F32 s12 = new LineSegment3D_F32(coordinates[2], coordinates[6]);
		
		Vector3D_F32 v0 = new Vector3D_F32(O, x);
		Vector3D_F32 v1 = new Vector3D_F32(coordinates[0], coordinates[1]);
		Vector3D_F32 v2 = new Vector3D_F32(coordinates[0], coordinates[3]);
		Vector3D_F32 v3 = new Vector3D_F32(coordinates[0], coordinates[4]);
		
		
		if (s1.getLength() == s2.getLength() && s2.getLength() == s3.getLength() && s3.getLength() == s4.getLength()) {
			if (s5.getLength() == s6.getLength() && s6.getLength() == s7.getLength() && s7.getLength() == s8.getLength()) {
				if (s9.getLength() == s10.getLength() && s10.getLength() == s11.getLength() && s11.getLength() == s12.getLength()) {
					if (v1.dot(v2) == 0 && v1.dot(v3) == 0 && v2.dot(v3) == 0 && v0.dot(v3) == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public float volumeRecCal(Point3D_F32[] coordinates) {
		LineSegment3D_F32 a = new LineSegment3D_F32(coordinates[0], coordinates[1]);
		LineSegment3D_F32 b = new LineSegment3D_F32(coordinates[0], coordinates[3]);
		LineSegment3D_F32 c = new LineSegment3D_F32(coordinates[0], coordinates[4]);
		
		return a.getLength()*b.getLength()*c.getLength();
	}

	@Override
	public List<Point3D_F32> getInterBlockAndSegment(Block block, LineParametric3D_F32 line) {
		
		List<Point3D_F32> results = new ArrayList<>();
		
		Point3D_F32[] coors = block.getCoordinates();
		
		Vector3D_F32 v1 = new Vector3D_F32(coors[0], coors[1]);
		Vector3D_F32 v2 = new Vector3D_F32(coors[0], coors[3]);
		Vector3D_F32 v3 = new Vector3D_F32(coors[0], coors[4]);
		
		Point3D_F32 inter1 = new Point3D_F32();
		PlaneNormal3D_F32 m1 = new PlaneNormal3D_F32(coors[0], v1);
		if (Intersection3D_F32.intersection(m1, line, inter1)) {
			if (coors[0].y <= inter1.y && inter1.y <= coors[3].y) {
				if (coors[0].z < inter1.z && inter1.z <= coors[4].z) {
					results.add(inter1);
				}
			}
		}
		
		Point3D_F32 inter2 = new Point3D_F32();
		PlaneNormal3D_F32 m2 = new PlaneNormal3D_F32(coors[1], v1);
		if (Intersection3D_F32.intersection(m2, line, inter2)) {
			if (coors[1].y <= inter2.y && inter2.y <= coors[2].y) {
				if (coors[1].z <= inter2.z && inter2.z <= coors[5].z) {
					results.add(inter2);
				}
			}
		}
		
		Point3D_F32 inter3 = new Point3D_F32();
		PlaneNormal3D_F32 m3 = new PlaneNormal3D_F32(coors[0], v2);
		if (Intersection3D_F32.intersection(m3, line, inter3)) {
			if (coors[0].x <= inter3.x && inter3.x <= coors[1].x) {
				if (coors[0].z <= inter3.z && inter3.z <= coors[4].z) {
					results.add(inter3);
				}
			}
		}
		
		Point3D_F32 inter4 = new Point3D_F32();
		PlaneNormal3D_F32 m4 = new PlaneNormal3D_F32(coors[3], v2);
		if (Intersection3D_F32.intersection(m4, line, inter4)) {
			if (coors[3].x <= inter4.x && inter4.x <= coors[2].x) {
				if (coors[3].z <= inter4.z && inter4.z <= coors[7].z) {
					results.add(inter4);
				}
			}
		}
		
		Point3D_F32 inter5 = new Point3D_F32();
		PlaneNormal3D_F32 m5 = new PlaneNormal3D_F32(coors[0], v3);
		if (Intersection3D_F32.intersection(m5, line, inter5)) {
			if (coors[0].x <= inter5.x && inter5.x <= coors[1].x) {
				if (coors[0].y <= inter5.y && inter5.y <= coors[3].y) {
					results.add(inter5);
				}
			}
		}
		Point3D_F32 inter6 = new Point3D_F32();
		PlaneNormal3D_F32 m6 = new PlaneNormal3D_F32(coors[4], v3);
		if (Intersection3D_F32.intersection(m6, line, inter6)) {
			if (coors[4].x <= inter6.x && inter6.x <= coors[5].x) {
				if (coors[4].y <= inter6.y && inter6.y <= coors[7].y) {
					results.add(inter6);
				}
			}
		}
		return results;
	}

	@Override
	public LineParametric3D_F32 getLineParametric(Point3D_F32 p1, Point3D_F32 p2) {
		float slopeX = p1.x - p2.x;
		float slopeY = p1.y - p2.y;
		float slopeZ = p1.z - p2.z;
		return new LineParametric3D_F32(p1.x, p1.y, p1.z, slopeX, slopeY, slopeZ);
	}
}
