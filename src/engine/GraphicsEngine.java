package engine;

import game.Constants;

import java.awt.Color;
import java.awt.Graphics;

import world.Level;
import world.PhysicalObject;

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
		
		for(PhysicalObject object: level.objects){
			
			//for now we only care about rectangles
			//TODO change that
			if(object.isRectangle){

				//draw object if one of its corners is inside the screen area
				if(screen.containsInWorld(object.posX, object.posY)
						||screen.containsInWorld(object.posX + object.width, object.posY)
						||screen.containsInWorld(object.posX, object.posY + object.height)
						||screen.containsInWorld(object.posX + object.width, object.posY + object.height)){

					g.setColor(Color.BLACK);
					g.fillRect(
							screen.worldToScreenX(object.posX),
							screen.worldToScreenY(object.posY),							
							object.width / screen.zoom, 
							object.height / screen.zoom);

					g.setColor(Color.BLACK);
					g.drawRect(
							screen.worldToScreenX(object.posX),
							screen.worldToScreenY(object.posY),							
							object.width / screen.zoom, 
							object.height / screen.zoom);
				}

			}

		}
		
	}

}
