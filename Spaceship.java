
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spaceship {
	private static int width,height;
	private static double x,y;
	private BufferedImage spaceshipImage;
	private static double speed;
	
	
	 /**
     * Constructor that create an image of a Spaceship with a location and velocity
     */
	public Spaceship(){
		importImage();
		
		width = 48;
		height = 48;

		x = (MeteorMadnessWindow.Width/2) - (width/2);
		y = (MeteorMadnessWindow.Height*9)/10 - (height/2);
		
		speed = 0;
	}

	//Getters
	/**Gets the x-position of the spaceship
     * @return x- position of the spaceship
     */
	public static double getX(){
		return x;
	}
	
	/**Gets the y-position of the spaceship
     * @return y- position of the spaceship
     */
	public static double getY(){
		return y;
	}
	
	 /**Gets the width of the spaceship
     * @return width of the spaceship
     */
	public static int getWidth(){
		return width;
	}
	
	
	/**Gets the height of the spaceship
     * @return height of the spaceship
     */
	public static int getHeight(){
		return height;
	}
	
	/**Gets the speed of the spaceship
     * @return speed of the spaceship
     */
	public static double getSpeed(){
		return speed;
	}
	
	//Setters
	/**Sets the position of the spaceship
     * @param x x-position of the spaceship
     * @param y y-position of the spaceship
     */
	public void setPosition(double x, double y){
		Spaceship.x = x;
		Spaceship.y = y;
	}
	
	 /**
     * Sets the size of the spaceship(in pixels)
     * @param width Width of the spaceship (in pixels)
     * @param height Height of the spaceship (in pixels)
     */
	public void setSize(int width, int height){
		Spaceship.width = width;
		Spaceship.height = height;
	}
	
	/** 
     * Sets the speed of the spaceship
     * @param speed Speed of spaceship
     */
    public static void setSpeed(double speed){
        Spaceship.speed=speed;
    }
	
	
	/**
     * Moves the spaceship
     * @param speed Amount to move in y direction
     */
    public void move(){
    	Spaceship.x+=speed;
    }
	
    /**Checks for whether Spaceship and other objects have come in contact
     * @return true or false
     */
	public boolean collision(){
	    
		return true;
	}
	
	/**Decides which action to take if there is collision 
     * between Spaceship and other objects 
     */
	public void collide(){
		
	}
	
	 /**Imports image of spaceship
     *  
     */
	public void importImage(){
		try {
			spaceshipImage = ImageIO.read(new File("res/Spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Draws image of spaceship and sets its behaviors
     */
	public void drawSpaceship(Graphics graphics){
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.drawImage(spaceshipImage, (int)x,(int) y, width, height, null);
		move();
		if (getX() <= 0){
			setPosition(0, getY());
		}
		else if (getX() + getWidth() > MeteorMadnessWindow.Width){
			setPosition(MeteorMadnessWindow.Width - getWidth(), getY());
		}
	}
	
}