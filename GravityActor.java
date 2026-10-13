
import mayflower.*;

public class GravityActor extends Actor
{
    private double velocityY;      // vertical speed
    private double gravity;        // gravity acceleration
    private double maxFallSpeed;   // max downward speed

    public GravityActor()
    {
        //sets the instance variables with values
        velocityY = 0;
        gravity = 0.2;
        maxFallSpeed = 20; 
    }

    /**
     * Check if actor is standing on a block
     */
    public boolean isOnGround()
    {
        boolean onGround = false; 
        setLocation(getX(), getY() + 1);
        if (isBlocked()) //checks if the Cat Actor is on the ground
            onGround = true;
        setLocation(getX(), getY() - 1); //changes the location of the Cat Actor to prevent it from falling through the ground
        return onGround;

    }
    /**
     * Takes care of gravity
     */
    public void act()
    {
        // Apply gravity
        velocityY += gravity;
        if (velocityY > maxFallSpeed) 
            velocityY = maxFallSpeed;

        if (getY() <0)
           {
               velocityY = 3;
            }
        // Move vertically
        setLocation(getX(), getY() + (int)velocityY);

        //setLocation(getX(),getY()+1);
        if(isBlocked())
        {
            setLocation(getX(), getY() - (int)velocityY); //resets location of the cat
            velocityY = 0;
        }
    }
    /**
     * checks if the Actor is touching the ground
     */ 
    public boolean isBlocked()
    {
        if(isTouching(Block.class))
            return true;
        return false;
    }
    /**
     * jump stuff
     */
    public void jump()
    {
        if (isOnGround())
        {
            velocityY = -9; // jump strength
        }
    }
    /**
     * checks if the Actor is falling
     */
    public boolean isFalling()
    {
        boolean isOnGround = isOnGround();
        return !isOnGround;
    }
}



