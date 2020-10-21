package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import main.Game.STATE;

public class Mouse extends MouseAdapter {
	private Handler handler;
	private Game game;

	public Mouse(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
	}

}
