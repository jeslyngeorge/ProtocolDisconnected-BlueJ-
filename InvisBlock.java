import mayflower.*;

public class InvisBlock extends Actor {
    public InvisBlock(String fileName) {
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
        }
    }
}

