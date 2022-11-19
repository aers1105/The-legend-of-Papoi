package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author angel
 */
public class Door extends Objects{
    public Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true; 
        
    }
}
