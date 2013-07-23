package gamexo.body;

import gamexo.player.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private final Character FIELD_CELL_LEFT = '[';
	private final Character FIELD_CELL_RIGHT = ']';
	private final char FIELD_CLEAR = ' ';
	public final char[] PLAYERS_CHARS = {'X', 'O'};
	private int fieldSize = 3;
	private char[][] gameField;
	public Player[] players = new Player[PLAYERS_CHARS.length];;

	public void init() throws IOException {
		readFieldSize();
		initField();
		showGameField();
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
		for (int y = size; y >= 0; y--) {
			for (int x = 0; x < getFieldSize(); x++) {
				System.out.print(FIELD_CELL_LEFT.toString() + field[y][x] + FIELD_CELL_RIGHT.toString());
			}
			System.out.println();
		}
	}

	public void setCell(int x, int y, char value) {
		gameField[y][x] = value;
	}

	public char getCell(int x, int y) {
		char result = FIELD_CLEAR;
		for (char value: PLAYERS_CHARS) {
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

	private void readFieldSize() throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		int newFieldSize = getFieldSize();

		System.out.print("Enter field size [" + newFieldSize + "]:");
		String inputValue = buffer.readLine();

		if ("".equals(inputValue)) {
			System.out.println("Set default value of game field size.");
		} else {
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

	}

	public void makeMove(Player player) throws IOException {
		System.out.println();
		try {
			int[] coords = player.readCoords();
			if (!checkCoords(coords)) {
				System.err.println("Введены не верные координаты!");
				System.out.println("Повторите ход.");
				makeMove(player);
			} else {
				setCell(coords[0], coords[1], player.PLAYER_CHAR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkCoords(int[] coords) {
		boolean result = false;
		int size = getFieldSize();
		if (coords != null) {
			for (int i = 0; i < coords.length; i++) {
				if (coords[i] > 0 || coords[i] <= size) {
					result = true;
				}
			}
			if (result) {
				char currentCell = getCell(coords[0], coords[1]);
				if (currentCell != FIELD_CLEAR) {
					result = false;
				}
			}
		}
		return result;
	}

	public boolean checkWin(Player player) {
		boolean result = false;
		if (checkColls(player)
			|| checkLines(player)
				|| checkDiags(player)) {
			result = true;
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
}
