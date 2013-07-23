package gamexo.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

	private static final int HUMAN = 0;
	private static final int COMPUTER = 1;
	private final int PLAYER_TYPE;
	public final int PLAYER_NUM;
	public String name = "Anonimus";

	public Player(int num) {
		this(num, HUMAN);
	}

	public Player(int num, int type) {
		this.PLAYER_TYPE = type;
		this.PLAYER_NUM = num;
	}

	public int[] readCoords() throws IOException {
		int[] coords = new int[2];

		switch (this.PLAYER_TYPE) {
			case HUMAN:
				coords = this.readCoordsHuman();
				break;
			case COMPUTER:
				coords = this.readCoordsComputer();
				break;
		}
		return coords;
	}

	private int[] readCoordsComputer() throws IOException {
		int[] coords = new int[2];
		return coords;
	}

	private int[] readCoordsHuman() throws IOException {
		BufferedReader bufferY = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader bufferX = new BufferedReader(new InputStreamReader(System.in));
		int[] coords = new int[2];

		while (true) {
			System.out.println("Введите координаты X:");
			String inputValueX = bufferX.readLine();

			try {
				coords[0] = Integer.parseInt(inputValueX);
				break;
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			}
		}

		while (true) {
			System.out.println("Введите координаты Y:");
			String inputValueY = bufferY.readLine();

			try {
				coords[1] = Integer.parseInt(inputValueY);
				break;
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			}
		}

		return coords;
	}

	/**
	 * Получение имени игрока
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Установка имени игрока
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
