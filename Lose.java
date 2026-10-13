

import mayflower.*;

public class Lose extends World {
    /**
     * gets time the game was started and sets lose screen background
     */
    public Lose() 
    {
        long totalms = System.currentTimeMillis() - GameStart.start;
        double total = totalms/1000;
        setBackground("img/levels/lose.png");
        showText("You lasted:" + total + " seconds", 240, 430, Color.WHITE);
    }
    /**
     * restarts game when the enter key is pressed
     */
    public void act()
    {
        if(Mayflower.isKeyDown(Keyboard.KEY_ENTER))
        {
            Mayflower.setWorld(new GameStart());
        }
    }
}





