package game;

import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The game's main Window. It holds an extended JPanel as a canvas on which
 * the game world is displayed.
 * Additionally it also has buttons and stuff for level editing and testing.
 * 
 * @author Hans Maulwurf
 *
 */
public class GameWindow extends JFrame{
	
	//for now, this is how other classes access the canvas
	public Graphics canvasGraphics;
	
	public GameWindow(Main main){
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//this will hold the side panel and the canvas
		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
		this.add(horizontalPanel);
		
		//this will hold buttons to click on
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
		horizontalPanel.add(leftSidePanel);
		
		//this will display the actual game world
	    Canvas canvas = new Canvas();
	    horizontalPanel.add(canvas);
	    canvasGraphics = canvas.getGraphics();
	    	       
	    canvas.addMouseListener(main);
	    canvas.addMouseMotionListener(main);
	    canvas.addKeyListener(main);
	    this.addKeyListener(main);
	    
	    
	    pack();
	    setVisible(true);
	    
	}

}
