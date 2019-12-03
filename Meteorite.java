import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Represents a meteor that falls down a container
 */
public class Meteorite {
    //fields(attributes)
    private double x,y;
    private int height,width;
    private double speed;
    private BufferedImage meteoriteImage;
    private Random r = new Random();
    
    /**
     * Constructor that create an image of a meteor with a location and velocity
     */
    public Meteorite(){
        importImage();
        
        
        x  = r.nextInt(MeteorMadnessWindow.Width-width);
        y = r.nextInt(MeteorMadnessWindow.Height/2)*-1;
        
        height = 42;
        width =42;
        
        speed = 7;
        
    }
    
    
    //Getters
    /**Gets the x-position of the meteor
     * @return x- position of the meteor
     */
    public double getX(){
        return x;
    }
    
    /**Gets the y-position of the meteor
     * @return y- position of the meteor
     */
    public double getY(){
        return y;
    }
   
    
    /**Gets the height of the meteor
     * @return height- height of the meteor
     */
    public double getHeight(){
        return height;
    }
    
    /**Gets the width of the meteor
     * @return width- width of the meteor
     */
    public double getWidth(){
        return width;
    }
    
    /**Gets the speed of the meteor
     * @return speed- speed of the meteor
     */
    public double getSpeed(){
        return speed;
    }
    
    
    //Setters
    /**Sets the position of the meteor
     * @param x x-position of the meteor
     * @param y y-position of the meteor
     */
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    
     /**
     * Sets the speed of the meteor 
     * @param speed Speed of the meteor
     */
    public void setSpeed(int speed){
        this.speed=speed;
    }
    
     /**
     * Sets the size of the meteor(in pixels)
     * @param width Width of the meteor (in pixels)
     * @param height Height of the meteor (in pixels)
     */
    public void setSize(int width, int height){
        this.width=width;
        this.height=height;
    }
    
    
    //Actions
     /**
     * Moves the meteor
     */
    public void move(){
        if(speed<0){
            this.y-=speed;
        }
        else{
            this.y+=speed;
        }
    }
    
    /**
     * Checks for whether Spaceship and Meteor have come in contact
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
     * Imports image of meteor 
     */
    public void importImage(){
        try {
            meteoriteImage = ImageIO.read(new File("res/meteorite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**Draws image of Meteor and sets its behavior
     *  @param graphics The Graphics object on which the meteor is drawn
     */
    public void drawMeteorite(Graphics graphics){
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(meteoriteImage, (int) x, (int) y, width, height, null);
        //g2d.rotate(Math.PI/5, width, height);
        
        if(getY() > MeteorMadnessWindow.Height)setPosition(r.nextInt(MeteorMadnessWindow.Width-width), r.nextInt(MeteorMadnessWindow.Height/2)*-1);
        if(collision())setPosition(r.nextInt(MeteorMadnessWindow.Width-width), r.nextInt(MeteorMadnessWindow.Height/2)*-1);
        move();
    }

}