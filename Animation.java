import mayflower.MayflowerImage;
/**
*Manages the sequence of images in a cycle
*/
public class Animation {
    private MayflowerImage[] frames;
    private int frameRate;
    private int currentFrame;
    
    /**
    * instanciates speed of animation
    */
    public Animation(int fRate, String[] f) {
        frameRate = fRate;
        frames = new MayflowerImage[f.length];
        for (int i = 0; i < f.length; i++) {
            frames[i] = new MayflowerImage(f[i]);
        }
        currentFrame = 0;
    }
    
    /**
    * return the framrate
    */
    public int getFrameRate() {
        return frameRate;
    }
    
    /**
    * loops back to first frame automatically after last frame
    */
    public MayflowerImage getNextFrame() {
        MayflowerImage img = frames[currentFrame];
        if (currentFrame == frames.length-1) currentFrame = 0;
        else currentFrame++;
        return img;
    }
    
    /**
    * resizes all animation frames
    */
    public void scale(int w, int h) {
        for (MayflowerImage frame : frames) {
            frame.scale(w, h);
        }
    }
    
    /**
    * Sets the opacity of the character
    */
    public void setTransparency(int percent) {
        for (MayflowerImage frame : frames) {
            frame.setTransparency(percent);
        }
    }
    
    /**
    * method to flips animation frames horizontally
    * used for reusing right-facing animation for left-facing movement
    */
    public void mirrorHorizontally() {
        for (MayflowerImage frame : frames) {
            frame.mirrorHorizontally();
        }
    }
    
    /**
    * Crops all animation frames
    */
    public void setBounds(int x, int y, int w, int h) {
        for (MayflowerImage frame : frames) {
            frame.crop(x, y, w, h);
        }
    }

}
