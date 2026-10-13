import mayflower.*;

public class Bullet extends Locker {
    private Direction d;
    private boolean fired = false;
    private boolean active = false;

    /**
     * sets bullet image and direction
     */
    public Bullet() {
        super("img/BG/invis.png");
        d = Direction.Right;
    }

    /**
     * checks if locker is open and fires bullet toward player
     */
    @Override
    public void act() {
        Locker locker = (Locker) getOneIntersectingObject(Locker.class);
        
        if (locker != null && locker.open && !fired) {
            fireTowardPlayer();
        }
        if (active) {
            moveBullet();
        }
    }

    /**
     * shoots bullet in the direction of the player
     */
    private void fireTowardPlayer() {
        PlayerAnimatedActor player = (PlayerAnimatedActor) getWorld().getObjects(PlayerAnimatedActor.class).get(0);
        if (player == null) return;
        
        if (player.getX() >= this.getX()) {
            d = Direction.Right;
        } else {
            d = Direction.Left;
        }
        setImage("img/Object/bullet.png");
        fired = true;
        active = true;
    }

    /**
     * moves the bullet and removes it after hitting something
     */
    private void moveBullet() {
        int speed = 1;
        if (d == Direction.Right) {
            setLocation(getX() + speed, getY());
        } else {
            setLocation(getX() - speed, getY());
        }
        if (isBlocked()) {
            getWorld().removeObject(this);
            return;
        }
        if (isTouching(PlayerAnimatedActor.class)) {
            PlayerAnimatedActor player = getOneIntersectingObject(PlayerAnimatedActor.class);
            player.decreaseLives();
            getWorld().removeObject(this);
        }
    }

    /**
     * checks if bullet is blocked by a platform or block
     */
    public boolean isBlocked() {
        return isTouching(Platform.class) || isTouching(Block.class);
    }
}