import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Swing window for the game.
 */

public class MeteorMadnessWindow extends JFrame implements ActionListener, KeyListener {
	//Fields 
	public static final int Width = 1000;
	public static final int Height =1200;
	
	private JPanel cards;
	private CardLayout cl;
	private MeteorMadnessPanel mmPanel;
	private Menu mmMenu;
	
	private static volatile boolean gameState;
	

	//Constructor
	/**
     * Create a game window with animation viewer
     */
	public MeteorMadnessWindow() {
		gameState= false;
		createPanels();
		createView();
		playMenuSound();

	}
	
	public static void setGameState(boolean gameState) {
		MeteorMadnessWindow.gameState = gameState;
	}
	
	public static boolean getGameState(){
		return gameState;
	}
	
	public void createView(){
		getContentPane().add(cards);
		setTitle("Meteor Madness");
		setResizable(true);
		setLocationByPlatform(true); 					 // Lets platform decide where to open window
		pack();
		
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//**This line...
		setVisible(true); // makes windows visible
		
	}
	
	public void createPanels(){
		JButton button = new JButton();
		cards = new JPanel();
		mmMenu = new Menu();
		mmPanel = new MeteorMadnessPanel();
		cl = new CardLayout();
		cards.setLayout(cl);
		cards.add(mmMenu, "menu");
		cards.add(mmPanel, "panel");
		cl.show(cards, "menu");
		
		button = mmMenu.startButton();
		button.setFocusable(false);
		button.addActionListener(this);
		mmMenu.add(button);
		
	}
	
	 /**Adds a meteorite
     * @param meteorite The meteor to add
     */
	public void add(Meteorite meteorite){
	  mmPanel.add(meteorite);
	}
	
	/**Adds a star
     * @param stars The star to add
     */
	public void add(Star s){
		mmPanel.add(s);  
	} 
	
	/**Adds a spaceship
     * @param spaceship The spaceship to add
     */
	public void add(Spaceship spaceship){
		mmPanel.add(spaceship); 
	} 
	
	/**Removes a meteor
     * @param meteorite The meteor to remove
     */
	public void remove(Meteorite meteorite){
		  
	}
	
	/**Removes a star
     * @param stars The star to remove
     */
	public void remove(Star stars){
		  
	} 
	
	/**Removes a spaceship 
     * @param spaceship The spaceship to remove
     */
	public void remove(Spaceship spaceship){
		  
	}
	
	public void playGameSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Goodwill\\Desktop\\space_jam.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void playMenuSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res\\menu.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}


	public void actionPerformed(ActionEvent e) {
		setGameState(true);
		cl.show(cards, "panel");
		//playGameSound();
		
	}
}
