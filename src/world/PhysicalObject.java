package world;

import java.awt.Graphics;

public abstract class PhysicalObject {
	
	public int posX, posY;
	
	public int width, height;
	
	public int momentumX, momentumY;
	
	public int orientation;
	
	public int spinningMomentum;
	
	public int mass, airResistance;
	
	public boolean isRectangle, isCircle, hasBitmap;
	
	public boolean hasCollisions, isMovable;
	
	public int collisionElasticity;
	
	public abstract void draw(Graphics g, int x, int y);

}
