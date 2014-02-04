package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import engine.Screen;
import engine.Selector;

import world.Block;
import world.Level;

public class LevelEditor{
	
	
	public Task task = Task.CREATE;
	public int currentBlockType = Block.IMMOVABLE;
	
	private Level level;
	private Screen screen;
	private Main main;
	
	
	public List<Block> toBeMoved = new ArrayList<Block>();



	private enum Task {
		MOVE_SELECT {
			public void mousePressed(MouseEvent e, LevelEditor d){
				d.screen.selector.mousePressed(e.getX(), e.getY());
			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selector.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//store selected blocks
				Rectangle r = d.screen.selector.getRectangle();
				d.toBeMoved = d.level.getBlocksInRectangle(
						d.screen.screenToWorldX(r.x),
						d.screen.screenToWorldY(r.y), 
						d.screen.getDistanceInWorld(r.width), 
						d.screen.getDistanceInWorld(r.height));
				d.task = MOVE_MOVE;
			}
		},
		MOVE_MOVE {

			@Override
			public void mousePressed(MouseEvent e, LevelEditor d) {
				// store cursor x,y
				d.screen.arrow.mousePressed(e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e, LevelEditor d) {
				// show preview
				d.screen.arrow.mouseDragged(e.getX(), e.getY());			
			}

			@Override
			public void mouseReleased(MouseEvent e, LevelEditor d) {
				// change stored blocks' coordinates
				
				int changeX = d.screen.getDistanceInWorld(d.screen.arrow.x2 - d.screen.arrow.x1);
				int changeY = d.screen.getDistanceInWorld(d.screen.arrow.y2 - d.screen.arrow.y1);
				
				for(Block block: d.toBeMoved){
					block.posX += changeX;
					block.posY += changeY;
				}
				
				d.screen.arrow.mouseReleased();
				d.screen.selector.mouseReleased();
				d.task = MOVE_SELECT;
				
			}
			
		},
		CREATE {
			public void mousePressed(MouseEvent e, LevelEditor d){
				//store x,y
				d.screen.selector.mousePressed(e.getX(), e.getY());

			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selector.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//if grid is on, create multiple blocks with the same size
				if(d.screen.isGridEnabled){
					d.createBlocksInGrid(d.screen.selector);
				}
				//if grid is off, create single block
				else {
					d.createBlock(d.screen.selector);
				}
				
				
				
				
				
				d.screen.selector.mouseReleased();
			}
		},
		DELETE {
			public void mousePressed(MouseEvent e, LevelEditor d){
				//store x,y
				d.screen.selector.mousePressed(e.getX(), e.getY());
				
			}
			public void mouseDragged(MouseEvent e, LevelEditor d){
				//change selection rectangle
				d.screen.selector.mouseDragged(e.getX(), e.getY());
				d.main.drawScreen();
				
			}
			public void mouseReleased(MouseEvent e, LevelEditor d){
				//delete everything within selection rectangle
				d.deleteBlocksInScreenRectangle(d.screen.selector.getRectangle());
				
				d.screen.selector.mouseReleased();
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
		
		case KeyEvent.VK_G: screen.toggleGrid();
		break;
		
		}

	}


	private void createBlocksInGrid(Selector selector){
		
		Rectangle selection = selector.getRectangle();
		
		for(int x = screen.screenToCellNumberX(selection.x);
		x <= screen.screenToCellNumberX(selection.x + selection.width);
		x++){
			
			for(int y = screen.screenToCellNumberY(selection.y);
			y <= screen.screenToCellNumberY(selection.y + selection.height);
			y++){
				
				Block block = Block.createBlock(
						currentBlockType,
						screen.cellNumberToWorldX(x),
						screen.cellNumberToWorldY(y),							
						screen.grid.cellSize,
						screen.grid.cellSize);
				
				level.addBlock(block);
				
			}
		}
		
	}
	
	private void createBlock(Selector selector){
		
		Block block = new Block(currentBlockType);
		Rectangle selection = selector.getRectangle();
		
		block.posX = screen.screenToWorldX(selection.x);
		block.posY = screen.screenToWorldY(selection.y);
		block.width = screen.getDistanceInWorld(selection.width);
		block.height = screen.getDistanceInWorld(selection.height);
		
		level.addBlock(block);
		
	}

	/*
	 * set a blocks dimensions in the world to the ones corresponding to
	 * the input from the screen and add it to the world
	 */
	private void addBlockOnScreen(Block block, int x, int y){			

		if(screen.isGridEnabled){

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
	
	

}
