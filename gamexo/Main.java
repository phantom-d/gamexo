package gamexo;

import gamexo.body.Game;
import gamexo.player.Player;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.init();

		for (int i = 0; i < game.PLAYERS_CHARS.length; i++) {
			game.players[i] = new Player(game.PLAYERS_CHARS[i]);
		}
		boolean result = false;
		while (true) {
			for (Player player: game.players) {
				game.makeMove(player);
				game.showGameField();
				if (game.checkWin(player)) {
					result = true;
					System.out.println("Выиграл " + player.name);
					break;
				}
			}
			if (result) {
				break;
			}
		}
	}
}
