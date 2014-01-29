package world;

import engine.Screen;
import game.Constants;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one level of the game. Holds a list of all objects in that level
 * as well as a bunch of environment variables that describe the level's properties.
 * 
 * Also contains whatever else will be necessary in terms of level specific
 * information.
 * 
 * @author Hans Maulwurf
 *
 */
public class Level {
	
	//everything in the level
	public List<PhysicalObject> objects = new ArrayList<PhysicalObject>();
	
	
	/*
	 * create a block from coordinates in the world
	 */
	public void addBlock(int x, int y, int width, int height){
		
		System.out.println("adding new Block at " + x + " " + y);
		objects.add(new Block(x,y, width, height));
	}
	
	/*
	 * create a block from a screen and coordinate on that screen
	 */
	public void addBlock(Screen screen, int x, int y, int width, int height){
		x = screen.screenToWorldX(x);
		y = screen.screenToWorldY(y);
		width = width * screen.zoom;
		height = height * screen.zoom;
		addBlock(x,y, screen.grid.cellSize, screen.grid.cellSize); 
	}
	
	/*
	 * create a block that fills exactly one cell in the screen's grid
	 */
	public void createStandardBlock(Screen screen, int x, int y){
		addBlock(
				screen.screenToCellX(x), 
				screen.screenToCellY(y),
				screen.grid.cellSize,
				screen.grid.cellSize);
		
	}
	
	/*
	 * delete all blocks that contain the specified point in the world
	 */
	public void deleteBlocksAt(int x, int y){
			
		List<PhysicalObject> toBeRemoved = new ArrayList<PhysicalObject>();
		
		for(PhysicalObject object: objects){
			
			Rectangle r = new Rectangle(
					object.posX,
					object.posY,
					object.width,
					object.height);

			if(r.contains(x,y)){
				toBeRemoved.add(object);
			}
		}
		
		for(PhysicalObject object: toBeRemoved){
			objects.remove(object);
		}
	}
	
	/*
	 * delete all blocks that contain the specified point on the given screen
	 */
	public void deleteBlocksAt(Screen screen, int x, int y){
		
		x = screen.screenToWorldX(x);
		y = screen.screenToWorldY(y);
		deleteBlocksAt(x,y);
	}

}
