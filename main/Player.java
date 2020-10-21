package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import main.Game.STATE;

public class Player extends GameObject {
	Random r = new Random();
	Handler handler;
	private double mouseX, mouseY;
	public static int points = 0;

	public Player(int x, int y, ID id, Handler handler, int size, int velX, int velY) {
		super(x, y, id, size, velX, velY);
		this.handler = handler;
	}

	public void tick() {
		mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = MouseInfo.getPointerInfo().getLocation().getY();

		x = (float) mouseX - size / 2;
		y = (float) mouseY - size / 2;

		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) x, (int) y, size, size);
		drawBounds(g);
	}

	public void collision() {
		// make a line between the two objects, check if the distance is smaller than their combined radius
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Enemy) {
				GameObject temp = handler.object.get(i);

				Line2D tempLine = new Line2D.Float(x + size / 2, y + size / 2, temp.getX() + temp.getSize() / 2,
						temp.getY() + temp.getSize() / 2);
				Point2D one = tempLine.getP1();
				Point2D two = tempLine.getP2();

				double distance = one.distance(two);

				if (distance < size + temp.getSize() / 2 - size/2) {
					if (size > temp.getSize()) {
						handler.removeObject(temp);
						size += 2;
						points++;
					}
					else {
						handler.removeObject(this);
						Game.gameState = STATE.Menu;
					}
				}
			}
		}

	}

	public void drawBounds(Graphics g) {
		g.setColor(Color.red);
		g.drawOval((int) x, (int) y, size, size);
	}
}
