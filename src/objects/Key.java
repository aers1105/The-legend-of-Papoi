package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Key extends Objects{

    public Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
