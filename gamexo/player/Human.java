package gamexo.player;

public class Human {

	private String name = "Anonimus";

	public void init(String name) {
		if (name != "") {
			this.setName(name);
		}
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
