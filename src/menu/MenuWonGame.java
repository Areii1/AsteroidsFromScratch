package menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game;
import game.Handler;
import game.Game.STATE;

public class MenuWonGame extends MouseAdapter {
	private Game game;
	private Handler handler;
	public static boolean wonGameAgainClicked = false;
	
	public MenuWonGame(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.WonGame) {
			if (MainMenu.mouseOver(mouseX, mouseY, 310, 260, 300, 64)) {
				wonGameAgainClicked = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	public void tick() {
	}
	
	public void render(Graphics g) {
		Font font = new Font("verdana", 1, 50);
		Font font2 = new Font("arial", 1, 30);
		
		if (Game.gameState == STATE.WonGame) {
			MainMenu.drawTitle(100, 200, Game.killCount + " Kills: You won the game with a score of: " 							//Won game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			g.drawRect(Game.WIDTH / 2 - 150, 250, 300, 64);
			g.drawString("Play Again?", 370, 290);
		}
		
	}
}
