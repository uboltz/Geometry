package world;

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
	
	public List<PhysicalObject> objects = new ArrayList<PhysicalObject>();
	
	
	public void addBlock(int x, int y){
		
		System.out.println("adding new Block at " + x + " " + y);
		objects.add(new Block(x,y));
	}
	

}
