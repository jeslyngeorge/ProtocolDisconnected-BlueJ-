import java.util.Arrays;
import mayflower.*;
import java.util.Stack;
/**
 * Takes care of animations, checkpoint coordinates after respawning, score/lives, and worlds
 */
public class PlayerAnimatedActor extends MovableAnimatedActor {
    private Animation walkR;
    private Animation walkL;
    private Animation idleR;
    private Animation idleL;
    private Animation fallR;
    private Animation fallL;
    private Animation jumpR;
    private Animation jumpL;
    private Animation crawlR;
    private Animation crawlL;
    
    private Stack<int[]> checkpoints = new Stack<>();
    //private int cuurrentCheckpoint = 0;
    
    private int score;
    private int lives;
    private int worldNum;
    /**
     * Takes care of animations
     */
    public PlayerAnimatedActor() {
        score = 0;
        lives = 10;
        worldNum = 0;
        
        checkpoints.push(new int[] {100, 100});
        checkpoints.push(new int[] {1000, 150});
        checkpoints.push(new int[] {1600, 250});
        checkpoints.push(new int[] {3000, 150});
        checkpoints.push(new int[] {800, 150});
        checkpoints.push(new int[] {2000, 150});
        checkpoints.push(new int[] {100, 150});
        checkpoints.push(new int[] {100, 150});
        
        
        String[] wRFrames = new String[3];
        for (int i = 0; i < wRFrames.length; i++) {
            wRFrames[i] = "img/Characters/run (" + (i) + ").png";
        }
        walkR = new Animation(50, wRFrames);
        String[] wLFrames = Arrays.copyOf(wRFrames, 3);
        walkL = new Animation(50, wLFrames);

        String[] iRFrames = new String[10];
        for (int i = 0; i < iRFrames.length; i++) {
            iRFrames[i] = "img/Characters/idle.png";
        }
        idleR = new Animation(50, iRFrames);
        String[] iLFrames = Arrays.copyOf(iRFrames, 10);
        idleL = new Animation(50, iLFrames);

        String[] fRFrames = new String[8];
        for (int i = 0; i < fRFrames.length; i++) {
            fRFrames[i] = "img/Characters/fall (0).png";
        }
        fallR = new Animation(50, fRFrames);
        
        String[] fLFrames = new String[8];
        for (int i = 0; i < fLFrames.length; i++) {
            fLFrames[i] = "img/Characters/fall (1).png";
        }
        fallL = new Animation(50, fLFrames);

        String[] jRFrames = new String[3];
        for (int i = 0; i < jRFrames.length; i++) {
            jRFrames[i] = "img/Characters/jump (" + (i) + ").png";
        }
        jumpR = new Animation(50, jRFrames);
        String[] jLFrames = Arrays.copyOf(jRFrames, 3);
        jumpL = new Animation(50, jLFrames);
        
        String[] cRFrames = new String[2];
        for (int i = 0; i < cRFrames.length; i++) {
            cRFrames[i] = "img/Characters/crawl (" + (i + 1) + ").png";
        }
        crawlR = new Animation(50, cRFrames);
        String[] cLFrames = Arrays.copyOf(cRFrames, 2);
        crawlL = new Animation(50, cLFrames);


        walkL.mirrorHorizontally();
        idleL.mirrorHorizontally();
        jumpL.mirrorHorizontally();
        crawlL.mirrorHorizontally();

        walkR.scale(100, 87);
        walkL.scale(100, 87);
        idleR.scale(100, 87);
        idleL.scale(100, 87);
        fallR.scale(100, 87);
        fallL.scale(100, 87);
        jumpR.scale(100, 87);
        jumpL.scale(100, 87);
        crawlR.scale(100, 87);
        crawlL.scale(100, 87);

        walkR.setBounds(18, 5, 54, 80);
        walkL.setBounds(28, 5, 54, 80);
        idleR.setBounds(18, 5, 54, 80);
        idleL.setBounds(28, 5, 54, 80);
        fallR.setBounds(18, 5, 54, 80);
        fallL.setBounds(13, 5, 59, 80);
        jumpR.setBounds(18, 5, 54, 80);
        jumpL.setBounds(28, 5, 54, 80);
        crawlR.setBounds(10, 5, 64, 80);
        crawlL.setBounds(18, 5, 64, 80);

        setWalkRightAnimation(walkR);
        setWalkLeftAnimation(walkL);
        setIdleRightAnimation(idleR);
        setIdleLeftAnimation(idleL);
        setFallRightAnimation(fallR);
        setFallLeftAnimation(fallL);
        setJumpRightAnimation(jumpR);
        setJumpLeftAnimation(jumpL);
        setCrawlRightAnimation(crawlR);
        setCrawlLeftAnimation(crawlL);

        setAnimation(idleR);
    }
    /**
     * Takes care of animations and worlds
     */
    @Override
    public void act() {
        super.act();
        updateText();
        // if the player loses all their lives, sets the world to the 'lose' screen
        if (lives == 0) {
            World world = getWorld();
            world.removeObject(this);
            Mayflower.setWorld(new Lose());
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_SPACE) || Mayflower.isKeyDown(Keyboard.KEY_UP)) {
             jump();}
    }
    /**
     * Takes care of score
     */
    public void increaseScore(int amount) {
        score += amount;
    }
    /**
     * Takes care of score
     */
    public int getScore() {
        return score;
    }
    /**
     * Takes care of lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * Takes care of lives
     */
    public void decreaseLives() {
        lives--;
        inverted = !inverted;
        
        if(checkpoints.size() > 1)
        {
            checkpoints.pop();
        }
        World world = getWorld();
        if (world instanceof Hallway)
        {
            ((Hallway) world).resetTo(checkpoints.peek()[0], checkpoints.peek()[1]);
        }
    }
    /**
     * Takes care of worlds
     */
    public void setWorldNum(int a) {
        worldNum = a;
    }
    /**
     * Takes care of score/lives
     */
    private void updateText() {
        World w = getWorld();
        w.removeText(10, 300);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.WHITE);
    }
}






