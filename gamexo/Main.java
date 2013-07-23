package gamexo;

import gamexo.body.Game;
import gamexo.player.Player;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.init();

		Player[] players = {
			new Player(0),
			new Player(1)
		};

		while (true) {
			for (int i = 0; i < players.length; i++) {
				game.makeMove(players[i]);
				game.showGameField();
				if (game.checkWin(players[i])) {
					System.out.println("Выиграл игрок с символом '" + game.PLAYER_CHAR[i] + "'!");
					break;
				}
			}
		}
	}
}
