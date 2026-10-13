import jaco.mp3.player.A;
import mayflower.*;
/**
* Extension of GravityActor to incorporate gravity effect
* Uses a timor to sync animation speed to game framerate
*/

public class AnimatedActor extends GravityActor {
    private Animation animation;
    private Timer animationTimer;

    public AnimatedActor() {
        animationTimer = new Timer(109000000);
    }
    /**
     * replaces current animation
     */
    public void setAnimation(Animation a) {
        animation = a;
    }
    /**
     * applies gravity to movement
     * swithces to next frame after timer
     * increments and updates frame of the animation
     */
    @Override
    public void act() {
        super.act();
        if (animation!=null) {
            if (animationTimer.isDone()) {
                animationTimer.reset();
                MayflowerImage nextFrame = animation.getNextFrame();
                setImage(nextFrame);
            }
        }
    }
}
