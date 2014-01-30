package engine;

import game.Constants;
import world.Block;
import world.Level;
import world.WorldObject;

public class Physics {
	
	private Level level;
	
	private int gravity = Constants.STANDARD_GRAVITY;
	
	public Physics(Level level){
		this.level = level;
	}
	
	
	/*
	 * applies all changes in the world that have to occur based on one unit of
	 * time passing
	 */
	public void performFrame(){

		for(Block block: level.blocks){

			if(block.isMovable && block.hasGravity){
				move(block, 0 , gravity);
			}	

		}

	}
	

	private void move(Block block, int x, int y){
		
		int newX = block.posX + x;
		int newY = block.posY + y;
		
		if(!collidesWithAnything(block, newX, newY)){
			block.posX = newX;
			block.posY = newY;
		}
				
	}
	
	private boolean collidesWithAnything(Block block, int x, int y){
		
		boolean didCollide = false;
		
		for(Block otherBlock: level.blocks){
			
			if(block != otherBlock
					&& (otherBlock.contains(x,y)
					|| otherBlock.contains(x + block.width - 1 ,y)
					|| otherBlock.contains(x,y + block.height - 1)
					|| otherBlock.contains(x + block.width - 1, y + block.height - 1))){
				
				didCollide = true;
				
				
			}
			
		}
		
		
		return didCollide;
	}

}
