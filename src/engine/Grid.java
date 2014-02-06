package engine;

/**
 * Provides a way to separate the game world into discrete cells, used to position
 * stuff in neat and tidy patterns.
 * 
 * For now, cells are squares, but could change to any kind of rectangle later.
 * Do not ask me why that would be desirable.
 * 
 * @author Hans Maulwurf
 *
 */
public class Grid {
	
	//the length of the cells' sides
	public final int cellSize;
	
	/*
	 * Constructor
	 */
	public Grid(int cellSize){
		this.cellSize = cellSize;
	}
	
	
	/*
	 * returns the x-coordinate of the upper left corner the cell a point is in
	 */
	public int getCellCornerX(int x){
		return (x / cellSize) * cellSize;
	}
	
	/*
	 * returns the y-coordinate of the upper left corner of the cell a point is in
	 */
	public int getCellCornerY(int y){
		return (y / cellSize) * cellSize;
	}
	

}
