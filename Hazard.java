


import mayflower.*;
public class Hazard extends Actor {
    //private instance variables
    private Direction d;
    private boolean up;

    /**
     * Constructor creates Fireball (Hazard)
     */
    public Hazard() {
        MayflowerImage img = new MayflowerImage("img/Object/crate.png");
        //img.crop(24, 25, 50, 50);
        setImage(img);
        //instantiate private instance variables
        d = Direction.Up;
        up = false;
    }
    /**
     * Moves depending on Direction
     * Decreases lives if player touches it
     * Checks if Hazard reaches Platform
     */
    @Override
    public void act() {
        gravity();
        if (up) {
            setLocation(getX(), getY() - 2);
            if (isBlocked() || getY() < 5) {
                d = Direction.Down;
            }
        }
        else{
            setLocation(getX(), getY() + 2);
            if (isBlocked()) {
                d = Direction.Up;
            }
        }

        //Decreases lives if player touches
        if (isTouching(PlayerAnimatedActor.class)) {
            PlayerAnimatedActor a = getOneIntersectingObject(PlayerAnimatedActor.class);
            a.decreaseLives();
        }
    }

    /**
     * checks if Fireball reaches Platform
     */
    public boolean isBlocked() {
        return this.isTouching(Platform.class);
    }

    /**
     * flips gravity of Fireball depending on Direction
     */
    public void gravity() {
        if (d == Direction.Up){
            up = true;
        }
        else{
            up = false;
        }
    }
}

