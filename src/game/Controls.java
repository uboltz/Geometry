package game;

import java.awt.event.KeyEvent;

import engine.Physics;

/**
 * Decides what the physics engine should do based on which keys are down.
 * 
 * @author Hans Maulwurf
 *
 */
public class Controls {
	
	private Physics physics;
	private KeyboardInput input;
	
	
	public Controls(Physics physics, KeyboardInput input){
		this.physics = physics;
		this.input = input;
	}
	
	public void processInput(){
		
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			physics.moveCommand(+1000, 0);
			
		}
		else if(input.isKeyDown(KeyEvent.VK_LEFT)){
			physics.moveCommand(-1000, 0);
			
		}
		
		if(input.isKeyDown(KeyEvent.VK_UP)){
			physics.moveCommand(0, -1000);
			
		}
		
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			physics.moveCommand(0, +1000);
			
		}
		
	}

}
