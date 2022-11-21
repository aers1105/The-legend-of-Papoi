package entity;

import control.Keyboard;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    Keyboard keyboard;

    //Indicate where playe drop 
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    //Player´s healt.
    public int healt;

    public Player(GamePanel gp, Keyboard keyboard) {
        this.gp = gp;
        this.keyboard = keyboard;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        healt = 100;
        direction = "down";
    }

    //Load the sprites.
    public void getPlayerImage() {
        
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageNAme){
        UtilityTool uTool = new UtilityTool();
        
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/"+imageNAme+".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
            
        } catch (Exception e) {
        }
        return image;
        
    }
    
    //Steps and collision
    public void update() {
        int speed = 4;
        int speedSprite = 12;
        if (keyboard.upPressed == true || keyboard.downPressed == true
                || keyboard.leftPressed == true || keyboard.rightPressed == true) {

            if (keyboard.upPressed) {
                direction = "up";
                if (keyboard.runPressed) {
                    speedSprite = 5;
                    speed +=4;

                }
            } else if (keyboard.downPressed) {
                direction = "down";
                if (keyboard.runPressed) {
                    speedSprite = 5;
                    speed +=4;
                }

            } else if (keyboard.leftPressed) {
                direction = "left";
                if (keyboard.runPressed) {
                    speedSprite = 5;
                    speed +=4;
                }

            } else if (keyboard.rightPressed) {
                direction = "right";
                if (keyboard.runPressed) {
                    speedSprite = 5;
                    speed +=4;
                }

            }

            //Check tile collision
            collisonOn = false;
            int objIndex = gp.cChecker.checkObject(this, true);

            //Check object collision
            gp.cChecker.checkTile(this);
            pickUpObject(objIndex);
            //If collision is false, player can move 
            if (collisonOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }
            spriteCounter++;
            if (spriteCounter > speedSprite) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    
                    gp.obj[index] = null;
                    if(hasKey == 0){
                        gp.ui.showMessage("¿Una llave?");
                    }
                    else{
                        gp.ui.showMessage("¿Otra llave?");
                    }
                    hasKey++;
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[index] = null;
                        hasKey--;
                        
                    }
                    else{
                        gp.playSE(5);
                        gp.ui.showMessage("¿Hum?... Creo que necisito una llave");
                    }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
                
            }

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
