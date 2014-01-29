package game;

public class Constants {
	
	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;
	
	public static final int GRID_CELL_SIZE = 25000;
	
	public static final int WORLD_WIDTH = 1000000000;
	public static final int WORLD_HEIGHT = 1000000000;
	
	//world pixels per screen pixel
	public static final int STANDARD_ZOOM = 1000;
	
	public static final int FRAMES_PER_SECOND = 120;
	public static final int MICROSECS_PER_FRAME = 1000000 / FRAMES_PER_SECOND;
	
	//for now, this is pixels per frame; long term it should probably be
	//acceleration (pixels per frame per frame)
	public static final int STANDARD_GRAVITY = 100;

}
