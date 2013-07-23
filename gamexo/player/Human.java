package gamexo.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human {

	private String name = "Anonimus";

	public void init(String name) {
		if (name != "") {
			this.setName(name);
		}
	}

	public int[] readCoords() throws IOException {
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
		return name;
	}

	/**
	 * Установка имени игрока
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
