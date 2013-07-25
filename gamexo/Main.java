package gamexo;

import gamexo.game.Game;
import gamexo.player.Player;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		game.init();

		for (char marker : game.PLAYERS_CHARS) {
			game.setPlayer(new Player().init(marker));
		}
		boolean result = false;
		while (true) {
			for (Player player : game.getPlayers()) {
				game.makeMove(player);
				int checkWin = game.checkWin(player);
				switch (checkWin) {
					case Game.PLAYER_WIN:
						result = true;
						System.out.println("Выиграл " + player.getName() + "!");
						break;
					case Game.PLAYER_DRAW:
						result = true;
						game.showGameField();
						System.out.println("Ничья!");
						break;
				}
				if (result) {
					return;
				}
			}
		}
	}
}
