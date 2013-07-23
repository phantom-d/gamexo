package gamexo.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
	private final int HUMAN = 0;
	private final int COMPUTER = 1;

	private String name = "Anonimus";
	private int playerType = this.HUMAN;

	public void player(int type) throws IOException {
		this.setPlayerType(type);
	}

	public int[] readCoords(int type) throws IOException {
		int[] coords = new int[2];

		switch (this.getPlayerType()) {
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
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Установка имени игрока
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the playerType
	 */
	private int getPlayerType() {
		return this.playerType;
	}

	/**
	 * @param playerType the playerType to set
	 */
	private void setPlayerType(int playerType) {
		this.playerType = playerType;
	}
}
