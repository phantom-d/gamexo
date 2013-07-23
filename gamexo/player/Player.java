package gamexo.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

	private static final int HUMAN = 0;
	private static final int COMPUTER = 1;
	private final int PLAYER_TYPE;
	public final Character PLAYER_CHAR;
	public String name = "Игрок";

	public Player(char marker) {
		this(marker, HUMAN);
	}

	public Player(char marker, int type) {
		PLAYER_TYPE = type;
		PLAYER_CHAR = marker;
		name += " '" + PLAYER_CHAR.toString() + "'";
	}

	public int[] readCoords() throws IOException {
		int[] coords = new int[2];

		switch (PLAYER_TYPE) {
			case HUMAN:
				coords = readCoordsHuman();
				break;
			case COMPUTER:
				coords = readCoordsComputer();
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
			System.out.println(name + ". Введите координаты X:");
			String inputValueX = bufferX.readLine();

			try {
				coords[0] = Integer.parseInt(inputValueX) - 1;
				break;
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			}
		}

		while (true) {
			System.out.println(name + ". Введите координаты Y:");
			String inputValueY = bufferY.readLine();

			try {
				coords[1] = Integer.parseInt(inputValueY) - 1;
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
		return name;
	}

	/**
	 * Установка имени игрока
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		name = name;
	}
}
