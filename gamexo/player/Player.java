package gamexo.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {

	/**
	 * Class Constants
	 */
	private static final int HUMAN = 0;
	private static final int COMPUTER = 1;
	/**
	 * Variables
	 */
	private int PLAYER_TYPE;
	private Map<String, Integer> coords = new HashMap<String, Integer>();
	private String name = "Игрок";
	public Character PLAYER_CHAR;

	public Player init(char marker) throws InterruptedException {
		PLAYER_CHAR = marker;
		readPlayerType();
		readPlayerName();
		setName(getName() + " [" + marker + "]");
		return this;
	}

	private void readPlayerType() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println((HUMAN + 1) + ". Человек");
			System.out.println((COMPUTER + 1) + ". Компьютер");
			System.out.print("Выберите тип игрока для маркера `" + PLAYER_CHAR + "` [" + (HUMAN + 1) + "]: ");
			String inputValue = scanner.nextLine();
			if ("".equals(inputValue)) {
				inputValue = "1";
			}
			try {
				int type = Integer.parseInt(inputValue) - 1;
				if (type != HUMAN && type != COMPUTER) {
					System.err.println("Не правильный ввод!");
					System.err.println("Повторите выбор.");
					Thread.currentThread().sleep(100);
					readPlayerType();
					break;
				}
				switch (type) {
					case HUMAN:
						System.out.println("Выбраный тип игрока \"Человек\"");
						break;
					case COMPUTER:
						System.out.println("Выбраный тип игрока \"Компьютер\"");
						break;
				}
				PLAYER_TYPE = type;
				break;
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				readPlayerType();
			}
		}
	}

	private void readPlayerName() {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		while (true) {
			System.out.print("Введите имя игрока для маркера `" + PLAYER_CHAR + "` [" + getName() + "]: ");
			String inputValue = scanner.nextLine();

			if ("".equals(inputValue)) {
				inputValue = getName();
			}
			setName(inputValue);
			System.out.println("Имя игрока для маркера `" + PLAYER_CHAR + "`: " + getName());
			break;
		}
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
		setCoord("X", readCoord('X'));
		setCoord("Y", readCoord('Y'));
	}

	private void readCoordsHuman() {
		setCoord("X", readCoord('X'));
		setCoord("Y", readCoord('Y'));
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
	private void setName(String newName) {
		name = newName;
	}

	private void setCoord(String marker, int coord) {
		coords.put(marker, coord);
	}


	/**
	 * @return the coords
	 */
	public Map<String, Integer> getCoords() {
		return coords;
	}
}
