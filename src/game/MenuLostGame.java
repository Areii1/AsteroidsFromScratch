package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import game.Game.STATE;

public class MenuLostGame extends MouseAdapter {
	private Game game;
	private Handler handler;
	public static boolean lostGameAgainClicked = false;
	
	public MenuLostGame(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.LostGame) {
			if (MainMenu.mouseOver(mouseX, mouseY, 310, 260, 300, 64)) {
				System.out.println("Again Clicked");
				lostGameAgainClicked = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	public void tick() {
	}
	
	public void render(Graphics g) {
		if (Game.gameState == STATE.LostGame) {
			Font font2 = new Font("arial", 1, 30);
			Font font = new Font("verdana", 1, 50);
			
			MainMenu.drawTitle(200, 160, Game.deathCount + " Deaths: You lost with a score of: " 									//Lost game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			MainMenu.drawButton(310, 260, "Play Again", font2, g);	
		}
		
	}
}
