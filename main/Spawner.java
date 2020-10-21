package main;

import java.util.Random;

public class Spawner {
	private int tick;
	private Handler handler;
	private Random r;
	private GameObject player;
	private int size, side, xSpeed, ySpeed, direc;

	public Spawner(Handler handler) {
		this.handler = handler;
		r = new Random();

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
	}

	public void tick() {
		tick++;
		if (tick > 10) {
			side = r.nextInt(4) + 1;
			size = r.nextInt(player.getSize() + 60) + 10;
			direc = r.nextBoolean() ? - 1 : 1;

			if (side == 1) {
				xSpeed = r.nextInt(3) + 1;
				ySpeed = (r.nextInt(3) + 1) * direc;
				handler.addObject(new Enemy(0 - size, r.nextInt((int) Game.screenSize.getHeight()), ID.Enemy, handler,
						size, xSpeed, ySpeed));
				tick = 0;
			}

			if (side == 2) {
				xSpeed = (r.nextInt(3) + 1) * -1;
				ySpeed = (r.nextInt(3) + 1) * direc;
				handler.addObject(new Enemy((int) Game.screenSize.getWidth(),
						r.nextInt((int) Game.screenSize.getHeight()), ID.Enemy, handler, size, xSpeed, ySpeed));
				tick = 0;
			}

			if (side == 3) {
				xSpeed = (r.nextInt(3) + 1) * direc;
				ySpeed = r.nextInt(3) + 1;
				handler.addObject(new Enemy(r.nextInt((int) Game.screenSize.getWidth()), 0 - size, ID.Enemy, handler,
						size, xSpeed, ySpeed));
				tick = 0;
			}

			if (side == 4) {
				xSpeed = (r.nextInt(3) + 1) * direc;
				ySpeed = (r.nextInt(3) + 1) * -1;
				handler.addObject(new Enemy(r.nextInt((int) Game.screenSize.getWidth()),
						(int) Game.screenSize.getHeight(), ID.Enemy, handler, size, xSpeed, ySpeed));
				tick = 0;
			}
		}
	}

}
