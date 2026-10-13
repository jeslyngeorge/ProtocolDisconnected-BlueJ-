import mayflower.*;
import java.util.Arrays;

public class Office extends World {
    // instance variables
    private PlayerAnimatedActor player;
    private String[][] tiles;
    private int startX;
    private int startY;
    private boolean transformed = false;
    /**
     * same player object passed between worlds
     */    
    public Office(PlayerAnimatedActor p) {
        // same player object passed between worlds
        player = p;
        setBackground("img/BG/office.png");
        tiles = new String[6][8];
        buildWorld();
        startX = 10;
        startY = 400;
        addPlayer();
    }
    /**
     * created ground
     */
    public void buildWorld() {
        for (int r = 0; r < tiles.length; r++) {
            Arrays.fill(tiles[r], "");
        }
        Arrays.fill(tiles[tiles.length - 1], "ground");

        //adds blocks where the array specifies ground
        for (int c = 0; c < tiles[tiles.length - 1].length; c++) {
            if (tiles[tiles.length - 1][c].equals("ground")) {
                addObject(new Block("img/levels/invis.png"), c*100, 500);
            }
        }

        tiles[4][0] = "start";
        
    }
    /**
     * identifies where mr.butler will be placed
     */
    public void addPlatforms() {
        //platform locations
        tiles[1][0] = "platform";
        
        //populates world with platforms
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c].equals("platform")) {
                    addObject(new Platform("img/Characters/butler1.png"), c*270, r*100);
                }
            }
        }
    }

    /**
     * adds player to world & updates player's world number
     */
    public void addPlayer () {
        addObject(player, startX, startY);
        player.setWorldNum(3);
    }
    /**
     * player's x position
     */
    public int getStartX() {
        return startX;
    }
    /**
     * player's y position
     */
    public int getStartY() {
        return startY;
    }
    /**
     * ending scene where player is turned into cabbage
     */
    public void act()
    {
        if(player.getX() > 350 && !transformed)
        {
            transformed = true;
            player.frozen = true;
            addPlatforms();
            player.setImage("img/Object/cabbage.png");
            showText("Mr. Butler turned you into a cabbage", 200, 100, Color.BLACK);
            setBackground("img/BG/office2.png");
        }
    }

}
