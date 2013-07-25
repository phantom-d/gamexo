package gamexo.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import gamexo.player.Player;

public class Game {

	/**
	 * Class Constants
	 */
	public static final int PLAYER_WIN = 1;
	public static final int PLAYER_DRAW = 0;
	public static final int NEXT_TURN = -1;
	/**
	 * Local Constants
	 */
	private final Character FIELD_CELL_LEFT = '[';
	private final Character FIELD_CELL_RIGHT = ']';
	private final char FIELD_CLEAR = ' ';
	public final char[] PLAYERS_CHARS = {'X', 'O'};

	/**
	 * Variables
	 */
	private int fieldSize = 3;
	private char[][] gameField;
	private List<Player> players = new ArrayList<Player>(PLAYERS_CHARS.length);

	;

	public void init() {
		readFieldSize();
		initField();
	}

	/**
	 * Initialization game field
	 */
	private void initField() {
		setGameField();
		for (int y = 0; y < getFieldSize(); y++) {
			for (int x = 0; x < getFieldSize(); x++) {
				setCell(x, y, FIELD_CLEAR);
			}
		}
	}

	public void showGameField() {
		char[][] field = getGameField();
		int size = getFieldSize();
		size--;
		System.out.println();
		for (int y = size; y >= 0; y--) {
			for (int x = 0; x < getFieldSize(); x++) {
				System.out.print(FIELD_CELL_LEFT.toString() + field[y][x] + FIELD_CELL_RIGHT.toString());
			}
			System.out.println();
		}
		System.out.println();
	}

	public void setCell(int x, int y, char value) {
		gameField[y][x] = value;
	}

	public char getCell(int x, int y) {
		char result = FIELD_CLEAR;
		for (char value : PLAYERS_CHARS) {
			if (gameField[y][x] == value) {
				result = value;
			}
		}
		return result;
	}

	/**
	 * Get game field size
	 *
	 * @return the fieldSize
	 */
	private int getFieldSize() {
		return fieldSize;
	}

	/**
	 * Set game field size
	 *
	 * @param fieldSize the fieldSize to set
	 */
	private void setFieldSize(int newFieldSize) {
		fieldSize = newFieldSize;
	}

	private char[][] getGameField() {
		return gameField;
	}

	private void setGameField() {
		int size = getFieldSize();
		gameField = new char[size][size];
	}

	private void readFieldSize() {
		Scanner scanner = new Scanner(System.in);
		int newFieldSize = getFieldSize();

		System.out.print("Введите размер игрового поля [" + newFieldSize + "]: ");
		String inputValue = scanner.nextLine();

		if (!inputValue.isEmpty()) {
			try {
				newFieldSize = Integer.parseInt(inputValue);
				if (newFieldSize > 0) {
					setFieldSize(newFieldSize);
				} else {
					readFieldSize();
				}
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				readFieldSize();
			}
		}
		System.out.println("Размер игрового поля: " + newFieldSize + "x" + newFieldSize + ".");
		System.out.println();
	}

	public void makeMove(Player player) {
		showGameField();
		try {
			player.readCoords();
			Map<String, Integer> coords = player.getCoords();
			if (!checkCoords(coords)) {
				System.err.println("Повторите ход.");
				System.err.println();
				Thread.currentThread().sleep(100);
				makeMove(player);
			} else {
				setCell(coords.get("X"), coords.get("Y"), player.PLAYER_CHAR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkCoords(Map<String, Integer> coords) {
		boolean result = true;
		int size = getFieldSize();
		if (coords != null) {
			if (coords.get("X") < 0 || coords.get("X") >= size) {
				System.err.println("Не верное значение координаты X!");
				result = false;
			}
			if (coords.get("Y") < 0 || coords.get("Y") >= size) {
				System.err.println("Не верное значение координаты Y!");
				result = false;
			}
			if (result) {
				char currentCell = getCell(coords.get("X"), coords.get("Y"));
				if (currentCell != FIELD_CLEAR) {
					System.err.println("Ячейка по указанным координатам уже занята!");
					result = false;
				}
			}
		}
		return result;
	}

	public int checkWin(Player player) {
		int result = NEXT_TURN;
		if (checkColls(player)
			   || checkLines(player)
			   || checkDiags(player)) {
			result = PLAYER_WIN;
		} else {
			if (!checkClearFields()) {
				result = PLAYER_DRAW;
			}
		}
		return result;
	}

	private boolean checkClearFields() {
		boolean result = false;
		char[][] field = getGameField();
		for (char[] row: field) {
			for (char col: row) {
				if (col == FIELD_CLEAR) {
					result = true;
				}
			}
		}

		return result;
	}
	private boolean checkColls(Player player) {
		boolean result = false;
		int size = getFieldSize();
		char[][] field = getGameField();
		int contain;

		for (int x = 0; x < size; x++) {
			contain = 0;
			for (int y = 0; y < size; y++) {
				if (field[y][x] == player.PLAYER_CHAR) {
					contain++;
				}
			}

			if (size == contain) {
				result = true;
			}
		}
		return result;
	}

	private boolean checkLines(Player player) {
		boolean result = false;
		int size = getFieldSize();
		char[][] field = getGameField();
		int contain;

		for (int y = 0; y < size; y++) {
			contain = 0;
			for (int x = 0; x < size; x++) {
				if (field[y][x] == player.PLAYER_CHAR) {
					contain++;
				}
			}

			if (size == contain) {
				result = true;
			}
		}
		return result;
	}

	private boolean checkDiags(Player player) {
		boolean result = false;
		int size = getFieldSize();
		char[][] field = getGameField();
		int contain = 0;

		for (int i = 0; i < size; i++) {
			if (field[i][i] == player.PLAYER_CHAR) {
				contain++;
			}
		}

		if (size == contain) {
			result = true;
		}

		contain = 0;
		int j = size;
		for (int i = 0; i < size; i++) {
			j--;
			if (field[i][j] == player.PLAYER_CHAR) {
				contain++;
			}
		}

		if (size == contain) {
			result = true;
		}
		return result;
	}

	/**
	 * @return the players
	 */
	public void setPlayer(Player player) {
		players.add(player);
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}
}
