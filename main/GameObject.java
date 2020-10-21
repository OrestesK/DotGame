package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected int size;

	public GameObject(float x, float y, ID id, int size, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.size = size;
		this.velX = velX;
		this.velY = velY;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void drawBounds(Graphics g);

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelX() {
		return (int) velX;
	}

	public int getVelY() {
		return (int) velY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int value) {
		size = value;
	}
}
