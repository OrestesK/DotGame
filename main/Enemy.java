package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {

	@SuppressWarnings("unused")
	private Handler handler;
	private Random r;
	@SuppressWarnings("unused")
	private GameObject player;
	private int rc;
	private int gc;
	private int bc;

	public Enemy(float x, float y, ID id, Handler handler, int size, int velX, int velY) {
		super(x, y, id, size, velX, velY);
		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}

		r = new Random();
		rc = r.nextInt(256);
		gc = r.nextInt(256);
		bc = r.nextInt(256);
	}

	public void drawBounds(Graphics g) {
		g.setColor(Color.red);
		g.drawOval((int) x, (int) y, size, size);
	}

	public void tick() {
		x += velX;
		y += velY;
		if (x - (size * 2) > Game.screenSize.getWidth()) {
			handler.removeObject(this);
		}
		if (y - (size * 2) > Game.screenSize.getHeight()) {
			handler.removeObject(this);
		}
		if (x + size < 0) {
			handler.removeObject(this);
		}
		if (y + size < 0) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(rc, gc, bc));
		g.fillOval((int) x, (int) y, size, size);
		drawBounds(g);
	}

}
