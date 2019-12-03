import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Main Menu of game
 *  - contains highest score, game title and button to start game
 * 
 * @author Group L 
 * @version 24 April 2017
 */

public class Menu extends JPanel {
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	
	private BufferedImage backgroundImage;
	private BufferedImage title;
	
	private JButton button;
	
	public Menu (){	
		createView();
	}
	
	/**
	 * Sets the menu graphical components windows objects will be viewed in.
     */
	public void createView(){
	  
		setLayout(null);
		setPreferredSize(new Dimension(MeteorMadnessWindow.Width,MeteorMadnessWindow.Height));
		
		Title();
	    importBackground();
	}
	
	/**
	 * Imports image of background 
     */
	public void importBackground(){
		try {
			 backgroundImage = ImageIO.read(new File("res/menubg1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * Imports image containg Title of game 
     */
	public void Title(){
		try {
			 title = ImageIO.read(new File("res/gameTitle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		
	/**
	 * Create and sets the postion of thye start button on the menu screen
     */
	public JButton startButton(){
		buttonX = (MeteorMadnessWindow.Width*3)/10;
		buttonY = (MeteorMadnessWindow.Height*2)/3;
		buttonWidth = (MeteorMadnessWindow.Width*4)/10;
		buttonHeight = MeteorMadnessWindow.Height/12;
		button = new JButton("Start Game");
		add(button);
		button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
		button.setBackground(new Color(90,200,255));
		
		return button;
	}
	
	/**
	 * Draws high score of game
     *  @param graphics The Graphics object on which the game high score is drawn
     */
	public void highScore(Graphics graphics){
		graphics.setColor(Color.white);
		graphics.setFont(new Font( "Monospaced", Font.BOLD, 36));
		graphics.drawString("HIGH SCORE", (MeteorMadnessWindow.Width*3)/10, 400);
		graphics.drawString(readScore(), (MeteorMadnessWindow.Width*3)/7, 450);
	}
	
	public void paintComponent(Graphics graphics){
	    super.paintComponent(graphics);
	    
		graphics.drawImage(backgroundImage, 0, 0,MeteorMadnessWindow.Width, MeteorMadnessWindow.Height, null);
		graphics.drawImage(title, 24, 200,500,46, null);
		highScore(graphics);
		readScore();
		
		repaint();
	}

}
