import java.util.List;

import javax.swing.SwingUtilities;

import controller.BlockController;
import controller.RoomController;
import georegression.struct.point.Point3D_F32;
import model.Camera;
import service.ReadFileService;
import service.impl.ReadFileServiceImpl;
import view.FirstXPlane;
import view.FirstYPlane;
import view.LastXPlane;
import view.LastYPlane;
import view.ParentPlane;
import view.ZPlane;


public class Main {
    public static void main(String[] args) throws Exception {
    	ReadFileService readFileService = new ReadFileServiceImpl();
    	
    	int accu = 100;
    	
    	// Get file's content
    	List<String> strings = readFileService.readFilefromS(args[0]);
		
    	// Get room coordinates
		Point3D_F32[] coorRoom = readFileService.getCoorRoom(strings, accu);
		for (Point3D_F32 point3d_F32 : coorRoom) {
			System.out.println(point3d_F32);
		}

		RoomController roomController = new RoomController(coorRoom);
		System.out.println("----------");
		// Get number of Blocks
		int nOB = readFileService.getNumOfBlock(strings);
		System.out.println(nOB);
		
		// Get blocks' coordinates
		List<Point3D_F32[]> coorBlocks = readFileService.getCoorBlock(strings, accu);
		for (Point3D_F32[] point3d_F32s : coorBlocks) {
			for (Point3D_F32 coor : point3d_F32s) {
				System.out.println(coor);
			}
		}
		for (Point3D_F32[] blockPoints : coorBlocks) {
			BlockController blockController = new BlockController(blockPoints);
			roomController.addBlock(blockController.getBlock());
		}
		System.out.println("-----------");
		// Get number of Cameras
		int numOfCam = readFileService.getNumOfCam(strings);
		System.out.println(numOfCam);
		
		// Get cameras' coordinates ( 1st, 2nd, 3rd is camera's location, 4th, 5th is camera's angle)
		List<Float[]> camInfos = readFileService.getCamInfo(strings, accu);
		for (Float[] camInfo : camInfos) {
			for (Float floats : camInfo) {
				System.out.print(floats+" ");
			}
			System.out.println();
		}
		System.out.println("-----------");
		for (Float[] camPoints : camInfos) {
			roomController.addCamera(new Camera(new Point3D_F32(camPoints[0],camPoints[1],camPoints[2]),camPoints[3].intValue(), camPoints[4].intValue(), 10000));
		}

		roomController.calculateObservablePoint();
		System.out.println(roomController.getObservablePercent());

		boolean[][][] state = roomController.getPointsState();
		ParentPlane[] planes = new ParentPlane[5];
		planes[0] = new FirstXPlane(state);
		planes[1] = new LastXPlane(state);
		planes[2] = new FirstYPlane(state);
		planes[3] = new LastYPlane(state);
		planes[4] = new ZPlane(state);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			   for (ParentPlane plane : planes) {
				   plane.drawPlane();
			   }
			}
		 });
    }
}
