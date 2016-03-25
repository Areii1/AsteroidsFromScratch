package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
//	List of all gameObjects
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject gameObject = gameObjects.get(i);
			gameObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject tempObject = gameObjects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void removeObject(GameObject object) {
		gameObjects.remove(object);
	}
	
	public void clearEnemies() {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject gameObject = gameObjects.get(i);
			gameObjects.remove(gameObject);
		}
	}
}
