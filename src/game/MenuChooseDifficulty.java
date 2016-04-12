package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;

public class MenuChooseDifficulty extends MouseAdapter {
	private Game game;
	private Handler handler;
	public static boolean easyClicked = false;
	public static boolean mediumClicked = false;
	public static boolean hardClicked = false;
	
	public MenuChooseDifficulty(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)) {
				easyClicked = true;
				System.out.println("easy");
				Game.startGameplay(10);
			}
		}
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300, 64)) {
					mediumClicked = true;
					Game.startGameplay(20);
				}
		}
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300, 64)) {
					hardClicked = true;
					Game.startGameplay(30);
				}
		}
		
	}
	public void mouseReleased(MouseEvent e) {}
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
		if (Game.gameState == STATE.ChooseDifficulty) {
			Font font2 = new Font("arial", 1, 30);
			Font font = new Font("verdana", 1, 50);
			MainMenu.drawTitle(Game.WIDTH / 2 - 170, 80, "Choose difficulty", font, g);						
			
			MainMenu.drawButton(Game.WIDTH / 2 - 150, 150, "Easy", font2, g);
			MainMenu.drawButton(Game.WIDTH / 2 - 150, 300, "Medium", font2, g);
			MainMenu.drawButton(Game.WIDTH / 2 - 150, 450, "Hard", font2, g);
		}
		
	}
}
