package engine;

import java.awt.Rectangle;

import game.Constants;

/**
 * This represents the state of the screen relative to the game world.
 * It contains information about location, zoom factor and such.
 * 
 * @author Hans Maulwurf
 *
 */
public class Screen {
	
	//position in game world
	public int posX = 0, posY = 0;
	
	//TODO add comment
	public int zoom = 1000;
	
	//screen size on canvas
	private int displayedWidth = Constants.CANVAS_WIDTH;
	private int displayedHeight = Constants.CANVAS_HEIGHT;
	
	
	public void changeZoom(int x){
		
		zoom = zoom + x;
		if(zoom < 1){
			zoom = 1;
		}
			
	}
	
	
	public int getWidthInWorld(){
		return displayedWidth * zoom;
	}
	
	public int getHeightInWorld(){
		return displayedHeight * zoom;
	}
	
	public boolean contains(int x, int y){
		
		Rectangle r = new Rectangle(posX, posY, getWidthInWorld(), getHeightInWorld());
		
		return r.contains(x, y);
		
	}

}
