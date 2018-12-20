package EX3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BallAnimation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new BallAnimation();
    }

    public BallAnimation() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private int x = 0;
        private int y = 0;

        public TestPane() {
            Timer timer = new Timer(0, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveBall();
                    repaint();
                }
            });
            timer.start();
        }

        protected void moveBall() {
            x++;
            y++;
        }

        @Override
        public Dimension getPreferredSize() {
        	try {
				BufferedImage mapImage = ImageIO.read(new File("data//Ariel1.png"));
				return new Dimension(mapImage.getWidth(), mapImage.getHeight());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return new Dimension(0, 0);
            
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
			try {
				BufferedImage mapImage = ImageIO.read(new File("data//Ariel1.png"));
				g2d.drawImage(mapImage, 0, 0, mapImage.getWidth(), mapImage.getHeight(), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x, y, 50, 50);
            g2d.dispose();
        }

    }

}