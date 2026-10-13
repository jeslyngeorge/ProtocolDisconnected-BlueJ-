
import mayflower.*;

public class Locker extends Actor {
    public boolean open = false;
    /**
     * sets locker inital 'closed' appearance
     */
    public Locker (String fileName) {
        setImage(fileName);
    }
    /**
     * flips image based on if player is touching the locker 
     */
    @Override   
    public void act() {
        if (touching()) {
            setImage("img/Object/l3B.png");
            open = true;
        }
        else
        {
            setImage("img/Object/l3.png");
            open  = false;
        }
    }
    /**
     * determines if player is touching the locker
     */
    public boolean touching()
    {
        return isTouching(PlayerAnimatedActor.class);
    }
}



