import mayflower.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Hallway extends World {
    private PlayerAnimatedActor player;
    private String[][] tiles;
    private boolean t;
    private int startX;
    private int startY;
    protected int xOffset = 0; // Tracks total scroll distance
    private boolean x;
    private Portal p1;
    private Locker l;
    private Map<Integer, Integer> zoneProgress = new HashMap<>();
    public Hallway()
    {
        setBackground("img/BG/hallway.png");
        tiles = new String[6][40];
        buildWorld();
        zoneProgress.put(0, 0);
        zoneProgress.put(1, 10);
        zoneProgress.put(2, 20);
        zoneProgress.put(3, 30);
        zoneProgress.put(4, 40);
        zoneProgress.put(5, 50);
        zoneProgress.put(6, 60);
        zoneProgress.put(7, 70);
        zoneProgress.put(8, 80);
        zoneProgress.put(9, 90);
        zoneProgress.put(10, 100);
        startX = 400;
        startY = 300;
        addItems();
        addPlayer();
        player.setWorldNum(1);
        t = true;
    }
     /**
    * Uses a 2d Arracy to populate world
    * each index is 100x100 area in world
    */
    public void buildWorld() {
        //ground blocks
        for (int r = 0; r < tiles.length; r++) {
            Arrays.fill(tiles[r], "");
        }
        
        Arrays.fill(tiles[tiles.length - 1], "ground");

        tiles[0][6] = "fireball";
        tiles[0][20] = "fireball";
        tiles[0][13] = "fireball";
        tiles[0][33] = "fireball";
        
        
        tiles[tiles.length - 1][7] = "invis";
        tiles[tiles.length - 1][8] = "invis";
        tiles[tiles.length - 1][9] = "invis";
        tiles[tiles.length - 1][10] = "invis";
        
        tiles[tiles.length - 1][14] = "";
        tiles[tiles.length - 1][15] = "";
        tiles[tiles.length - 1][16] = "";
        tiles[tiles.length - 1][17] = "";
        

        //tiles[tiles.length - 2][3] = "locker";
        tiles[tiles.length - 2][11] = "locker";
        tiles[tiles.length - 2][20] = "locker";
        tiles[tiles.length - 2][29] = "locker";
        //tiles[tiles.length - 2][37] = "locker";
        
        Portal p1 = new Portal("img/Object/vent.png");
        addObject(p1, 3700, 100);
        
        
        for (int c = 0; c < tiles[tiles.length - 1].length; c++) {
            if (tiles[tiles.length - 1][c].equals("invis")) {
                addObject(new InvisBlock("img/levels/watertile.png"), c*100, 500);
                //addObject(new Platform("img/levels/watertile.png"), c*100, 500);
            }

            if (tiles[tiles.length - 1][c].equals("ground")) {
                addObject(new Platform("img/levels/watertile.png"), c*100, 500);
            }
            if (tiles[tiles.length - 1][c].equals("")) {
                //addObject(new Platform("img/levels/watertile.png"), c*100, 500);
                addObject(new Platform("img/levels/invis.png"), c*100, 500);
            }
            if(tiles[tiles.length - 2][c].equals("locker")){
                addObject(new Locker("img/Object/l3.png"), c*100, 305);
                addObject(new Bullet(), (c*100) + 50, 370 + (int)(Math.random() * 100));
            }

            if (tiles[0][c].equals("fireball")) {
                    //change to item img
                    addObject(new Hazard(), c*100 + 25, 100 + (int)(Math.random() * 300));
            }

        }

    }
    /**
     * adds crates (collectible items)
     */
    public void addItems() {
        //crate (collectible item)    
        tiles[0][1] = "item";
        tiles[1][10] = "item";
        tiles[2][20] = "item";
        tiles[3][30] = "item";
        tiles[1][5] = "item";
        
        for(int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[0].length; c++)
            {
                if (tiles[r][c].equals("item")) {
                    addObject(new Item("img/Object/Crate.png"), c*100 + 9, r * 100 + 15);
                }
            }
        }
        
    }
    /**
     * adds player onto screen
     */
    public void addPlayer () {
        player = new PlayerAnimatedActor();
        addObject(player, startX, startY);
    }

    /**
     * returns players starting x position
     */
    public int getStartX() {
        return startX;
    }

    /**
     * returns players starting y position
     */
    public int getStartY() {
        return startY;
    }
    /**
     * takes care of scrolling and disappearing blocks
     */
    public void act()
    {
        int screenWidth = 800;
        int playerX = player.getX();
        int playerY = player.getY();
        int zone = 0;
        
        if (xOffset > 3000) zone = 10;
        else if (xOffset > 2600) zone = 9;
        else if (xOffset > 2500) zone = 8;
        else if (xOffset > 2000) zone = 7;
        else if (xOffset > 1700) zone = 6;
        else if (xOffset > 1050) zone = 5;
        else if (xOffset > 1000) zone = 4;
        else if (xOffset > 500) zone = 3;
        else if (xOffset > 400) zone = 2;
        else if (xOffset > 200) zone = 1;
        int progress = zoneProgress.get(zone);
        
        removeText(10, 55);
        showText("Progress: " + progress + "%", 10, 65, Color.GREEN);
        
        if (playerX > screenWidth / 2) {
            int shift = playerX - (screenWidth / 2);
            // only scroll right if we haven't hit the end
            if (xOffset + shift <= (tiles[0].length * 100) - screenWidth) {
                xOffset += shift;
                player.setLocation(player.getX() - shift, player.getY());
                for (Actor actor : getObjects(Actor.class)) {
                    if (actor != player) {
                        actor.setLocation(actor.getX() - shift, actor.getY());
                    }
                }
            }
        }
    
        if (playerX < screenWidth / 2) {
            int shift = (screenWidth / 2) - playerX;
            // only scroll left if we haven't hit the beginning
            if (xOffset - shift >= 0) {
                xOffset -= shift;
                player.setLocation(player.getX() + shift, player.getY());
                for (Actor actor : getObjects(Actor.class)) {
                    if (actor != player) {
                        actor.setLocation(actor.getX() + shift, actor.getY());
                    }
                }
            }
        }
        if (playerY > 600)
        {
            player.decreaseLives();
        }
        
        if (player.getLives() <= 8 && t)
        {
            addObject(new Platform("img/levels/watertile.png"), 700 - xOffset, 500);
            addObject(new Platform("img/levels/watertile.png"), 800 - xOffset, 500);
            addObject(new Platform("img/levels/watertile.png"), 900 - xOffset, 500);
            addObject(new Platform("img/levels/watertile.png"), 1000 - xOffset, 500);
            t = false;
        }
        
        if(xOffset > 3000)
        {
            showText("press a key to", 400, 130, Color.WHITE);
            showText("reach the vents", 400, 160, Color.WHITE);

        }
        
        //switches to next level after accumulating enough score
        if(player.getScore() >= 2 && Mayflower.isKeyDown(Keyboard.KEY_P))
        {
            addObject(new Platform("img/Object/Crate.png"), 3600 - xOffset, 350);
            addObject(new Platform("img/Object/Crate.png"), 3500 - xOffset, 400);
            //p1.nextWorld();
        }
        
    }
    /**
     * reset scroll by shifting everything back by xOffset and replace player
     */
    public void resetTo(int spawnX, int spawnY) {
        int shift = xOffset;
        for (Actor actor : getObjects(Actor.class)) {
            if (actor != player) {
                actor.setLocation(actor.getX() + shift, actor.getY());
            }
        }
        xOffset = 0;
        player.setLocation(spawnX, spawnY);
    }
}

















