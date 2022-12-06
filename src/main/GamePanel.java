package main;

import control.Keyboard;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import objects.Objects;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    public  int originalTileSize = 16; //16*16 tile.
    public final int scale = 4;
    public final int fps = 60;
    public final int tileSize = originalTileSize * scale; //64 X 64 PX TILES
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //wORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //System
    TileManager tileM = new TileManager(this);
    public Keyboard keyboard = new Keyboard(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public EventHandler hHandler = new EventHandler(this);

    //Entity and objects.
    public Player player = new Player(this, keyboard);
    public Objects[] obj = new Objects[10];
    public Entity npc[] = new Entity[10];
    public Entity[] monsters = new Entity[20];

    //Gamestate
    public int gameState;
    public SaveLoad saveLoad = new SaveLoad(this);
    public final int mainState = 0, playState = 1, pauseState = 2, dioalogueState = 3, gameOverState = 4, finishedState = 5;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        
        gameState = playState;
        gameState = mainState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double FPS = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / FPS;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {

                drawCount = 0;
                timer = 0;

            }

        }

    }

    public void update() {

        if (gameState == playState) {
            player.update();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (Entity monster : monsters) {
                if (monster != null) {
                    monster.update();
                }
            }

        }
        if (gameState == pauseState) {
            //nothing

        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //All entities its here.
        if (gameState == mainState) {
            ui.draw(g2);
        } else {
            //Tile draw
            tileM.draw(g2);

            //Objects draw, find who index are using and draw these.
            for (Objects obj1 : obj) {
                if (obj1 != null) {
                    obj1.draw(g2, this);
                }
            }
            //NPC
            for (Entity npc1 : npc) {
                if (npc1 != null) {
                    npc1.draw(g2);
                }
            }

            //Monsters
            for (Entity monster : monsters) {
                if (monster != null) {
                    monster.draw(g2);
                }
            }

            //Player  draw
            player.draw(g2);

            //Paint the UI
            ui.draw(g2);

        }
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    
    public void retry(){
        player.setDefaultPossitionAndSpeed();
        player.setDefaultLifeAndAttack();
        //player.setDefaultItems();
        aSetter.setMonster();
        //aSetter.setObject();
        //aSetter.setNPC();
        
    }
    public void reset(){
        player.setDefaultValues();
        aSetter.setMonster();
        aSetter.setNPC();
        aSetter.setObject();
        ui.playTime = 0;
        
    }
}
