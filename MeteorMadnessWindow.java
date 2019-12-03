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
 *  The Swing window for the game.
 *
 * @author Group L
 * @version 24 April 2017
 */

public class MeteorMadnessWindow extends JFrame implements ActionListener, KeyListener {
    //Fields
    public static int Width;
    public static int Height;

    private JPanel cards;
    private CardLayout cl;
    private MeteorMadnessPanel mmPanel;
    private Menu mmMenu;

    private static volatile boolean gameState;


    //Constructor
    /**
     * Create a game window with animation viewer
     */
    public MeteorMadnessWindow(int width, int height) {
        this.Width = width;
        this.Height = height;
        gameState= false;
        createPanels();
        createView();
        playMenuSound();

    }

    /**
     * Sets the state of the game
     * @param gameState
     */
    public static void setGameState(boolean gameState) {
        MeteorMadnessWindow.gameState = gameState;
    }

    /**Gets the state of the spaceship
     * @return gamestate
     */
    public static boolean getGameState(){
        return gameState;
    }


    /**
     * Creates the windows objects will be viewed in.
     * Adds Sets focus for listeners on window
     * detemines what happens when window is closed
     */
    public void createView(){

        getContentPane().add(cards);
        setTitle("Meteor Madness");
        setResizable(true);
        setLocationByPlatform(true);                     // Lets platform decide where to open window
        pack();

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // makes windows visible
        createBufferStrategy(3);

    }


    /**
     * Adds the panels (Menu and MeteoriteMAdnessPanel)
     * Adds button for listener to work on
     */
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

        //adding actionlister to button
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
     * @param star The star to add
     */
    public void add(Star star){
        mmPanel.add(star);
    }

    /**Adds a heart
     * @param life The heart to add
     */
    public void add(Life life){
        mmPanel.add(life);
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
         mmPanel.remove(meteorite);
    }

    /**Removes a star
     * @param stars The star to remove
     */
    public void remove(Star stars){
       mmPanel.remove(stars);
    }

    /**Removes a heart
     * @param life The heart to remove
     */
    public void remove(Life life){
         mmPanel.remove(life);
    }

    /**Removes a spaceship
     * @param spaceship The spaceship to remove
     */
    public void remove(Spaceship spaceship){
         mmPanel.remove(spaceship);
    }


    /**
     * Plays sound when staring the game.
     */
    public static void playMenuSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/menu.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //LISTENERS HANDLERS

    public void actionPerformed(ActionEvent e) {
        setGameState(true);
        cl.show(cards, "panel");
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        double speed = 8; //speed of Spaceship

        if (key == KeyEvent.VK_LEFT){
                Spaceship.setSpeed(-speed);
        }
        if (key == KeyEvent.VK_RIGHT){
                Spaceship.setSpeed(speed);
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){
            Spaceship.setSpeed(0);
        }
        if (key == KeyEvent.VK_RIGHT && Spaceship.getX() + Spaceship.getWidth() < MeteorMadnessWindow.Width){
            Spaceship.setSpeed(0);
        }

    }

    public void keyTyped(KeyEvent e) {
        // Nothing to see here
    }
}
