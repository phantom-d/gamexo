package gamexo.player;

import java.util.*;

public class Player {

	/**
	 * Class Constants
	 */
	private static final int HUMAN = 0;
	private static final int COMPUTER = 1;
	private static final int REMOTE= 2;
	private static final int PLAYER_LOCAL= 0;
	private static final int PLAYER_REMOTE= 1;
	/**
	 * Local Constants
	 */
	private final int REMOTE_USER;
	/**
	 * Variables
	 */
	private int playerType;
	private Map<String, Integer> coords = new HashMap<String, Integer>();
	private String name = "Игрок";
	private char playerChar;

	public Player() {
		this(PLAYER_LOCAL);
	}

	public Player(int remoteUser) {
		REMOTE_USER = remoteUser;
	}

	public Player init(char marker) throws InterruptedException {
		setPlayerChar(marker);
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
			System.out.print("Выберите тип игрока для маркера `" + getPlayerChar() + "` [" + (HUMAN + 1) + "]: ");
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
				} else {
					switch (type) {
						case HUMAN:
							System.out.println("Выбраный тип игрока \"Человек\"");
							break;
						case COMPUTER:
							System.out.println("Выбраный тип игрока \"Компьютер\"");
							break;
					}
					playerType = type;
					break;
				}
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
			System.out.print("Введите имя игрока для маркера `" + getPlayerChar() + "` [" + getName() + "]: ");
			String inputValue = scanner.nextLine();

			if ("".equals(inputValue)) {
				inputValue = getName();
			}
			setName(inputValue);
			System.out.println("Имя игрока для маркера `" + getPlayerChar() + "`: " + getName());
			break;
		}
	}

	public void readCoords() {
		System.out.println();
		switch (playerType) {
			case HUMAN:
				readCoordsHuman();
				break;
			case COMPUTER:
				readCoordsComputer();
				break;
			case REMOTE:
				readCoordsRemote();
				break;
		}
	}

	private void readCoordsComputer() {
		setCoord("X", readCoord('X'));
		setCoord("Y", readCoord('Y'));
	}

	private void readCoordsRemote() {
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

	/**
	 * @return the playerChar
	 */
	public Character getPlayerChar() {
		return playerChar;
	}

	/**
	 * @param playerChar the playerChar to set
	 */
	private void setPlayerChar(char newPlayerChar) {
		playerChar = newPlayerChar;
	}
}
