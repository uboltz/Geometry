package game;

import engine.GraphicsEngine;
import engine.Physics;
import engine.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import world.Level;

public class Main implements MouseListener, MouseMotionListener, KeyListener{

	public static final int PLAYING = 0, QUITTING = 1, PAUSED = 2;
	
	private int state = PAUSED;
	
	private GameWindow gameWindow;
	private GraphicsEngine engine;
	private Physics physics;
	private Level level;
	private Screen screen;
	private LevelEditor editor;
	public KeyboardInput input;
	public Controls controls;
	
	public Main(){
		
		screen = new Screen();
		level = new Level();
		engine = new GraphicsEngine(level, screen);
		physics = new Physics(level);
		editor = new LevelEditor(level, screen, this);
		input = new KeyboardInput();
		controls = new Controls(physics, input);
		
		gameWindow = new GameWindow(this);
		
	}
	
	
	public static void main(String[] args){
	
//		Main m = new Main();
//		
//		m.play();
		
		
		try {
			File f = new File("C:\\Program Files\\1&1 Surf-Stick\\log\\autoztemon.txt");
			
			FileReader in = new FileReader(f);
			BufferedReader reader = new BufferedReader(in);
			
			
			
			
			for(int i = 0; i < 200; i++){
				String s = reader.readLine();
				System.out.println(s);
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	private void play(){
		
		boolean playing = false;
		
		//load level

		FrameTimer frameTimer = new FrameTimer();
		
		while(state != QUITTING){

			if(state == PLAYING){

				System.out.println("playing");
				
				//process user input
				controls.processInput();

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

	public void drawScreen(){
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
	public void mouseReleased(MouseEvent e) {
		
		if(state == PLAYING){
			state = PAUSED;
		}
		else {
			editor.mouseReleased(e);
			drawScreen();
		}		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(state == PLAYING){
			state = PAUSED;
		}
		else {
			editor.mouseDragged(e);
			drawScreen();
		}		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			if(state == PLAYING) {
				state = PAUSED;
			}
			else{
				state = PLAYING;
			}

		}	
		
		if(state == PAUSED) {
			editor.keyPressed(e);
			drawScreen();
		}
		
		

		System.out.println("Screen at: " + screen.posX() + " " + screen.posY() 
				+ " Zoom: " + screen.zoomFactor());
		
	}
	

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// 
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// 
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// 
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// 
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// 
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// 
		
	}

	
}
