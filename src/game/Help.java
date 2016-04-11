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
			if (Menu.mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 500, 300, 64)); {
				game.gameState = STATE.Menu;
				Menu.helpClicked = false;

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
		
		Menu.drawButton(Game.WIDTH / 2 - 150, 500, "Back", font2, g);
		g.drawString("Instructions",360,150);
		
		Font font3 = new Font("arial", 1, 20);
		g.setFont(font3);
		
		g.drawString("To move use WASD",350,200);
		g.drawString("To exit the game use ESC",320, 230);
		g.drawString("To shoot use SPACE",345, 260);
		g.drawString("To use hacks press ENTER",315, 290);
	}
}
