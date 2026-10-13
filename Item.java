
import mayflower.*;

public class Item extends Actor {
    public Item(String fileName) {
        setImage(fileName);
    }
    /**
     * if player touches an item, increases score and removes the item
     */
    @Override
    public void act() {
        if (isTouching(PlayerAnimatedActor.class)) {
            PlayerAnimatedActor a = getOneIntersectingObject(PlayerAnimatedActor.class);
            World w = getWorld();
            w.removeObject(this);
            a.increaseScore(1);
        }
    }
}




