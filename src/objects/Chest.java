/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author angel
 */
public class Chest extends Objects{
    public Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/Objetos/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
