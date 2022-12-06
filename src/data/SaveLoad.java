package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() throws FileNotFoundException, IOException {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.txt")));

            DataStorage ds = new DataStorage();
            ds.life = gp.player.life;
            ds.maxLife = gp.player.maxLife;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.hasKey = gp.player.hasKey;
            ds.playTime = gp.ui.playTime;
            //Write datastorage.
            oos.writeObject(ds);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void load() throws ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.txt")));
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.hasKey = ds.hasKey;
            gp.player.life = ds.life;
            gp.player.maxLife = ds.maxLife;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.ui.playTime = ds.playTime;
            
            
        } catch (IOException e) {
        }
    }

}
