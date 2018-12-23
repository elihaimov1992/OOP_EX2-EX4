package EX3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.MouseEvent;

/**
 * A JFrame window the contains the menu and the game display.
 */
public class MyFrame extends JFrame{

	boolean packmenRunning = false;

	public MyFrame() {

		initUI();
	}

	/**
	 * Initialize the window:
	 * create the menu and buttons, and the game board.
	 */
	private void initUI() {

		GameBoard board = new GameBoard();
		add(board);

		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		JButton btnOpen = new JButton("Open");
		btnOpen.setIcon(new ImageIcon("data/open.png"));
		menuBar.add(btnOpen);

		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon("data/save.png"));
		menuBar.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		menuBar.add(btnClear);

		JButton btnRun = new JButton("Run");
		btnRun.setIcon(new ImageIcon("data/run.png"));
		menuBar.add(btnRun);
		
		JButton btnAddPack = new JButton("Add Packman");
		menuBar.add(btnAddPack);

		JButton btnAddFruit = new JButton("Add Fruit");
		menuBar.add(btnAddFruit);
		
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				//				board.stopPackmans();

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					board.game = new Game(selectedFile);
				}
			}         
		});  

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.game.writeCsv();
			}         
		}); 

		btnRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ShortestPathAlgo spa = new ShortestPathAlgo(board.game);
				spa.findPaths();
				
				Iterator<Packman> it = board.game.getPackmanArrayList().iterator();
				while(it.hasNext()) {
					Packman pack = it.next();
					System.out.println(pack.path.distance());
				}
				
				board.simulate = new Simulate(board.game);
				board.runSimulate();
				//					board.runPackmans();

			}         
		});  

		btnAddPack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.packOrFruit = 0;
			}         
		}); 
		
		btnAddFruit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.packOrFruit = 1;
			}         
		}); 
		
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator<Packman> it = board.game.getPackmanArrayList().iterator();
				while(it.hasNext()) {
					Packman pack = it.next();
					pack.running = false;
				}
				board.game.fruits.clear();
				board.game.packmans.clear();
				
			}         
		}); 
		
		setJMenuBar(menuBar);

		setResizable(false);
		pack();

		setTitle("Packman Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}    

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new MyFrame();
			ex.setVisible(true);
		});
	}

}