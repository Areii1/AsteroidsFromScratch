package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)) {
				game.gameState = STATE.Game;
					Game.createAsteroids(30);
					Game.createPlayer();
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300, 64)) {
				game.gameState = STATE.Help;
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300, 64)) {
				System.exit(0);
			}
			if (game.gameState == STATE.Help) {
				if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
	//				game.gameState = STATE.Game;
					return;
				}
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int objectWidth, int objectHeight) {
		if (mouseX > x && mouseX < x + objectWidth) {
			if (mouseY > y && mouseY < y + objectHeight) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font font = new Font("verdana", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString("Menu", Game.WIDTH / 2 - 70, 80);
			
			g.setFont(font2);
			g.setColor(Color.GREEN);
			g.drawString("Play", Game.WIDTH / 2 - 20, 188);
			g.setColor(Color.RED);
			g.drawRect(Game.WIDTH / 2 - 150, 150, 300, 64);
			
			g.setFont(font2);
			g.setColor(Color.GREEN);
			g.drawString("Help", Game.WIDTH / 2 - 20, 338);
			g.setColor(Color.RED);
			g.drawRect(Game.WIDTH / 2 - 150, 300, 300, 64);
			
			g.setFont(font2);
			g.setColor(Color.GREEN);
			g.drawString("Quit", Game.WIDTH / 2 - 20, 488);
			g.setColor(Color.RED);
			g.drawRect(Game.WIDTH / 2 - 150, 450, 300, 64);
		}
		else if (game.gameState == STATE.Help) {
			Font font = new Font("verdana", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString("Help", Game.WIDTH / 2 - 70, 80);
			
			g.setFont(font2);
			g.setColor(Color.GREEN);
			g.drawString("Back", Game.WIDTH / 2 - 20, 188);
			g.setColor(Color.RED);
			g.drawRect(Game.WIDTH / 2 - 150, 150, 300, 64);
		}
		
	}
}
