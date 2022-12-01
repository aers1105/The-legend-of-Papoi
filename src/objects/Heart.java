package objects;

import javax.imageio.ImageIO;
import main.GamePanel;

public class Heart extends Objects{
   GamePanel gp;

    public Heart(GamePanel gp) {
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/heart_blank.png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2= uTool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3=uTool.scaledImage(image3, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
