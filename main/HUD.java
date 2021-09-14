package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import main.Game.STATE;

public class HUD extends MouseAdapter {
	public static boolean reset = false;
	public void tick() {

	}

	public void mouseReleased(MouseEvent e) {
		if (Game.gameState == STATE.Menu) {
			reset = true;
		}
	}

	public void render(Graphics g) {
		Points(g);
		gameOver(g);
	}

	public void Points(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Points: " + String.valueOf(Player.points), 20, 20);
	}

	public void gameOver(Graphics g) {
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(
				System.getProperty("user.dir") + "\\font\\ClickerScript-Regular.ttf"))
					.deriveFont(100f);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);

			g.setFont(customFont);
			g.setColor(Color.white);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		if (Game.gameState == STATE.Menu) {
			g.drawString("Too Bad! Try Again?", (int) Game.screenSize.getWidth() / 2 - 380,
					(int) Game.screenSize.getWidth() / 2 - 280);
		}

	}
}
