package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Key extends Objects {

    GamePanel gp;

    public Key(GamePanel gp) {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/key.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
