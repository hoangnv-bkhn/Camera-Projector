package controller;

import java.util.ArrayList;
import java.util.List;

import georegression.struct.line.LineParametric3D_F32;
import georegression.struct.point.Point3D_F32;
import model.Block;
import model.Camera;
import model.Room;
import service.LineService;
import service.MathCalService;
import service.RoomService;
import service.impl.LineServiceImpl;
import service.impl.MathCalServiceImpl;
import service.impl.RoomServiceImpl;

public class RoomController {
    public static final boolean OBSERVABLE = true;
    public static final boolean UNOBSERVABLE = false;

    private Room roomModel;
    private int x, y, z;
    private int x0, y0, z0;
    private boolean pointState[][][];
    private RoomService roomService = new RoomServiceImpl();
    private MathCalService mathCalService = new MathCalServiceImpl();
    private LineService lineService = new LineServiceImpl();

    public RoomController(Point3D_F32 coordinates[]) {
        roomService = new RoomServiceImpl();
        mathCalService = new MathCalServiceImpl();
        lineService = new LineServiceImpl();
        if (coordinates.length == 8) {
            roomModel = new Room(coordinates);
            if (!roomService.checkValidRoom(roomModel)) {
                throw new RuntimeException("Invalid room.");
            }
            initialize();
        } else {
            throw new RuntimeException("Invalid number of points.");
        }
    }

