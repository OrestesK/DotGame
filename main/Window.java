package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 7046162652717156913L;
	private Dimension dimension = new Dimension((int)Game.screenSize.getWidth(), (int)Game.screenSize.getHeight());

	public Window(String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.getContentPane().setBackground(Color.black);
		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		game.start();
	}
}
