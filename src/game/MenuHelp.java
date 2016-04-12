package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import game.Game.STATE;

public class MenuHelp extends MouseAdapter {
	private Game game;
	private Handler handler;
	public static boolean backClicked = false;
	
	public MenuHelp(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.Help) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				backClicked = true;
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void tick() {
	}
	
	public static boolean mouseOver(int mouseX, int mouseY, int x, int y, int objectWidth, int objectHeight) {
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
	
	public void render(Graphics g) {
		if (Game.gameState == STATE.Help) {
			Font font2 = new Font("arial", 1, 30);
			Font font = new Font("verdana", 1, 50);
			MainMenu.drawTitle(Game.WIDTH / 2 - 70, 80, "Help", font, g);										//Title Help
			
			MainMenu.drawButton(Game.WIDTH / 2 - 150, 500, "Back", font2, g);
			g.drawString("Instructions", 360, 150);
			
			Font font3 = new Font("arial", 1, 20);
			g.setFont(font3);
			
			g.drawString("To move use WASD", 350, 200);
			g.drawString("To exit the game use ESC", 320, 230);
			g.drawString("To shoot use SPACE", 345, 260);
			g.drawString("To use hacks press ENTER", 315, 290);
		}
		
	}
}
