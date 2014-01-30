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
import world.WorldObject;

import engine.GraphicsEngine;
import engine.Physics;
import engine.Screen;

public class Main implements MouseListener, MouseMotionListener, KeyListener{

	public static final int PLAYING = 0, QUITTING = 1, PAUSED = 2;
	
	private int state = PAUSED;
	
	private GameWindow gameWindow;
	private GraphicsEngine engine;
	private Physics physics;
	private Level level;
	private Screen screen;
	private LevelEditor editor;
	
	public Main(){
		
		screen = new Screen();
		level = new Level();
		engine = new GraphicsEngine(level, screen);
		physics = new Physics(level);
		editor = new LevelEditor(level, screen);
		
		gameWindow = new GameWindow(this);
		
	}
	
	
	public static void main(String[] args){
	
		Main m = new Main();
		
		m.play();
		
		
	}
	
	
	
	private void play(){
		
		boolean playing = false;
		
		//load level

		FrameTimer frameTimer = new FrameTimer();
		
		while(state != QUITTING){

			if(state == PLAYING){

				System.out.println("playing");
				
				//process user input

				//process AI and physics
				physics.performFrame();

				//draw game world
				drawScreen();

				//draw overlay
			}
			
			
			frameTimer.waitForFrameEnd();
			
		}
	}
	
	
	
	private class FrameTimer {
		
		private Date lastTime;
		
		public FrameTimer(){
			lastTime = new Date();
		}
		
		public void waitForFrameEnd(){
			long remainingTime = (1000 / Constants.FRAMES_PER_SECOND) - (new Date().getTime() - lastTime.getTime());
			if(remainingTime > 0) {
				try {
					Thread.sleep(remainingTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lastTime = new Date();
			System.out.println("time: " + lastTime.getTime() + " remainder:" + remainingTime);	
		}
	}

	private void drawScreen(){
		engine.drawScreen(gameWindow.canvasGraphics);
		gameWindow.repaint();
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(state == PLAYING){
			state = PAUSED;
		}
		else {
			editor.mousePressed(e);
			drawScreen();
		}
		
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

			if(state == PLAYING) {
				state = PAUSED;
			}
			else{
				state = PLAYING;
			}

		}	
		
		editor.keyPressed(e);
		
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
