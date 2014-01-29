package world;

import java.awt.Graphics;

/**
 * As of now, this abstract class doesn't do much. It is mostly here as a reminder
 * for some possible later features.
 * It is meant to be the superclass of all objects in the game that are affected
 * by physics.
 * 
 * 
 * @author Hans Maulwurf
 *
 */
public abstract class PhysicalObject {
	
	public int posX, posY;
	
	public int width, height;
	
	public int momentumX, momentumY;
	
	public int orientation;
	
	public int spinningMomentum;
	
	public int mass, airResistance;
	
	public boolean isRectangle, isCircle, hasBitmap;
	
	public boolean hasPhysics, hasCollisions, isMovable, hasGravity;
	
	public int collisionElasticity;
	
	public abstract void draw(Graphics g, int x, int y);

}
