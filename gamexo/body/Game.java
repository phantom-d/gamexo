package gamexo.body;

import gamexo.player.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private final Character FIELD_CELL_LEFT = '[';
	private final Character FIELD_CELL_RIGHT = ']';
	public final char[] PLAYER_CHAR = {'X', 'O'};
	private int fieldSize = 3;
	private String[][] gameField;

	public void init() throws IOException {
		this.readFieldSize();
		this.initField();
		this.showGameField();
	}

	/**
	 * Initialization game field
	 */
	private void initField() {
		this.setGameField();
		for (int y = 0; y < this.getFieldSize(); y++) {
			for (int x = 0; x < this.getFieldSize(); x++) {
				this.setCell(x, y, ' ');
			}
		}
	}

	public void showGameField() {
		String[][] field = this.getGameField();
		for (int y = 0; y < this.getFieldSize(); y++) {
			for (int x = 0; x < this.getFieldSize(); x++) {
				System.out.print(field[y][x]);
			}
			System.out.println();
		}
	}

	public void setCell(int x, int y, char value) {
		this.gameField[y][x] = this.FIELD_CELL_LEFT.toString() + value + this.FIELD_CELL_RIGHT.toString();
	}

	/**
	 * Get game field size
	 *
	 * @return the fieldSize
	 */
	private int getFieldSize() {
		return this.fieldSize;
	}

	/**
	 * Set game field size
	 *
	 * @param fieldSize the fieldSize to set
	 */
	private void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	private String[][] getGameField() {
		return this.gameField;
	}

	private void setGameField() {
		int size = this.getFieldSize();
		this.gameField = new String[size][size];
	}

	private void readFieldSize() throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		int newFieldSize = this.getFieldSize();

		System.out.print("Enter field size [" + newFieldSize + "]:");
		String inputValue = buffer.readLine();

		if ("".equals(inputValue)) {
			System.out.println("Set default value of game field size.");
		} else {
			try {
				newFieldSize = Integer.parseInt(inputValue);
				if (newFieldSize > 0) {
					this.setFieldSize(newFieldSize);
				} else {
					this.readFieldSize();
				}
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
				this.readFieldSize();
			}
		}

	}

	public void makeMove(Player player) throws IOException {
		try {
			int[] coords = player.readCoords();
			if (!this.checkCoords(coords)) {
				System.err.println("Введены не верные координаты!");
				System.out.println("Повторите ход.");
				this.makeMove(player);
			} else {
				setCell(coords[0], coords[1], this.PLAYER_CHAR[player.PLAYER_NUM]);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkCoords(int[] coords) {
		boolean result = true;
		int size = this.getFieldSize();
		if (coords != null) {
			for (int i = 0; i < coords.length; i++) {
				if (coords[i] < 0 || coords[i] > size) {
					result = false;
				}
			}
		}
		return result;
	}

	public boolean checkWin(Player player) {
		boolean result = false;
		if (this.checkColls(player)
			|| this.checkLines(player)
				|| this.checkDiags(player)) {
			result = true;
		}

		return result;
	}

	private boolean checkColls(Player player) {
		boolean result = false;
		int size = this.getFieldSize();
		String[][] field = this.getGameField();
		int contain;

		for (int x = 0; x < size; x++) {
			contain = 0;
			for (int y = 0; y < size; y++) {
				String cell = this.FIELD_CELL_LEFT.toString() + this.PLAYER_CHAR[player.PLAYER_NUM] + this.FIELD_CELL_RIGHT.toString();
				if (field[y][x] == cell) {
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
		int size = this.getFieldSize();
		String[][] field = this.getGameField();
		int contain = 0;

		for (int y = 0; y < size; y++) {
			contain = 0;
			for (int x = 0; x < size; x++) {
				String cell = this.FIELD_CELL_LEFT.toString() + this.PLAYER_CHAR[player.PLAYER_NUM] + this.FIELD_CELL_RIGHT.toString();
				if (field[y][x] == cell) {
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
		int size = this.getFieldSize();
		String[][] field = this.getGameField();
		int contain = 0;

		for (int i = 0; i < size; i++) {
			String cell = this.FIELD_CELL_LEFT.toString() + this.PLAYER_CHAR[player.PLAYER_NUM] + this.FIELD_CELL_RIGHT.toString();
			if (field[i][i] == cell) {
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
			String cell = this.FIELD_CELL_LEFT.toString() + this.PLAYER_CHAR[player.PLAYER_NUM] + this.FIELD_CELL_RIGHT.toString();
			if (field[i][j] == cell) {
				contain++;
			}
		}

		if (size == contain) {
			result = true;
		}
		return result;
	}
}
