package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;


/**
 * Keeps track of which keys are down.
 * 
 * @author Hans Maulwurf
 *
 */
public class KeyboardInput extends KeyAdapter{
	
	//contains all keys that are currently down
	private Set<Integer> keysDown = new HashSet<Integer>();
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		keysDown.add(e.getKeyCode());
		
		System.out.println(e.getKeyChar());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown.remove(e.getKeyCode());
		
	}

	
	public boolean isKeyDown(int key){
		return keysDown.contains(key);
	}

	
	
	
}
