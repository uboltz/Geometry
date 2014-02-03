package engine;

import java.awt.Rectangle;

/**
 * A selection rectangle to be used with mouse listeners.
 * The rectangle is created by dragging the mouse.
 * 
 * @author Hans Maulwurf
 *
 */
public class Selector {
	
	
	private int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	
	public boolean isVisible = false;
	
	public void mousePressed(int x, int y){
		x1 = x;
		y1 = y;
		
		x2 = x;
		y2 = y;
	}
	
	public void mouseDragged(int x, int y){
		x2 = x;
		y2 = y;
		isVisible = true;
	}
	
	public void mouseReleased(){
		isVisible = false;
	}
	
	public Rectangle getRectangle(){
		
		if((x1 <= x2) && (y1 <= y2)){
			return new Rectangle(x1, y1, x2 - x1, y2 - y1);
		}
		else if((x1 > x2) && (y1 <= y2)){
			return new Rectangle(x2, y1, x1 - x2, y2 - y1);
			
		}
		else if((x1 <= x2) && (y1 > y2)){
			return new Rectangle(x1, y2, x2 - x1, y1 - y2);
			
		}
		else if((x1 > x2) && (y1 > y2)){
			return new Rectangle(x2, y2, x1 - x2, y1 - y2);
			
		}
		//just in case; this should never happen 
		else {
			return new Rectangle(0,0,0,0);
		}
		
	}
	

}
