import mayflower.*;
public class GameStart extends World {
    public static long start;
    /**
     * sets background image of start screen
     */
    public GameStart() 
    {
        setBackground("img/BG/start.png");
        
    }
    /**
     * starts the timer and sets the game to the water level after enter key is pressed
     */
    public void act()
    {
        if(Mayflower.isKeyDown(Keyboard.KEY_ENTER))
        {
            //starts the timer
            start = System.currentTimeMillis();
            //sets the game to the water level after enter key is pressed
            Mayflower.setWorld(new Hallway());
        }
    }

}





