package game;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends javax.swing.JFrame {
	private static final long serialVersionUID = -4369730830015653927L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame();
		
		frame.setTitle(title);
		Dimension size = new Dimension(width, height);
		
		frame.setMinimumSize(size);
		frame.setPreferredSize(size);
		frame.setMaximumSize(size);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
