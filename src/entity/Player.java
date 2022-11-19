package entity;

import control.Keyboard;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    Keyboard keyboard;

    //Indicate where playe drop 
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

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
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_right_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Walking_sprites/boy_down_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    hasKey++;
                    gp.obj[index] = null;
                    
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[index] = null;
                        hasKey--;
                        System.out.println("key: "+ hasKey);
                    }
                    else{
                        gp.playSE(5);
                        System.out.println("You need a key to open the door");
                    }
                    break;
                case "Chest":

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
