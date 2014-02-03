package engine;

/**
 * Provides a way to separate the game world into discrete cells, used to position
 * stuff in neat and tidy patterns.
 * 
 * @author Hans Maulwurf
 *
 */
public class Grid {
	
	public final int cellSize;
	
	public Grid(int cellSize){
		this.cellSize = cellSize;
	}
	
	
	/*
	 * returns the x-coordinate of the upper left corner of a point's cell
	 */
	public int getCellCornerX(int x){
		return (x / cellSize) * cellSize;
	}
	
	/*
	 * returns the y-coordinate of the upper left corner of a point's cell
	 */
	public int getCellCornerY(int y){
		return (y / cellSize) * cellSize;
	}
	

}
