package gamexo;

import gamexo.body.Game;
import gamexo.player.Human;
import gamexo.player.Player;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.init();

		Object[] players = {
			new Player(0),
			new Player(0),
		};

		while (true) {
			for (int i = 0; i < players.length; i++) {

			}
		}
	}
}
