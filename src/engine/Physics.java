package engine;

import game.Constants;
import world.Level;
import world.PhysicalObject;

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
		
		for(PhysicalObject object: level.objects){
			
			if(object.hasPhysics){
				
				if(object.isMovable && object.hasGravity){
					move(object, 0 , gravity);
				}
				
			}
			
		}
		
	}
	
	//TODO add collisions
	private void move(PhysicalObject object, int x, int y){
		object.posX += x;
		object.posY += y;
	}

}
