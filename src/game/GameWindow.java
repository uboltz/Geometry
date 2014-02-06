package game;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
	    
	    //make it so that the canvas is focused when clicked on, 
	    //otherwise KeyEvents might be send to a different component
	    canvas.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e){
	    		e.getComponent().requestFocusInWindow();
	    	}
	    });
	    	       
	    //register listeners
	    canvas.addMouseListener(main);
	    canvas.addMouseMotionListener(main);
	    canvas.addKeyListener(main);
	    canvas.addKeyListener(main.input);
	    
	    
	    //radio button group to select editor action
	    ButtonGroup buttonGroup = new ButtonGroup();
	    
	    
	    //button for creating standard sized blocks
	    JRadioButton standardButton = new JRadioButton("standard size");
	    buttonGroup.add(standardButton);
	    leftSidePanel.add(standardButton);
	    
	    //button for creating custom sized blocks
	    JRadioButton customButton = new JRadioButton("custom size");
	    buttonGroup.add(customButton);
	    leftSidePanel.add(customButton);
	    
	    //button for creating blocks by dragging the mouse
	    JRadioButton dragButton = new JRadioButton("drag mouse");
	    buttonGroup.add(dragButton);
	    leftSidePanel.add(dragButton);
	    
	    //button for moving blocks
	    JRadioButton moveButton = new JRadioButton("move blocks");
	    buttonGroup.add(moveButton);
	    leftSidePanel.add(moveButton);
	    
	    //input fields for custom size
	    JLabel widthLabel = new JLabel("Width:");
	    leftSidePanel.add(widthLabel);
	    
	    JTextField widthField = new JTextField(10);	 
	    leftSidePanel.add(widthField);
	    
	    JLabel heightLabel = new JLabel("Height:");
	    leftSidePanel.add(heightLabel);
	    
	    JTextField heightField = new JTextField(10);	    
	    leftSidePanel.add(heightField);
	    
	    
	    //now display everything
	    pack();
	    setVisible(true);
	    
	}

}
