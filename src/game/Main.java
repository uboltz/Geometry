package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import world.Level;

import engine.GraphicsEngine;
import engine.Screen;

public class Main implements MouseListener, MouseMotionListener, KeyListener{

	private GameWindow gameWindow;
	private GraphicsEngine engine;
	private Level level;
	private Screen screen;
	
	public Main(GraphicsEngine engine, Screen screen, Level level){
		
		this.engine = engine;
		this.level = level;
		this.screen = screen;
		
		gameWindow = new GameWindow(this);
	}
	
	
	public static void main(String[] args){
				
		Screen screen = new Screen();
		Level level = new Level();
		GraphicsEngine engine = new GraphicsEngine(level, screen);
		
		Main m = new Main(engine, screen, level);
		
	}
	
	
	
	private void play(){
		
		boolean playing = true;
		
		//load level
		
		while(playing){
			
			//process user input
			
			//process AI and physics
			
			//draw game world
			
			//draw overlay
			
		}
		
	}
	
	private void drawScreen(){
		engine.drawScreen(gameWindow.canvasGraphics);
		gameWindow.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		level.addBlock(
				((e.getX() * screen.zoom + screen.posX) / Constants.BLOCK_SIZE) * Constants.BLOCK_SIZE, 
				((e.getY() * screen.zoom + screen.posY) / Constants.BLOCK_SIZE) * Constants.BLOCK_SIZE);

		drawScreen();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
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
		}		
		
		drawScreen();

		System.out.println("Screen at: " + screen.posX + " " + screen.posY + " Zoom: " + screen.zoom);
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
