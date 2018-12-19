package EX3;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Geom.Point3D;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class guiTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiTest window = new guiTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public guiTest() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		Point3D start = new Point3D(32.105716, 35.202373);
		Point3D end = new Point3D(32.101911, 35.212528);
		Map map = new Map("data//Ariel1.png", start, end);
		Graphics g = map.mapImage.getGraphics();
		frame = new JFrame();
//		frame.setBounds(100, 100, map.mapImage.getWidth()+18, map.mapImage.getHeight()+65);
//		frame.setBounds(100, 100, 605, 361);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		toolBar.setPreferredSize(new Dimension(map.mapImage.getWidth(), 25));
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frame.getContentPane()}));
		
		MyFrame2 mf = new MyFrame2();
		frame.getContentPane().add(mf, BorderLayout.CENTER);
		frame.setSize(1500, 700);
		
		JButton btnOpen = new JButton("open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(btnOpen);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					//This is where a real application would open the file.
					System.out.println("Opening: " + file.getName() + "\n");
					Game game = new Game(file);
					try {
						mf.showGame(mf.getGraphics(), game);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("File not selected");
//				}

			}
		}});
		toolBar.add(btnOpen);

		JButton btnSave = new JButton("save");
		toolBar.add(btnSave);

		JButton btnStartPause = new JButton("start/pause");
		toolBar.add(btnStartPause);
		
		

	}

}
