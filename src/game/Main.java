package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import world.Level;
import world.PhysicalObject;

import engine.GraphicsEngine;
import engine.Physics;
import engine.Screen;

public class Main implements MouseListener, MouseMotionListener, KeyListener{

	public static final int PLAYING = 0;
	public static final int CREATE_STANDARD = 1;
	public static final int CREATE_CUSTOM = 2;
	public static final int CREATE_DRAGGING = 3;
	public static final int MOVE = 4;
	public static final int DELETE = 5;
	public static final int QUITTING = 6;
	
	
	private int state = CREATE_STANDARD;
	
	private GameWindow gameWindow;
	private GraphicsEngine engine;
	private Physics physics;
	private Level level;
	private Screen screen;
	
	public Main(){
		
		screen = new Screen();
		level = new Level();
		engine = new GraphicsEngine(level, screen);
		physics = new Physics(level);
		
		gameWindow = new GameWindow(this);
		
	}
	
	
	public static void main(String[] args){
	
		Main m = new Main();
		
		m.play();
		
	}
	
	
	
	private void play(){
		
		boolean playing = false;
		
		//load level

		Date lastTime = new Date();
		
		while(state != QUITTING){

			if(state == PLAYING){

				//process user input

				//process AI and physics
				physics.performFrame();

				//draw game world
				drawScreen();

				//draw overlay

			}

			long remainingTime = (1000 / Constants.FRAMES_PER_SECOND) - (new Date().getTime() - lastTime.getTime());
			if(remainingTime > 0) {
				try {
					Thread.sleep(remainingTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lastTime = new Date();
//				System.out.println("time: " + lastTime.getTime() + " remainder:" + remainingTime);
			}
		}
	}
	
	private void drawScreen(){
		engine.drawScreen(gameWindow.canvasGraphics);
		gameWindow.repaint();
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(state){
		case CREATE_STANDARD : level.createStandardBlock(screen, e.getX(), e.getY());
		break;		
		case DELETE : level.deleteBlocksAt(screen, e.getX(), e.getY());
		break;
		
		}

		drawScreen();
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE: state = PLAYING;
		break;
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
		
		case KeyEvent.VK_S: state = CREATE_STANDARD;
		break;
		case KeyEvent.VK_D: state = DELETE;
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
