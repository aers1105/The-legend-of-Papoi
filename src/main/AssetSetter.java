 package main;

import entity.NPC_OldMan;
import monster.GreenSlime;
import objects.Chest;
import objects.Door;
import objects.Key;
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    //Create the object that will appear on the screen.
    public void setObject(){
        
        //Creating two obj   
        
        gp.obj[0] = new Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        
        gp.obj[1] = new Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        
        gp.obj[2] = new Key(gp);
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;
        
        gp.obj[3] = new Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;
        
        gp.obj[4] = new Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        
        gp.obj[5] = new Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 23 * gp.tileSize;
        
        
        gp.obj[6] = new Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;
        
        
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
    
    public void setMonster(){
        gp.monsters[0] = new GreenSlime(gp);
        gp.monsters[0].worldX = gp.tileSize*23;
        gp.monsters[0].worldY = gp.tileSize*36;
        
        gp.monsters[1] = new GreenSlime(gp);
        gp.monsters[1].worldX = gp.tileSize*23;
        gp.monsters[1].worldY = gp.tileSize*37;
    }
}
