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
	public List<Block> blocks = new ArrayList<Block>();
	
	
	public void addBlock(Block block){
		blocks.add(block);
	}
	
	
	
	/*
	 * delete all blocks that contain the specified point in the world
	 */
	public void deleteBlocksAt(int x, int y){
			
		List<Block> toBeRemoved = new ArrayList<Block>();

		for(Block block: blocks){

			Rectangle r = new Rectangle(
					block.posX,
					block.posY,
					block.width,
					block.height);

			if(r.contains(x,y)){
				toBeRemoved.add(block);
			}
		}

		for(WorldObject object: toBeRemoved){
			blocks.remove(object);
		}
	}
	
}
