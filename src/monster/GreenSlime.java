/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;

/**
 *
 * @author angel
 */
public class GreenSlime extends Entity{

    private final String name;
    
    public GreenSlime(GamePanel gp) {
        super(gp);
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        type = 2;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultX = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/images/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/images/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/images/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/images/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/images/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/images/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/images/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/images/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }
    
    @Override
    public void setAction(){
        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
