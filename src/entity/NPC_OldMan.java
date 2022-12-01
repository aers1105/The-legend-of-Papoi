package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/images/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/images/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/images/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/images/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/images/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/images/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/images/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/images/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hola, muchacho.";
        dialogues[1] = "¿Sabias que en esta isla hay un cofre con un tesoro?";
        dialogues[2] = "Lo usé para ser un gran mago, pero ahora...\nsoy un poco más viejo.";
        dialogues[3] = "Buena suerte.";
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(50) + 1;
//            if (i <= 25) {
//                direction = "up";
//            }
            if (i < 25 && i <= 50) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
//            if (i > 50 && i <= 75) {
//                direction = "left";
//            }
//            if (i > 75 && i <= 100) {
//                direction = "right";
//            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
