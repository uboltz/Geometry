package game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import engine.Screen;

import world.Block;
import world.Level;

public class LevelEditor{

	public static final int 
		MOVE = 0,
		CREATE_IMMOVABLE = 1,
		CREATE_MOVABLE = 2,
		CREATE_CUSTOM = 3,
		CREATE_DRAGGING = 4,
		DELETE = 5;

	
	public int task = CREATE_IMMOVABLE;
	
	private Level level;
	private Screen screen;
	
	private boolean isGridEnabled = true;
	
	
	public LevelEditor(Level level, Screen screen){
		
		this.level = level;
		this.screen = screen;	

	}

	public void mousePressed(MouseEvent e){

		switch(task){
		case CREATE_IMMOVABLE : createImmovableBlockAt(e.getX(), e.getY());
		break;		
		case CREATE_MOVABLE : createMovableBlockAt(e.getX(), e.getY());
		break;		
		case DELETE : deleteBlocksAt(e.getX(), e.getY());
		break;

		}

	}

	public void keyPressed(KeyEvent e){

		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT: screen.moveX(-10);
		break;
		case KeyEvent.VK_RIGHT: screen.moveX(+10);
		break;
		case KeyEvent.VK_UP: screen.moveY(-10);
		break;
		case KeyEvent.VK_DOWN: screen.moveY(+10);
		break;
		case KeyEvent.VK_Q: screen.changeZoom(90);
		break;
		case KeyEvent.VK_A: screen.changeZoom(110);
		break;

		case KeyEvent.VK_S: task = CREATE_IMMOVABLE;
		break;
		case KeyEvent.VK_M: task = CREATE_MOVABLE;
		break;
		case KeyEvent.VK_D: task = DELETE;
		break;

		}

	}
	
		/*
		 * set a blocks dimensions in the world to the ones corresponding to
		 * the input from the screen and add it to the world
		 */
		private void addBlockAt(Block block, int x, int y, int width, int height){			
			
			if(isGridEnabled){
				
				block.posX = screen.screenToCellX(x);
				block.posY = screen.screenToCellY(y);
				block.width = screen.grid.cellSize;
				block.height = screen.grid.cellSize;
				level.addBlock(block);
				
				
			}
			
			else {
				block.posX = screen.screenToWorldX(x);
				block.posY = screen.screenToWorldY(y);
				block.width = width * screen.zoom;
				block.height = height * screen.zoom;
				level.addBlock(block);
			}
			
		}
		
	
		
		private void createMovableBlockAt(int x, int y){
			
			createMovableBlockAt(x, y, screen.grid.cellSize, screen.grid.cellSize);		
		}
		
		private void createImmovableBlockAt(int x, int y){

			createImmovableBlockAt(x, y, screen.grid.cellSize, screen.grid.cellSize);		
		}
		
		
		private void createMovableBlockAt(int x, int y, int width, int height){
			
			addBlockAt(createMovableBlock(), x, y, width, height);
			
		}
		
		private void createImmovableBlockAt(int x, int y, int width, int height){
			
			addBlockAt(createImmovableBlock(), x, y, width, height);
			
		}
		
		
		private Block createMovableBlock(){
			
			Block block = new Block();
			block.hasGravity = true;
			block.isMovable = true;
			
			return block;
						
		}
		
		public static Block createImmovableBlock(){
			
			Block block = new Block();
			block.hasGravity = false;
			block.isMovable = false;
			
			return block;
		}
		
		
		
		/*
		 * delete all blocks that contain the specified point on the given screen
		 */
		private void deleteBlocksAt(int x, int y){
			
			x = screen.screenToWorldX(x);
			y = screen.screenToWorldY(y);
			level.deleteBlocksAt(x,y);
		}
		
	
	
	

}
