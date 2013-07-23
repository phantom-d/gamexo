package gamexo.body;

public class Game {

	private final Character FIELD_CELL_LEFT = '[';
	private final Character FIELD_CELL_RIGHT = ']';
	private final char[] PLAYER_CHAR = {'X', 'O'};

	private int fieldSize = 3;
	private String[][] gameField;

	public void init() {
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

	private void showGameField() {
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
	 * @return the fieldSize
	 */
	public int getFieldSize() {
		return fieldSize;
	}

	/**
	 * Set game field size
	 * @param fieldSize the fieldSize to set
	 */
	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	/**
	 * @return the gameField
	 */
	private String[][] getGameField() {
		return gameField;
	}

	/**
	 * @return the gameField
	 */
	private void setGameField() {
		int size = this.getFieldSize();
		this.gameField = new String[size][size];
	}
}
