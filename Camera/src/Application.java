import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import controller.BlockController;
import controller.CameraController;
import controller.RoomController;
import georegression.struct.point.Point3D_F32;
import model.Camera;
import service.ReadFileService;
import service.impl.ReadFileServiceImpl;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Application {

	private JFrame frmCameraProjector;
	private JTextField txtFileLink;
	private JTextField txtAccuracy;

	public static ReadFileService readFileService = new ReadFileServiceImpl();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmCameraProjector.setLocationRelativeTo(null);
					window.frmCameraProjector.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
		initialize();
	}

	private void initialize() {
		frmCameraProjector = new JFrame();
		frmCameraProjector.getContentPane().setBackground(Color.PINK);
		frmCameraProjector.setTitle("Camera Projector");
		frmCameraProjector.setBounds(100, 100, 808, 518);
		frmCameraProjector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCameraProjector.getContentPane().setLayout(null);

		JLabel lblTeam = new JLabel("Tokyo Team");
		lblTeam.setForeground(Color.RED);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setVerticalAlignment(SwingConstants.TOP);
		lblTeam.setFont(new Font("Showcard Gothic", Font.BOLD, 28));
		lblTeam.setBounds(277, 10, 241, 48);
		frmCameraProjector.getContentPane().add(lblTeam);

		JLabel lblClass = new JLabel("Object-oriented programming Class");
		lblClass.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setBounds(181, 390, 408, 41);
		frmCameraProjector.getContentPane().add(lblClass);

		JLabel lblLecturer = new JLabel("Lecturer: Nguyen Tien Thanh");
		lblLecturer.setBackground(Color.LIGHT_GRAY);
		lblLecturer.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecturer.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblLecturer.setBounds(191, 430, 408, 41);
		frmCameraProjector.getContentPane().add(lblLecturer);

		JLabel Memberslbl = new JLabel("MEMBERS");
		Memberslbl.setHorizontalAlignment(SwingConstants.LEFT);
		Memberslbl.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		Memberslbl.setBounds(24, 83, 110, 41);
		frmCameraProjector.getContentPane().add(Memberslbl);

		JLabel lblName1 = new JLabel("NGUYEN VIET HOANG");
		lblName1.setHorizontalAlignment(SwingConstants.LEFT);
		lblName1.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblName1.setBounds(24, 134, 201, 41);
		frmCameraProjector.getContentPane().add(lblName1);

		JLabel lblName2 = new JLabel("NGO VAN ANH");
		lblName2.setHorizontalAlignment(SwingConstants.LEFT);
		lblName2.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblName2.setBounds(24, 185, 201, 41);
		frmCameraProjector.getContentPane().add(lblName2);

		JLabel lblName3 = new JLabel("CHU TIEN DAT");
		lblName3.setHorizontalAlignment(SwingConstants.LEFT);
		lblName3.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblName3.setBounds(24, 236, 201, 41);
		frmCameraProjector.getContentPane().add(lblName3);

		JLabel lblName4 = new JLabel("NGUYEN TIEN DUNG");
		lblName4.setHorizontalAlignment(SwingConstants.LEFT);
		lblName4.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblName4.setBounds(24, 287, 201, 41);
		frmCameraProjector.getContentPane().add(lblName4);

		JLabel lblInput = new JLabel("INPUT DATA");
		lblInput.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblInput.setBounds(513, 83, 110, 41);
		frmCameraProjector.getContentPane().add(lblInput);

		JLabel lblFileLink = new JLabel("FILE LINK");
		lblFileLink.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileLink.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblFileLink.setBounds(271, 134, 110, 41);
		frmCameraProjector.getContentPane().add(lblFileLink);

		txtFileLink = new JTextField();
		txtFileLink.setBounds(391, 135, 352, 25);
		frmCameraProjector.getContentPane().add(txtFileLink);
		txtFileLink.setColumns(10);

		JLabel lblAccuracy = new JLabel("ACCURACY");
		lblAccuracy.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccuracy.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		lblAccuracy.setBounds(271, 198, 110, 41);
		frmCameraProjector.getContentPane().add(lblAccuracy);

		txtAccuracy = new JTextField();
		txtAccuracy.setBounds(391, 197, 352, 25);
		frmCameraProjector.getContentPane().add(txtAccuracy);
		txtAccuracy.setColumns(10);

		JButton btnCalculate = new JButton("CALCULATE");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int accu = 0;
				float percentObser = 0;
				boolean check = false;

				String fileLink = txtFileLink.getText();
				String accuracy = txtAccuracy.getText();

				try {
					accu = Integer.parseInt(accuracy);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Accuracy must be Integer number !!!");
				}
				if (50 <= accu && accu <= 300) {
					check = true;
				}

				if (accu == 10 || check) {

					try {
						// Get file's content
						List<String> strings = readFileService.readFilefromS(fileLink);
						// Get room coordinates
						Point3D_F32[] coorRoom = readFileService.getCoorRoom(strings, accu);

						// Initialize Room Controller
						RoomController roomController = new RoomController(coorRoom);

						// Get blocks' coordinates
						List<Point3D_F32[]> coorBlocks = readFileService.getCoorBlock(strings, accu);

						// Add block to Room
						for (Point3D_F32[] blockPoints : coorBlocks) {
							BlockController blockController = new BlockController(blockPoints);
							roomController.addBlock(blockController.getBlock());
						}

						// Get cameras' coordinates ( 1st, 2nd, 3rd is camera's location, 4th, 5th is
						// camera's angle)
						List<Float[]> camInfos = readFileService.getCamInfo(strings, accu);

						for (Float[] camPoints : camInfos) {
							roomController
									.addCamera(new CameraController(new Camera(new Point3D_F32(camPoints[0], camPoints[1], camPoints[2]),
											camPoints[3].intValue(), camPoints[4].intValue(), 10000)).getCameraModel());
						}
						// Calculate Observable Point
						roomController.calculateObservablePoint();

						percentObser = roomController.getObservablePercent();

						// Get state of points
						boolean[][][] state = roomController.getPointsState();

						ViewFrame viewFrame = new ViewFrame(state, percentObser);
						viewFrame.setLocationRelativeTo(frmCameraProjector);
						viewFrame.setVisible(true);
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, "File not found!!!!!!!!!");
						exc.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Accuracy must be 10 or in range 50 to 300 !!!");
				}

			}
		});
		btnCalculate.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		btnCalculate.setBounds(495, 310, 148, 48);
		frmCameraProjector.getContentPane().add(btnCalculate);

		JLabel lblNewLabel_2 = new JLabel("10:   Accurate to the decimeter");
		lblNewLabel_2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(391, 232, 352, 25);
		frmCameraProjector.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("100: Accurate to the centimeter");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(391, 252, 352, 25);
		frmCameraProjector.getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Accuracy can input in range from 50 to 300");
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNewLabel_2_1_1.setBounds(391, 272, 352, 25);
		frmCameraProjector.getContentPane().add(lblNewLabel_2_1_1);
	}
}
