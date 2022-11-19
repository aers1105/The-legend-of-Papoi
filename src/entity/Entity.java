package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author angel
 */
public class Entity {
    public int worldX,worldY,speed;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    
    public String direction;
    
    //This to can change the sprite.
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    
    
    //Collision
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisonOn = false;
    
    
    
    
    
    
}
