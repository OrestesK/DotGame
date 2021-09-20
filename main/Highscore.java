package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Highscore {
	public static int score = 0;
	File highscore;

	public Highscore() {
		highscore = new File(System.getProperty("user.dir") + "\\score\\highscore.txt");
		Scanner reader;
		try {
			reader = new Scanner(highscore);
			while (reader.hasNextLine()) {
				score = Integer.valueOf(reader.nextLine());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateScore() {
		FileWriter writer;
		try {
			writer = new FileWriter(highscore);
			if (Player.points > score) {
				writer.write(String.valueOf(Player.points));
				score = Player.points;
			} else {
				writer.write(String.valueOf(score));
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getScore() {
		return score;
	}
}
