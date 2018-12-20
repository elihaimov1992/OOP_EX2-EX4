package EX3;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

	public MyFrame() {

        initUI();
    }

    private void initUI() {

        add(new GameBoard());

        setResizable(false);
        pack();
        
//        JButton openBtn = new JButton("Open");
//        openBtn.setBounds(100, 100, 50, 80);
//        add(openBtn);

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
