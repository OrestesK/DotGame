package main;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -473349850293143017L;
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public boolean running = false;
	private Thread thread;
	private Handler handler;
	private HUD hud;
	private KeyInput keyInput;
	private Spawner spawner;
	private Highscore highScore;
	private int temp = 0;

	public Game() {
		new Window("PolkaDotGame", this);
		handler = new Handler();
		keyInput = new KeyInput();
		highScore = new Highscore();
		hud = new HUD();
		this.addKeyListener(keyInput);
		this.addMouseListener(hud);
		startGame();
	}

	public enum STATE {
		Menu, Game, Help
	};

	public static STATE gameState = STATE.Game;

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		int bug = 0;
		while (running) {
			bug++;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (bug > 10) {
					tick();
				}
				delta--;

			}
			if (running) {
				if (bug > 10) {
					render();
				}
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void startGame() {
		handler.addObject(new Player((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2, ID.Player,
				handler, 20, 0, 0));
		spawner = new Spawner(handler);
		try {
			Robot robot = new Robot();
			robot.mouseMove((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
		} catch (AWTException e) {
		}
	}

	private void tick() {
		if (temp == 0) {
			try {
				Thread.sleep(1000);
				temp = 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
				temp = 1;
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		super.paint(g);
		handler.render(g);
		hud.render(g);

		g.dispose();
		bs.show();
	}

	private void reset() {
		if (HUD.reset == true) {
			handler.clearEnemies();
			HUD.reset = false;
			highScore.updateScore();
			Player.points = 0;
			Game.gameState = STATE.Game;
			startGame();
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
