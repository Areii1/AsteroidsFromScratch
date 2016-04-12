package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.STATE;

public class MainMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	public static boolean helpClicked = false;
	public static boolean playClicked = false;
	
	public MainMenu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	/*When cursor is top of some box that says "Play","Help" or "Exit" and you click it and something happens. 
	 * */
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (Game.gameState == STATE.Menu) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)) {
				playClicked = true;
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 300, 300, 64)) {
				helpClicked = true;
			}
			else if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 450, 300, 64)) {
				System.exit(0);
			}
		}
		if (Game.gameState == STATE.LostGame) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				resetGame();
			}
		}
		if (Game.gameState == STATE.WonGame) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH / 2 - 150, 150, 300, 64)); {
				resetGame();
			}
		}
	}
	
	// When mouse is not pressed, doesn't do anything special
	public void mouseReleased(MouseEvent e) {
	}
	
	// checks position of the cursor.
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
		else if (Game.gameState == STATE.LostGame) {
			drawTitle(200, 160, Game.deathCount + " Deaths: You lost with a score of: " 									//Lost game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			drawButton(310, 260, "Play Again", font2, g);														//More
		}
		else if (Game.gameState == STATE.WonGame) {	

			drawTitle(100, 200, Game.killCount + " Kills: You won the game with a score of: " 							//Won game info
			+ (Game.gameScore - Game.deathCount * 400 + Game.killCount * 50), font2, g);
			
			g.drawRect(Game.WIDTH / 2 - 150, 250, 300, 64);
			g.drawString("Play Again?", 370, 290);								//More
		}
	}
	
	private void resetGame() {
		Game.gameState = STATE.Play;
		Game.deathCount = 0;
		Game.killCount = 0;
		Game.gameScore = 0;
		Game.createAsteroids(10);
		Game.createPlayer();
	}
	
	public static void drawButton(int x, int y, String text, Font font, Graphics g) {
		if (Game.gameState == STATE.LostGame) {
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString(text, 390, 300);
			g.setColor(Color.RED);
			g.drawRect(x, y, 300, 64);
		}
		else {
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString(text, x + 130, y + 38);
			g.setColor(Color.RED);
			g.drawRect(x, y, 300, 64);
		}
	}
	
	public static void drawTitle(int x, int y, String text, Font font, Graphics g) {
		if (Game.gameState == STATE.LostGame) {
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString(text, x, y);
		}
		else {
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString(text, x, y);
		}
	}
}
