import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a heart that disappears when the Spaceship comes in contact with a meteor
 * 
 * @author Group L 
 * @version 24 April 2017
 */
public class Life {
    
    //Fields(attributes)
    private int lifeCount = 3;
    private int x,y;
    private int width, height;
    private BufferedImage heartImage;
 
    
    //Constructor
    public Life(){
        importImage();
        x = 0;
        y = 0;
        
        height = 36;
    	width =36;
    }
    
    //Setters
    /**Sets the position of the heart
     * @param x x-position of the heart
     * @param y y-position of the heart
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the size of the heart(in pixels)
     * @param width Width of the heart (in pixels)
     * @param height Height of the heart (in pixels)
     */
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    /**
     * Sets the counts for player lives
     * @param lifeCount Number of lives player has (in pixels)
     */
    public void setLife(int lifeCount){
        this.lifeCount = lifeCount;
    }
    
    
    //Getters
    /**Gets the x-position of the heart
     * @return x- position of the heart
     */
    public int getX(){
        return this.x;
    }
    
    /**Gets the y-position of the heart
     * @return y- position of the heart
     */
    public int getY(){
        return this.y;
    }
    
    /**Gets the width of the heart
     * @return width of the heart
     */
    public int getWidth(){
        return this.width;
    }
    
    /**Gets the height of the heart
     * @return height of the heart
     */
    public int getHeight(){
        return this.height;
    }
    
    /**Gets number of lives of player
     * @return LifeCount- number of lives
     */
    public int getLife(){
        return lifeCount;
    }
    
    /**Draws image of heart
     * 
     */
    public void importImage(){
        try {
            heartImage = ImageIO.read(new File("res/heartt.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**Draws image of Heart
     *  @param graphics The Graphics object on which the heart is drawn
     */
    public void drawHeart(Graphics graphics){
        Graphics2D g2d = (Graphics2D) graphics;
		g2d.drawImage(heartImage, x, y, width, height, null);
		
    }
}
