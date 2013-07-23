package gamexo.player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {

	private static final int HUMAN = 0;
	private static final int COMPUTER = 1;
	private final int PLAYER_TYPE;
	public final Character PLAYER_CHAR;

	private Map<String, Integer> coords = new HashMap<String, Integer>();
	public String name = "Игрок";

	public Player(char marker) {
		this(marker, HUMAN);
	}

	public Player(char marker, int type) {
		PLAYER_TYPE = type;
		PLAYER_CHAR = marker;
		name += " '" + PLAYER_CHAR.toString() + "'";
	}

	public void readCoords() {
		System.out.println();
		switch (PLAYER_TYPE) {
			case HUMAN:
				readCoordsHuman();
				break;
			case COMPUTER:
				readCoordsComputer();
				break;
		}
	}

	private void readCoordsComputer() {
		getCoords().put("X", readCoord('X'));
		getCoords().put("Y", readCoord('Y'));
	}

	private void readCoordsHuman() {
		getCoords().put("X", readCoord('X'));
		getCoords().put("Y", readCoord('Y'));
	}

	private int readCoord(char marker) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print(name + ". Введите координаты " + marker + ": ");
			String inputValue = scanner.nextLine();

			try {
				return Integer.parseInt(inputValue) - 1;
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				readCoord(marker);
			}
		}
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

	/**
	 * @return the coords
	 */
	public Map<String, Integer> getCoords() {
		return coords;
	}

}
