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
	/*When cursor is top of some box that says "Play","Help" or "Exit" and you click it and something happens. 
	 * */
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)) {
				game.gameState = STATE.Play;
				Game.createAsteroids(30);
				Game.createPlayer();
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300, 64)) {
				game.gameState = STATE.Help;
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300, 64)) {
				System.exit(0);
			}
		}
		//NOT WORKING PROPERLY
		if (game.gameState == STATE.Help) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				game.gameState = STATE.Menu;
			}
		}
		if (game.gameState == STATE.LostGame) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				resetGame();
			}
		}
		if (game.gameState == STATE.WonGame) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				resetGame();
			}
		}
	}
	
	// When mouse is not pressed, doesn't do anything special
	public void mouseReleased(MouseEvent e) {
	}
	
	// checks position of the cursor.
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
		
		Font font = new Font("verdana", 1, 50);
		Font font2 = new Font("arial", 1, 30);
		if (game.gameState == STATE.Menu) {
			drawTitle(Game.WIDTH / 2 - 140, 80, "Asteroids", font, g);									//Title Asteroids
			
			drawButton(Game.WIDTH / 2 - 150, 150, "Play", font2, g);									//Play
			drawButton(Game.WIDTH / 2 - 150, 300, "Help", font2, g); 									//Help
			drawButton(Game.WIDTH / 2 - 150, 450, "Quit", font2, g);									//Quit
		}
		else if (game.gameState == STATE.Help) {
			drawTitle(Game.WIDTH / 2 - 70, 80, "Help", font, g);										//Title Help
			
			drawButton(Game.WIDTH / 2 - 150, 150, "Back", font2, g);									//Back
		}
		else if (game.gameState == STATE.LostGame) {
			drawTitle(200, 160, "5 deaths: You lost with a score of: " 									//Lost game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			drawButton(310, 260, "More", font2, g);														//More
		}
		else if (game.gameState == STATE.WonGame) {
			drawTitle(100, 80, "90 kills: You won the game with a score of: " 							//Won game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			drawButton(Game.WIDTH / 2 - 150, 150, "More", font2, g);									//More
		}
	}
	
	private void resetGame() {
		game.gameState = STATE.Play;
		Game.deathCount = 0;
		Game.killCount = 0;
		Game.gameScore = 0;
		Game.createAsteroids(30);
		Game.createPlayer();
	}
	
	private void drawButton(int x, int y, String text, Font font, Graphics g) {
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString(text, x + 130, y + 38);
		g.setColor(Color.RED);
		g.drawRect(x, y, 300, 64);
	}
	
	private void drawTitle(int x, int y, String text, Font font, Graphics g) {
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString(text, x, y);
	}
}
