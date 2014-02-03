package engine;

import java.awt.Graphics;
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
	
	//world pixels per screen pixel
	public int zoom = Constants.STANDARD_ZOOM;
	
	//screen size on canvas
	private int displayedWidth = Constants.CANVAS_WIDTH;
	private int displayedHeight = Constants.CANVAS_HEIGHT;
	
	public Grid grid = new Grid(Constants.GRID_CELL_SIZE);	
	
	public Selector selection = new Selector();
	
	
	public void drawOverlay(Graphics g){
		if(selection.isVisible){
			Rectangle r = selection.getRectangle();
			g.drawRect(r.x, r.y, r.width, r.height);
		}
	}
	
	
	/*
	 * Changes the zoom by a factor in percent
	 */
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
	
	/*
	 * moves the screen horizontally by the specified number of pixels on screen
	 */
	public void moveX(int x){
		this.posX += x * zoom;
		ensurePositionWithinBounds();
	}
	
	/*
	 * moves the screen vertically by the specified number of pixels on screen
	 */
	public void moveY(int y){
		this.posY += y * zoom;
		ensurePositionWithinBounds();
	}
	
	/*
	 * If the current screen position is not inside the world, it is set
	 * to be on the worlds border.
	 */
	private void ensurePositionWithinBounds(){
		
		if(posX <= -Constants.WORLD_WIDTH) {
			posX = -Constants.WORLD_WIDTH;
		}
		if(posX >= Constants.WORLD_WIDTH) {
			posX = Constants.WORLD_WIDTH;
		}
		if(posY <= -Constants.WORLD_HEIGHT) {
			posY = -Constants.WORLD_HEIGHT;
		}
		if(posY >= Constants.WORLD_HEIGHT) {
			posY = Constants.WORLD_HEIGHT;
		}
	}
	
	public int getDistanceInWorld(int distance){
		return distance * zoom;
	}
	
	public int worldToScreenX(int x){
		return (x - posX) / zoom; 
	}
	
	public int worldToScreenY(int y){
		return (y - posY) / zoom; 
	}
	
	public int screenToCellX(int x){
		return grid.getCellCornerX(screenToWorldX(x));
	}
	
	public int screenToCellY(int y){
		return grid.getCellCornerY(screenToWorldY(y));
	}
	
	
	public int screenToCellNumberX(int x){
		if(screenToWorldX(x) < 0){
			return (screenToWorldX(x) / grid.cellSize) - 1;		
		}
		else {
			return screenToWorldX(x) / grid.cellSize;
		}	
	}
	
	public int screenToCellNumberY(int y){
		if(screenToWorldY(y) < 0){
			return (screenToWorldY(y) / grid.cellSize) - 1;		
		}
		else {
			return screenToWorldY(y) / grid.cellSize;
		}
	}
	
	public int cellNumberToWorldX(int x){
		return x * grid.cellSize;
	}
	
	public int cellNumberToWorldY(int y){
		return y * grid.cellSize;
	}

	/*
	 * takes an x coordinate from the screen and returns the corresponding
	 * coordinate in the world
	 */
	public int screenToWorldX(int x){
		return x * zoom + posX;
	}
	
	/*
	 * takes ay y coordinate from the screen and returns the corresponding
	 * coordinate in the world
	 */
	public int screenToWorldY(int y){
		return y * zoom + posY;
	}
	
	
	/*
	 * the width of the part of the world that is displayed on screen
	 */
	public int getWidthInWorld(){
		return displayedWidth * zoom;
	}
	
	/*
	 * the height of the part of the world that is displayed on screen
	 */
	public int getHeightInWorld(){
		return displayedHeight * zoom;
	}
	
	/*
	 * Is a given point in the world on screen right now?
	 */
	public boolean containsInWorld(int x, int y){
		
		Rectangle r = new Rectangle(posX, posY, getWidthInWorld(), getHeightInWorld());
		
		return r.contains(x, y);
		
	}

}
