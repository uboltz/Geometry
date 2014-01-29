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
	
	
	public void changeZoom(int factor){
		
		//find screen center
		int centerX = posX + (getWidthInWorld() / 2);
		int centerY = posY + (getHeightInWorld() / 2);
		
		//apply changes
		zoom = zoom * factor;
		zoom = zoom / 100;

		//this hack fights rounding errors and out of bounds situations
		if(zoom < 1){		
			zoom = 1;
		}
		if(factor > 100){
			zoom++;
		}
		
		//adjust screen so that its center stays the same
		posX = centerX - (getWidthInWorld() / 2);
		posY = centerY - (getHeightInWorld() / 2);
		ensurePositionWithinBounds();
		
		
			
	}
	
	public void moveX(int x){
		this.posX += x * zoom;
		ensurePositionWithinBounds();
	}
	
	public void moveY(int y){
		this.posY += y * zoom;
		ensurePositionWithinBounds();
	}
	
	private void ensurePositionWithinBounds(){
		if(posX < 0) {
			posX = 0;
		}
		if(posX >= Constants.WORLD_WIDTH) {
			posX = Constants.WORLD_WIDTH;
		}
		if(posY < 0) {
			posY = 0;
		}
		if(posY >= Constants.WORLD_HEIGHT) {
			posY = Constants.WORLD_HEIGHT;
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
