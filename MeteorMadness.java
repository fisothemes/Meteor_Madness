import java.util.Random;

/**
 * This  class runs the program
 * 
 * @author Group L 
 * @version 24 April 2017
 */
public class MeteorMadness {
    
     //fields
    private MeteorMadnessWindow mmWindow;
    private Meteorite[] meteors;
    private Star[] stars;
    private Life[] heart;
    
    private static boolean isRunning;
    private static int score;
        
    
    /**
     * Main of the program
     */
    public static void main(String[] args) throws InterruptedException{
        new MeteorMadness();        
    }

    //Constructor
    /**
     * This runs the actions of the object in the game
     */
    public MeteorMadness() throws InterruptedException{
        
        //Creates new window
        mmWindow = new MeteorMadnessWindow(540, 740);
        
        //Runs game
        isRunning = true;
        
        //Adds player and objects to screen
        createSprites();
        createGameLoop();
    
    }
    
    
    /**
     * Creates Sprites and adds Spaceship and Hearts to Window
     *  
     *  Sprites are: Meteorite, Stars, Life and Spaceship
     *  
     *  -Adds Spaceship
     *  -Adds Hearts that represent life and sets their position 
     *  
     */
    public void createSprites(){
        //Adds spaceship to window
        Spaceship Player = new Spaceship();
        mmWindow.add(Player);
        
        //Array List of components to be added to window
       meteors = new Meteorite[10]; 
       stars = new Star[5];
       heart = new Life[3];
        
         //Adds hearts to window and sets their position
        for (int j = 0; j<heart.length;j++){
            heart[j] = new Life();
            mmWindow.add(heart[j]);
            }
        //Setting positions of hearts
        heart[2].setPosition(MeteorMadnessWindow.Width-heart[2].getWidth()-2,0);
        heart[1].setPosition(heart[2].getX()-heart[1].getWidth()-2,0);
        heart[0].setPosition(heart[1].getX() - heart[0].getWidth() - 2,0);
        
         //Assigns each component in the meteors and stars Array List
        for (int i = 0; i<meteors.length;i++){
            meteors[i] = new Meteorite();
            }
        for (int i = 0; i<stars.length;i++){
            stars[i] = new Star();
            }
    }
    
     /**
     * Creates Game Loop - this runs the game's behaviors in a loop until certain contitions are met 
     * 
     *  -Adds Stars and Meteorites to Window
     *  -Removes all Sprites
     *  
     */
    
    public void createGameLoop() throws InterruptedException{
        int livesLost = 0;  
        MeteorMadness.score = 0;
        boolean addingObjects = true; //Checks if components are being added to window
        while(getIsRunning()){ //Constantly checks if MeteorMadnessWindow.getGameState() is true or false
            
            while(MeteorMadnessWindow.getGameState()){ //Adding components to Window
                
                for(int j = 0; j<meteors.length;j++){
                    if (addingObjects)mmWindow.add(meteors[j]);
                }
                for(int j = 0; j<stars.length;j++){
                    if (addingObjects)mmWindow.add(stars[j]);
                }
                addingObjects = false; // finished adding components
                MeteorMadnessWindow.setGameState(false);
            }
  
            for(int j = 0; j<meteors.length;j++){ //remove hearts if collision occurs
                if(meteors[j].collision()&& !addingObjects && livesLost<3){
                    mmWindow.remove(heart[livesLost]);
                    Thread.sleep(20);
                    livesLost++;
                }
                
            }
            if(livesLost == 3)setIsRunning(false);
        }
        
        for(int j = 0; j<meteors.length;j++){
            mmWindow.remove(meteors[j]);
            if(j<stars.length)mmWindow.remove(stars[j]);
        }
    }
    
    /**Checks if game loop is still running
     * @return isRunning - Is the game still running? True or False
     */
    
    public static boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Sets the state of the game loop
     * @param  isRunning - Want the game loop to run? True or False
     */
    public static void setIsRunning(boolean isRunning) {
        MeteorMadness.isRunning = isRunning;
    }
    
   

}