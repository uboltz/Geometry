package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Constants;

public class Block extends WorldObject{
	
	
	public static final int 		
	IMMOVABLE = 1,
	MOVABLE = 2,
	PLAYER = 3;
	
	public int width, height;
	
	/*
	 * Constructs a rectangular block with the specified dimensions.
	 */
	public static Block createBlock(int type, int x, int y, int width, int height){
		
		Block block = new Block(type);
		
		block.posX = x;
		block.posY = y;
		
		block.width = width;
		block.height = height;
		
		return block;
		
	}
	
	public Block(){};
	
	public Block(int type){
		if(type == MOVABLE){
			this.isMovable = true;
			this.hasGravity = true;
			this.isControlledByUser = false;
		}
		else if(type == IMMOVABLE){
			this.isMovable = false;
			this.hasGravity = false;			
			this.isControlledByUser = false;
		}
		else if(type == PLAYER){
			this.isMovable = true;
			this.hasGravity = true;
			this.isControlledByUser = true;
		}
	}
	
	
	public boolean contains(int x, int y){
		return new Rectangle(posX, posY, width, height).contains(x,y);
	}
	

}
