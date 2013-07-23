package gamexo;

import java.io.IOException;
import gamexo.body.Game;
import gamexo.player.Player;

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
				int checkWin = game.checkWin(player);
				if (checkWin == Game.PLAYER_WIN) {
					result = true;
					System.out.println("Выиграл " + player.name + "!");
					break;
				}
				if (checkWin == Game.PLAYER_DRAW) {
					result = true;
					System.out.println("Ничья!");
					break;
				}
			}
			if (result) {
				break;
			}
		}
	}
}
