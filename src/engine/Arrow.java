package engine;

import java.awt.Rectangle;

public class Arrow {

	public int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	
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
	
		
}
