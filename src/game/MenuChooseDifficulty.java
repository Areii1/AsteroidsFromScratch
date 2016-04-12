package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;

public class MenuChooseDifficulty extends MouseAdapter {
	private Game game;
	private Handler handler;
	
	public MenuChooseDifficulty(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (MainMenu.mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)) {
				Game.asteroidsAmount = 10;
				Game.startGameplay(Game.asteroidsAmount);
			}
		}
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (MainMenu.mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300, 64)) {
				Game.asteroidsAmount = 20;
				Game.startGameplay(Game.asteroidsAmount);
			}
		}
		if (Game.gameState == STATE.ChooseDifficulty) {
			if (MainMenu.mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300, 64)) {
				Game.asteroidsAmount = 30;
				Game.startGameplay(Game.asteroidsAmount);
				
			}
		}
		
	}
	public void mouseReleased(MouseEvent e) {}
	
	public void tick() {
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