    private void initialize() {
        x0 = (int)roomModel.getCoordinates()[0].getX();
        y0 = (int)roomModel.getCoordinates()[0].getY();
        z0 = (int)roomModel.getCoordinates()[0].getZ();
        x = (int)roomModel.getCoordinates()[6].getX() - x0;
        y = (int)roomModel.getCoordinates()[6].getY() - y0;
        z = (int)roomModel.getCoordinates()[6].getZ() - z0;
        pointState = new boolean[x][y][z];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    pointState[i][j][k] = UNOBSERVABLE;
                }
            }
        }
    }

    public void addBlock(Block block) {
        if (!roomService.isValidBlockLocation(roomModel, block)) {
            throw new RuntimeException("Invalid block location");
        }
        roomModel.addBlock(block);
    }

    public void addCamera(Camera camera) {
        if (!roomService.isValidCameraLocation(roomModel, camera)) {
            throw new RuntimeException("Invalid camera location");
        }
        roomModel.addCamera(camera);
    }

    public void calculateObservablePoint() {
        for (Camera camera : roomModel.getCameras()) {
            Point3D_F32[] points = roomService.findFieldOfView(roomModel, camera);
            
            if (points[0].getX() == points[1].getX() && points[0].getX() == points[2].getX()) {
                for (int i = (int)points[0].getZ(); i < (int)points[2].getZ(); i++) {
                    for (int j = (int)points[0].getY(); j < (int)points[1].getY(); j++) {
                        Point3D_F32 curPoint = new Point3D_F32(points[0].getX(), j, i);
                        LineParametric3D_F32 line = mathCalService.getLineParametric(camera.getLocation(), curPoint);
                        List<Point3D_F32> intersections = new ArrayList<>();
                        for (Block block : roomModel.getBlocks()) {
                            intersections.addAll(mathCalService.getInterBlockAndSegment(block, line));
                        }
                        intersections.add(curPoint);
                        Point3D_F32 nearestPoint = lineService.findNearestPointBaseX(camera, intersections);
                        List<Point3D_F32> obserPoints = lineService.getPointListInRangeBaseX(line, camera.getLocation(), nearestPoint);
                        for (Point3D_F32 obserPoint : obserPoints) {
                            float obserX = obserPoint.getX();
                            float obserY = obserPoint.getY();
                            float obserZ = obserPoint.getZ();
                            if (obserX < x0 || obserX >= (this.x + x0)) {
                                continue;
                            }
                            if (obserY < y0 || obserY >= (this.y + y0)) {
                                continue;
                            }
                            if (obserZ < z0 || obserZ >= (this.z + z0)) {
                                continue;
                            }
                            //System.out.println(obserX + " " + obserY + " " + obserZ);
                            this.pointState[(int)obserX - x0][(int)obserY - x0][(int)obserZ - z0] = OBSERVABLE;
                        }
                    }
                }
            } else if (points[0].getY() == points[1].getY() && points[0].getY() == points[2].getY()) {
                for (int i = (int)points[0].getZ(); i < (int)points[2].getZ(); i++) {
                    for (int j = (int)points[0].getX(); j < (int)points[1].getX(); j++) {
                        Point3D_F32 curPoint = new Point3D_F32(j, points[0].getY(), i);
                        LineParametric3D_F32 line = mathCalService.getLineParametric(camera.getLocation(), curPoint);
                        List<Point3D_F32> intersections = new ArrayList<>();
                        for (Block block : roomModel.getBlocks()) {
                            intersections.addAll(mathCalService.getInterBlockAndSegment(block, line));
                        }
                        intersections.add(curPoint);
                        Point3D_F32 nearestPoint = lineService.findNearestPointBaseY(camera, intersections);
                        List<Point3D_F32> obserPoints = lineService.getPointListInRangeBaseY(line, camera.getLocation(), nearestPoint);
                        for (Point3D_F32 obserPoint : obserPoints) {
                            float obserX = obserPoint.getX();
                            float obserY = obserPoint.getY();
                            float obserZ = obserPoint.getZ();
                            if (obserX < x0 || obserX >= (this.x + x0)) {
                                continue;
                            }
                            if (obserY < y0 || obserY >= (this.y + y0)) {
                                continue;
                            }
                            if (obserZ < z0 || obserZ >= (this.z + z0)) {
                                continue;
                            }
                            //System.out.println(obserX + " " + obserY + " " + obserZ);
                            this.pointState[(int)obserX - x0][(int)obserY - x0][(int)obserZ - z0] = OBSERVABLE;
                        }
                    }
                }
            } else {
                for (int i = (int)points[0].getX(); i < (int)points[1].getX(); i++) {
                    for (int j = (int)points[0].getY(); j < (int)points[2].getY(); j++) {
                        Point3D_F32 curPoint = new Point3D_F32(i, j, points[0].getZ());
                        LineParametric3D_F32 line = mathCalService.getLineParametric(camera.getLocation(), curPoint);
                        List<Point3D_F32> intersections = new ArrayList<>();
                        for (Block block : roomModel.getBlocks()) {
                            intersections.addAll(mathCalService.getInterBlockAndSegment(block, line));
                        }
                        intersections.add(curPoint);
                        Point3D_F32 nearestPoint = lineService.findNearestPointBaseZ(camera, intersections);
                        List<Point3D_F32> obserPoints = lineService.getPointListInRangeBaseZ(line, camera.getLocation(), nearestPoint);
                        for (Point3D_F32 obserPoint : obserPoints) {
                            float obserX = obserPoint.getX();
                            float obserY = obserPoint.getY();
                            float obserZ = obserPoint.getZ();
                            if (obserX < x0 || obserX >= (this.x + x0)) {
                                continue;
                            }
                            if (obserY < y0 || obserY >= (this.y + y0)) {
                                continue;
                            }
                            if (obserZ < z0 || obserZ >= (this.z + z0)) {
                                continue;
                            }
                            //System.out.println(obserX + " " + obserY + " " + obserZ);
                            this.pointState[(int)obserX - x0][(int)obserY - x0][(int)obserZ - z0] = OBSERVABLE;
                        }
                    }
                }
            }
        }
    }

    public boolean[][][] getPointsState() {
        return this.pointState;
    }

    public float getObservablePercent() {
        float count = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    if (pointState[i][j][k] == OBSERVABLE) {
                        count++;
                    }
                }
            }
        }
        float result = count/((x-x0)*(y-y0)*(z-z0));
        return result;
    }
}
