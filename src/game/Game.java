package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import menu.MainMenu;
import menu.MenuChooseDifficulty;
import menu.MenuHelp;
import menu.MenuLostGame;
import menu.MenuWonGame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -4584388369897487885L;
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	public static int gameScore;
	public static int killCount = 0;
	public static int deathCount = 0;
	public static int asteroidsAmount = 1;

	private Thread thread;
	private boolean running = false;

	private static Handler handler;
	
	public enum STATE {
		Menu,
		Help,
		Play,
		ChooseDifficulty,
		LostGame,
		WonGame;
	}
	public static STATE gameState = STATE.Menu;
	
	private MainMenu menu;
	private MenuHelp help;
	private MenuChooseDifficulty chooseDifficulty;
	private MenuLostGame lostGame;
	private MenuWonGame wonGame;
	
	public Game() {
		handler = new Handler();
		menu = new MainMenu(this, handler);
		help = new MenuHelp(this, handler);
		chooseDifficulty = new MenuChooseDifficulty(this, handler);
		lostGame = new MenuLostGame(this, handler);
		wonGame = new MenuWonGame(this, handler);
		
		addKeyListener(new KeyInput(handler));
		addMouseListener(new MainMenu(this, handler));
		addMouseListener(new MenuHelp(this, handler));
		addMouseListener(new MenuChooseDifficulty(this, handler));
		addMouseListener(new MenuLostGame(this, handler));
		addMouseListener(new MenuWonGame(this, handler));
		
		new Window(WIDTH, HEIGHT, "AsteroidsFromScratch", this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	Copied game loop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		
		if (gameState == STATE.Play) {
			gameScore++;
		}
		else if (gameState == STATE.Menu) {
			menu.tick();
			if (MainMenu.playClicked) {
				gameState = STATE.ChooseDifficulty;
				MainMenu.playClicked = false;
			}
			else if (MainMenu.helpClicked) {
				gameState = STATE.Help;
				MainMenu.helpClicked = false;
			}
		}
		else if (gameState == STATE.ChooseDifficulty) {
			chooseDifficulty.tick();
		}
		else if (gameState == STATE.Help) {
			help.tick();
			if (gameState == STATE.Help) {
				if (MenuHelp.backClicked) {
					gameState = STATE.Menu;
					MenuHelp.backClicked = false;
				}
			}
		}
		else if (gameState == STATE.WonGame) {
			wonGame.tick();
			if (MenuWonGame.wonGameAgainClicked) {
				gameState = STATE.Play;
				MainMenu.resetGame();
				MenuWonGame.wonGameAgainClicked = false;
			}
		}
		else if (gameState == STATE.LostGame) {
			lostGame.tick();
			if (MenuLostGame.lostGameAgainClicked) {
				gameState = STATE.Play;
				MainMenu.resetGame();
				MenuLostGame.lostGameAgainClicked = false;
			}
		}
		
		if (deathCount >= 5) {
			gameState = STATE.LostGame;
			handler.clearEnemies();
		}
		
		if (killCount >= asteroidsAmount * 3) {
			gameState = STATE.WonGame;
			handler.clearEnemies();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if (gameState == STATE.Play) {
			String deaths = "Deaths: " + deathCount;
			String kills = "Kills: " + killCount;
			String scoreStr = "Score: " + (gameScore + (killCount * 50) - (deathCount * 500));
			String difficulty = "Difficulty: " + determineDifficulty();
			
			g.setColor(Color.GREEN);
			g.drawString(deaths, 10, 30);
			g.drawString(kills, 10, 50);
			g.drawString(scoreStr, 10, 70);
			g.drawString(difficulty, 10, 90);
		}
		else if (gameState == STATE.Help) {
			help.render(g);
		}
		else if (gameState == STATE.ChooseDifficulty) {
			chooseDifficulty.render(g);
		}
		else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		else if (gameState == STATE.LostGame) {
			lostGame.render(g);
		}
		else if (gameState == STATE.WonGame) {
			wonGame.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] arguments) {
		new Game();
	}
	
	// creates asteroid in game
	public static void createAsteroids(int amount) {
		Random r = new Random();
		for (int i = 0; i < amount; i++) {
			handler.addObject(new Asteroid(new Point(r.nextInt(WIDTH), r.nextInt(HEIGHT)), ID.Asteroid, 30, 30));
		}
	}
	// creates player in game
	public static void createPlayer() {
		handler.addObject(new Player(new Point(WIDTH / 2, HEIGHT / 2), ID.Player, handler, 20, 20));
	}
	
	public static void startGameplay(int asteroidsAmount) {
		gameState = STATE.Play;
		createAsteroids(asteroidsAmount);
		createPlayer();
	}
	
	private String determineDifficulty() {
		String difficulty = null;
		if (asteroidsAmount == 10) difficulty = "Easy";
		if (asteroidsAmount == 20) difficulty = "Medium";
		if (asteroidsAmount == 30) difficulty = "Hard";
		
		return difficulty;
	}
}
