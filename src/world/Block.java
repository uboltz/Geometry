package world;

import java.awt.Color;
import java.awt.Graphics;

import game.Constants;

public class Block extends PhysicalObject{
	
	/*
	 * Constructs a rectangular block with the specified dimensions.
	 */
	public Block(int x, int y, int width, int height){
		
		this.posX = x;
		this.posY = y;
		
		this.width = width;
		this.height = height;
		
		this.isRectangle = true;
		
		this.isMovable = true;
		this.hasGravity = true;
		this.hasPhysics = true;
		
	}
	
	
	public void draw(Graphics g, int x, int y){
		
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
		
	}

}
