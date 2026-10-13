
import mayflower.*;
public class ProjectOne extends Mayflower
{

    //Constructor
    public ProjectOne()
    {
        //Create a window with 800x600 resolution
        super("Project 1", 800, 600);
    }

    public void init()
    {
        //Change this to true to run this program in fullscreen mode
        Mayflower.setFullScreen(false);
        World w =  new GameStart();
        Mayflower.setWorld(w);
    }
}

