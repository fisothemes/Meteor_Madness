import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Represents a star that falls down a container
 */
public class Star {

    //Fields(attributes)
    private int x,y;
    private int height,width;
    private int speed;
    private BufferedImage starsImage;
    private Random r = new Random();
    
    private static int score;
    
    //Constructor
    public Star(){
        importImage();
        
        score = 0;
        
        x  = r.nextInt(MeteorMadnessWindow.Width-width);
        y = r.nextInt(MeteorMadnessWindow.Height/2)*-1;
        
        height = 42;
        width =42;
        
        speed = 7;
    }
    
    
    //Getters
    
     /**Gets the x-position of the star
     * @return x- position of the star
     */
    public int getX(){
        return x;
    }
    
    /**Gets the y-position of the Star
     * @return y- position of the Star
     */
    public int getY(){
        return y;
    }
    
     /**Gets the height of the Star
     * @return height- height of the Star
     */
    public int getHeight(){
        return height;
    }
    
    /**Gets the width of the Star
     * @return width- width of the Star
     */
    public int getWidth(){
        return width;
    }
    
    /**Gets the speed of the Star
     * @return speed- speed of the Star
     */
    public int getSpeed(){
        return speed;
    }
    
    /**Gets the score, represente by each star encounter with the spaceship
     * @return score- how many times the star encounters the spaceship
     */
    public static int getScore(){
    	return score;
    }
    
    //Setters
    
     /**
     * Sets the speed of the Star 
     * @param speed Speed of the Star
     */
    public void setSpeed(int mySpeed){
        this.speed=mySpeed;
    }
    
    /**
     * Sets the size of the Star(in pixels)
     * @param width Width of the Star (in pixels)
     * @param height Height of the Star (in pixels)
     */
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    
     /**Sets the position of the Star
     * @param x x-position of the Star
     * @param y y-position of the Star
     */
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    
     /**Sets the score of the game
     * @param score
     */
    public static void setScore(int score){
    	Star.score = score;
    }
    //Actions
    
     /**
     * Moves the Star
     */
    public void move(){
        this.y+=speed;
    }
    
     /**
     * Checks for whether Spaceship and Star have come in contact
     * @return true or false
     */
    public boolean collision(){
      //Checks bottom right corner of rectangle
        boolean yCoordCheckR = this.y + this.height >= Spaceship.getY() 
                && 
                this.y + this.height <= Spaceship.getY() + Spaceship.getHeight();
        boolean xCoordCheckR = this.x + this.width >= Spaceship.getX() 
                && 
                this.x + this.width <= Spaceship.getX() + Spaceship.getWidth();
        boolean bottomRightCheck = xCoordCheckR && yCoordCheckR;
        
          //Checks bottom left corner of rectangle
        boolean yCoordCheckL = this.y + this.height >= Spaceship.getY() 
                && 
                this.y + this.height <= Spaceship.getY() + Spaceship.getHeight();
        boolean xCoordCheckL = this.x >= Spaceship.getX() 
                && 
                this.x <= Spaceship.getX() + Spaceship.getWidth();
        boolean bottomLeftCheck = xCoordCheckL && yCoordCheckL;
        
        return bottomLeftCheck || bottomRightCheck;
    }
    
    /**
     * Imports image of Star 
     */
    public void importImage(){
        try {
            starsImage = ImageIO.read(new File("res/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     /**Draws image of Star and sets its behavior
     *  @param graphics The Graphics object on which the Star is drawn
     */
    public void drawStars(Graphics graphics){
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(starsImage, x, y, width, height, null);
        //g2d.rotate(Math.PI/5, width, height);
        
        if(getY() > MeteorMadnessWindow.Height)setPosition(r.nextInt(MeteorMadnessWindow.Width-width), r.nextInt(MeteorMadnessWindow.Height/2)*-1);
        if(collision()){ 
        	setPosition(r.nextInt(MeteorMadnessWindow.Width-width), r.nextInt(MeteorMadnessWindow.Height/2)*-1);
        	score++;
        	}
        move();
    }
    
}
