package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * An extended JPanel that is used as a canvas to display the games graphics on.
 */
public class Canvas extends JPanel{
	
	//this will be painted to the canvas every frame
	private Image buffer;
	
	
	/*
	 * Constructor
	 */
	public Canvas(){
		
		this.setPreferredSize(
				new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
		
		buffer = new BufferedImage(
				Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT,
				BufferedImage.TYPE_3BYTE_BGR);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.drawImage(buffer, 0, 0, null);
		
	}
	
	
	public Graphics getGraphics(){
		return buffer.getGraphics();
	}

}
