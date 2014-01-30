package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Constants;

public class Block extends WorldObject{
	
	public int width, height;
	
	/*
	 * Constructs a rectangular block with the specified dimensions.
	 */
	private Block(int x, int y, int width, int height){
		
		this.posX = x;
		this.posY = y;
		
		this.width = width;
		this.height = height;
		
		this.isMovable = true;
		this.hasGravity = true;
		
	}
	
	public Block(){};
	
	
	public boolean contains(int x, int y){
		return new Rectangle(posX, posY, width, height).contains(x,y);
	}
	

}
