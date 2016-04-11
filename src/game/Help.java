package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import game.Game.STATE;

public class Help extends MouseAdapter {
	private Game game;
	private Handler handler;
	
	public Help(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (game.gameState == STATE.Help) {
			if (Menu.mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				game.gameState = STATE.Menu;
			}
		}
	}
	public void mouseReleased() {}
	public void tick() {
	}
	
	public void render(Graphics g) {
		Font font2 = new Font("arial", 1, 30);
		Font font = new Font("verdana", 1, 50);
		Menu.drawTitle(Game.WIDTH / 2 - 70, 80, "Help", font, g);										//Title Help
		
		Menu.drawButton(Game.WIDTH / 2 - 150, 150, "Back", font2, g);	
	}
}
