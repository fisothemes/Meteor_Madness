import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This  class runs the program
 * 
 * @author Group L 
 * @version 24 April 2017
 */

public class MeteorMadnessPanel extends JPanel {
	
	private List<Star> stars;   //Store a list of stars
	private List<Meteorite> meteorites;   //Store a list of meteors
	private List<Spaceship> spaceships;   //Store a list of spaceships
	private List<Life> lives;
	
	private BufferedImage background;
	
	/**
	 * Sets the main graphical components windows objects will be viewed in.
     */
	public MeteorMadnessPanel(){
		//Window
		createView(); //creates window
		importBackground(); //imports background
		refreshRate();
		
		//Objects
		spaceships = Collections.synchronizedList(new ArrayList<Spaceship>());
		meteorites =Collections.synchronizedList(new ArrayList<Meteorite>());
		stars = Collections.synchronizedList(new ArrayList<Star>());
		lives = Collections.synchronizedList(new ArrayList<Life>());
	}
	
	/**Creates the windows objects will be viewed in.
     * 
     */
	public void createView(){
		setPreferredSize(new Dimension(MeteorMadnessWindow.Width,MeteorMadnessWindow.Height));
	}
	
	/**
	 * Imports image of background 
     */
	public void importBackground(){
		try {
			 background = ImageIO.read(new File("res/gameBG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**Adds a star
     * @param star The star to add
     */
	public void add(Star star){
		stars.add(star);
	}
	
	/**Adds a meteor
     * @param meterite The meteor to add
     */
	public void add(Meteorite meteorite){
		 meteorites.add(meteorite);
	}
	
	/**Adds a spaceship
     * @param spaceship The spaceship to add
     */
	public void add(Spaceship spaceship){
		spaceships.add(spaceship);
	}
	
	/**Adds a life
     * @param life The life/heart to add
     */
	public void add(Life life){
		lives.add(life);
	}
	
	
	/**Removes a star
     * @param stars The star to remove
     */
	public void remove(Star star){
		stars.remove(star);
	}
	
	/**Removes a meteor
     * @param meteorite The meteor to remove
     */
	public void remove(Meteorite meteorite){
		meteorites.remove(meteorite);
	}
	
	/**Removes a spaceship 
     * @param spaceship The spaceship to remove
     */
	public void remove(Spaceship spaceship){
		spaceships.remove(spaceship);
	}
	
	/**Removes a life 
     * @param life The spaceship to remove
     */
	public void remove(Life life){
		lives.remove(life);
	}
	
	/**
	 * Refreshes the screen at a rate of 10milliseconds
     */
	public void refreshRate() {
      ActionListener animate = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
          	repaint();
          }
      };
      Timer timer = new Timer(10,animate);
      timer.start();
	}
	
	/**Reads the highest score from at file called highscore.txt in the res folder
     * @return line - returns highest score as a string
     */
	public String readScore(){
		BufferedReader br=null;
		String line = null;
		    try {
		    	br = new BufferedReader(new FileReader("res/highscore.txt"));
		        while (br.ready()) {
		        	line = br.readLine();
		        }
		    } catch (IOException e){
		    	e.printStackTrace();
	        }
		    return line;
	}
	
	/**Writes the highest score to a file called highscore.txt in the res folder
     * @param score The string to be writen to the file.
     */
	public void writeScore(String score){
		 try{
	            BufferedWriter bw = new BufferedWriter(new PrintWriter("res/highscore.txt"));
	            
	            
	            bw.write(score);
	            bw.close();
	        }
	        catch (IOException e){
	            e.printStackTrace();
	        }
	}
	
	/**
     * Draws all Sprites and sets their behaviors
     * Draws Background image
     * Draws all Text such as "Score: XXX" and "Game Over"
     */
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		
		
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.drawImage(background, 0, 0,MeteorMadnessWindow.Width, MeteorMadnessWindow.Height, null);
		
		
		if(MeteorMadness.getIsRunning()){
			synchronized(spaceships){
				for (Spaceship ship:spaceships){
					ship.drawSpaceship(g2d);
	        	}
			}
			
			synchronized(meteorites){
				for (Meteorite meteor:meteorites){
	            	meteor.drawMeteorite(g2d);
	        	}
			}
			
			synchronized(stars){
				for (Star star:stars){
	            	star.drawStars(g2d);
	        	}
			}
			
			synchronized(lives){
				for (Life life:lives){
	            	life.drawHeart(g2d);
	        	}
			}
		}
		else{
	        Font f = new Font("Monospaced", Font.BOLD, 36);
	        g2d.setFont(f);
	        g2d.setColor(new Color(255,255,255));
	        g2d.drawString("Game Over", (MeteorMadnessWindow.Width*3)/10, 350);
	        
	        int highscore = Integer.parseInt(readScore());
	        if (Star.getScore() > highscore){
	        	 String convertToString = new String(""+Star.getScore());
	 	        writeScore(convertToString);
	        }
	       
		}
		
		
		Font f = new Font("Monospaced", Font.BOLD, 30);
        g2d.setFont(f);
        g2d.setColor(new Color(255,255,255));
        g2d.drawString(new String("Score: "+Star.getScore()), 0, 30);
		//repaint();
		
	}
	
}