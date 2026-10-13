

import mayflower.*;

public class MovableAnimatedActor extends AnimatedActor {
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation idleLeft;
    private Animation fallRight;
    private Animation fallLeft;
    private Animation jumpRight;
    private Animation jumpLeft;
    private Animation crawlRight;
    private Animation crawlLeft;

    private Action currentAction;
    private Direction direction;

    private boolean isCrawling;
    private boolean isJumping;
    private boolean jumpKeyPressedLastFrame; // prevent holding space from double-jump
    
    protected int worldx = 400;
    protected boolean inverted = false;
    protected boolean frozen = false;

    public MovableAnimatedActor() {
        walkRight = null;
        idleRight = null;
        currentAction = null;
        direction = Direction.Right;
    }
    /**
     * Crawling, jumping, walking movement
     */
    public void act() {
        if(frozen)
        {
            setImage("img/Object/cabbage.png");
            return;
        }
        
        super.act();

        Action newAction = null;
        if (currentAction == null)
            currentAction = Action.IdleRight;

        int x = this.getX();
        int y = this.getY();
        int w = this.getWidth();
        int h = this.getHeight();

        //Crawling
        boolean shiftPressed = (Mayflower.isKeyDown(Keyboard.KEY_DOWN) || Mayflower.isKeyDown(Keyboard.KEY_LSHIFT));

        //Jumping: only once per key press and only from the ground
        boolean spacePressed = (Mayflower.isKeyDown(Keyboard.KEY_UP) || Mayflower.isKeyDown(Keyboard.KEY_SPACE));
        jumpKeyPressedLastFrame = spacePressed;

        // jump code
        if (spacePressed && (currentAction.equals(Action.IdleRight) || currentAction.equals(Action.IdleLeft)) && !jumpKeyPressedLastFrame) {
            if (direction == Direction.Right) 
            {
                jump();
                newAction = Action.JumpRight;
            }
            if (direction == Direction.Left) 
            {
                jump();
                newAction = Action.JumpLeft;
            }
            setLocation(this.getX(), this.getY() - 7);
            while (this.isBlocked()) setLocation(getX(), getY() + 1);
        }
        // when player presses down or shift combined with right movement, they crab walk aka "crawl"    
        else if (shiftPressed && (x > 0 || x < 800)) {
            newAction = Action.CrawlRight;
            if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && (x + w) < 799) 
            {
                if(inverted)
                {
                    setLocation(x - 5, y);
                    worldx -= 5;
                }
                else
                {
                    setLocation(x + 5,  y);
                    worldx += 5;
                }
                if (super.isBlocked())
                    setLocation(x - 2, y);
                newAction = Action.CrawlRight;
                direction = Direction.Right;
            }
            if (Mayflower.isKeyDown(Keyboard.KEY_LEFT)  && x > 0) 
            {
                if(inverted)
                {
                    setLocation(x + 5, y);
                    worldx += 5;
                }
                else
                {
                    setLocation(x - 5,  y);
                    worldx -= 5;
                }
                if (super.isBlocked())
                    setLocation(x + 2, y);
                newAction = Action.CrawlLeft;
                direction = Direction.Left;
            }
        }

        //moves player to the right
        else if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && (x + w) < 799) {
            if(inverted)
                {
                    setLocation(x - 5, y);
                    worldx -= 5;
                }
                else
                {
                    setLocation(x + 5,  y);
                    worldx += 5;
                }
            if (super.isBlocked())
                setLocation(x - 2, y);
            newAction = inverted? Action.WalkLeft : Action.WalkRight;
            direction = inverted? Direction.Left : Direction.Right;
        }

        //moves player to the left
        else if ((Mayflower.isKeyDown(Keyboard.KEY_LEFT) && x > 0)) {
            if(inverted)
                {
                    setLocation(x + 5, y);
                    worldx += 5;
                }
                else
                {
                    setLocation(x - 5,  y);
                    worldx -= 5;
                }
            if (super.isBlocked())
                setLocation(x + 2, y);
            newAction = inverted? Action.WalkRight : Action.WalkLeft;
            direction = inverted? Direction.Right : Direction.Left;
        }

        else if (this.isFalling()) {
            if (direction == Direction.Right)
                newAction = Action.FallRight;
            else newAction = Action.FallLeft;
        }
        else if (direction == Direction.Right)
            newAction = Action.IdleRight;
        else
            newAction = Action.IdleLeft;

        if (newAction != null && !newAction.equals(currentAction)) {
            if (newAction == Action.IdleRight)
                setAnimation(idleRight);
            else if (newAction == Action.IdleLeft)
                setAnimation(idleLeft);
            else if (newAction == Action.WalkRight) {
                setAnimation(walkRight);
            }
            else if (newAction == Action.WalkLeft) {
                setAnimation(walkLeft);
            }
            else if (newAction == Action.FallRight) {
                setAnimation(fallRight);
            }
            else if (newAction == Action.FallLeft) {
                setAnimation(fallLeft);
            }
            else if (newAction == Action.CrawlRight) {
                setAnimation(crawlRight);
            }
            else if (newAction == Action.CrawlLeft) {
                setAnimation(crawlLeft);
            }
            currentAction = newAction;
        }

        super.act();
    }
    /**
     * set animation
     */
    public void setAnimation(Animation a) {
        super.setAnimation(a);
    }
    /**
     * set right walk animation
     */
    public void setWalkRightAnimation(Animation a) {
        walkRight = a;
    }
    /**
     * set left walk animation
     */
    public void setWalkLeftAnimation(Animation a) {
        walkLeft = a;
    }
    /**
     * set idle right animation
     */
    public void setIdleRightAnimation(Animation a) {
        idleRight = a;
    }
    /**
     * set idle left animation
     */
    public void setIdleLeftAnimation(Animation a) {
        idleLeft = a;
    }
    /**
     * set fall right animation
     */
    public void setFallRightAnimation(Animation a) {
        fallRight = a;
    }
    /**
     * set fall left animation
     */
    public void setFallLeftAnimation(Animation a) {
        fallLeft = a;
    }
    /**
     * set jump right animation
     */
    public void setJumpRightAnimation(Animation a) {
        jumpRight = a;
    }
    /**
     * set jump left animation
     */
    public void setJumpLeftAnimation(Animation a) {
        jumpLeft = a;
    }
    /**
     * set crawl right animation
     */
    public void setCrawlRightAnimation(Animation a) {
        crawlRight = a;
    }
    /**
     * set crawl left animation
     */
    public void setCrawlLeftAnimation(Animation a) {
        crawlLeft = a;
    }
}





