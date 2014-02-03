package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import engine.GraphicsEngine;
import engine.Screen;

import world.Block;
import world.Level;

public class LevelEditor{
	
	
	public Task task = Task.CREATE;
	public int currentBlockType = Block.IMMOVABLE;
	
	private Level level;
	private Screen screen;
	private Main main;
	
	public boolean isGridEnabled = true;



	private enum Task {
		MOVE_SELECT {
			public void mousePressed(MouseEvent e, LevelEditor d){
				d.screen.selection.mousePressed(e.getX(), e.getY());
			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selection.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//change selected block's x,y; grid plays a role
			}
		},
		MOVE_MOVE {

			@Override
			public void mouseDragged(MouseEvent e, LevelEditor d) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e, LevelEditor d) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e, LevelEditor d) {
				// TODO Auto-generated method stub
				
			}
			
		},
		CREATE {
			public void mousePressed(MouseEvent e, LevelEditor d){
				//store x,y
				d.screen.selection.mousePressed(e.getX(), e.getY());

			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selection.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//if grid is on, create multiple blocks with the same size
				
				Rectangle selection = d.screen.selection.getRectangle();
				
				for(int x = d.screen.screenToCellNumberX(selection.x);
				x <= d.screen.screenToCellNumberX(selection.x + selection.width);
				x++){
					
					for(int y = d.screen.screenToCellNumberY(selection.y);
					y <= d.screen.screenToCellNumberY(selection.y + selection.height);
					y++){
						
						Block block = Block.createBlock(
								d.currentBlockType,
								d.screen.cellNumberToWorldX(x),
								d.screen.cellNumberToWorldY(y),							
								d.screen.grid.cellSize,
								d.screen.grid.cellSize);
						
						d.level.addBlock(block);
						
					}
				}
				
				
				//if grid is off, create single block
				
				
				d.screen.selection.mouseReleased();
			}
		},
		DELETE {
			public void mousePressed(MouseEvent e, LevelEditor d){
				//store x,y
				d.screen.selection.mousePressed(e.getX(), e.getY());
				
			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selection.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
				
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//delete everything within selection rectangle
				d.deleteBlocksInScreenRectangle(d.screen.selection.getRectangle());
				
				d.screen.selection.mouseReleased();
				d.main.drawScreen();
			}
		};
		
		public abstract void mousePressed(MouseEvent e, LevelEditor d);
		public abstract void mouseDragged(MouseEvent e, LevelEditor d);
		public abstract void mouseReleased(MouseEvent e, LevelEditor d);
		
		
	}	
	
	
	public LevelEditor(Level level, Screen screen, Main main){
		
		this.level = level;
		this.screen = screen;	
		this.main = main;
		
	}

	public void mousePressed(MouseEvent e){
		task.mousePressed(e, this);
	}
	
	public void mouseReleased(MouseEvent e){
		task.mouseReleased(e, this);
	}
	
	public void mouseDragged(MouseEvent e){
		task.mouseDragged(e, this);
	}
	
	
	

	public void keyPressed(KeyEvent e){

		switch(e.getKeyCode()) {
		//move screen
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

		//decide what will happen at the next mouse click
		case KeyEvent.VK_C: task = Task.CREATE;
		break;
		case KeyEvent.VK_M: task = Task.MOVE_SELECT;
		break;
		case KeyEvent.VK_D: task = Task.DELETE;
		break;
		
		//decide type of the next block that gets created
		case KeyEvent.VK_N: currentBlockType = Block.MOVABLE;
		break;
		case KeyEvent.VK_I: currentBlockType = Block.IMMOVABLE;
		break;
		
		case KeyEvent.VK_G: toggleGrid();
		break;
		
		}

	}
	
		/*
		 * set a blocks dimensions in the world to the ones corresponding to
		 * the input from the screen and add it to the world
		 */
		private void addBlockOnScreen(Block block, int x, int y){			
			
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
				block.width = screen.grid.cellSize;
				block.height = screen.grid.cellSize;
				level.addBlock(block);
			}
			
		}
		
		
		/*
		 * delete all blocks that contain the specified point on the given screen
		 */
		private void deleteBlocksOnScreen(int x, int y){
			
			x = screen.screenToWorldX(x);
			y = screen.screenToWorldY(y);
			level.deleteBlocksAt(x,y);
		}
		
	
		private void deleteBlocksInScreenRectangle(Rectangle r){
			level.deleteBlocksInRectangle(
					screen.screenToWorldX(r.x),
					screen.screenToWorldY(r.y),
					screen.getDistanceInWorld(r.width),
					screen.getDistanceInWorld(r.height));
		}
	
		/*
		 * switches the grid on and off
		 */
		private void toggleGrid(){
			if(isGridEnabled){
				isGridEnabled = false;
			}
			else {
				isGridEnabled = true;
			}
		}
	

}
