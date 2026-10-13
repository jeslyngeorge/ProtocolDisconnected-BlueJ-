

import mayflower.*;

public class Portal extends Actor {
    //private instance variable
    private Timer t;

    /**
     * instantiate private instance variable and set portal image
     */
    public Portal (String fileName) {
        t = new Timer(1000);
        super.setImage(fileName);
    }
    /**
     * a short lag before moving player to next world
     */
    public void act() {
        if (isTouching(PlayerAnimatedActor.class))
        {
            t.reset();
            PlayerAnimatedActor player = getOneIntersectingObject(PlayerAnimatedActor.class);
            Mayflower.setWorld(new Office(player));
        }
    }
}



