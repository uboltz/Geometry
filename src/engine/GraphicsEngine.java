package engine;

import game.Constants;

import java.awt.Color;
import java.awt.Graphics;

import world.Block;
import world.Level;
import world.WorldObject;

/**
 * This is where information about the level is processed into an image that can
 * be displayed.
 * 
 * @author Hans Maulwurf
 *
 */
public class GraphicsEngine {
	
	private Level level;
	private Screen screen;
	
	public GraphicsEngine(Level level, Screen screen){
		
		this.level = level;
		this.screen = screen;
		
	}

	public void drawScreen(Graphics g){

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);

		drawGrid(g);
		
		drawWorld(g);
		
		drawOverlay(g);
		
	}
	
	/*
	 * draws the part of the world that is currently on screen
	 */
	private void drawWorld(Graphics g){
		
		for(Block block: level.blocks){

			//draw object if one of its corners is inside the screen area
			if(screen.containsInWorld(block.posX, block.posY)
					||screen.containsInWorld(block.posX + block.width, block.posY)
					||screen.containsInWorld(block.posX, block.posY + block.height)
					||screen.containsInWorld(block.posX + block.width, block.posY + block.height)){

				
				if(block.isMovable){
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.GREEN);
				}				
				
				g.fillRect(
						screen.worldToScreenX(block.posX),
						screen.worldToScreenY(block.posY),							
						block.width / screen.zoom, 
						block.height / screen.zoom);

				g.setColor(Color.BLACK);
				g.drawRect(
						screen.worldToScreenX(block.posX),
						screen.worldToScreenY(block.posY),							
						block.width / screen.zoom, 
						block.height / screen.zoom);
			}

		}
	}

	
	private void drawGrid(Graphics g){
		if(screen.isGridEnabled){

			g.setColor(Color.GRAY);

			int x = screen.posX;

			while(x < screen.posX + screen.getWidthInWorld()){
				int lineX  = x / screen.grid.cellSize;

				if (lineX == 0) {
					System.out.println("zero");
				}
			
				lineX *= screen.grid.cellSize;

				g.drawLine(screen.worldToScreenX(lineX), 0,
						screen.worldToScreenX(lineX), Constants.CANVAS_HEIGHT);

				x += screen.grid.cellSize;
			}

			int y = screen.posY;

			while(y < screen.posY + screen.getHeightInWorld()){
				int lineY  = y / screen.grid.cellSize;

				lineY *= screen.grid.cellSize;

				g.drawLine(0, screen.worldToScreenY(lineY),
						Constants.CANVAS_WIDTH, screen.worldToScreenY(lineY));

				y += screen.grid.cellSize;
			}
		}
	}


	private void drawOverlay(Graphics g){

		screen.drawOverlay(g);

	}

}
