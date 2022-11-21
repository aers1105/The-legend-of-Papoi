package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import objects.Key;

public class UI {

    GamePanel gp;
    Font font, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 90);
        Key key = new Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        if (gameFinished) {
            String text = "Encontraste un cofre";
            int textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth /2 - textLenght/2;
            int y = gp.screenHeight / 2 -(gp.tileSize);
            g2.drawString(text, x, y);
            
            
            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            
            text = "Felicidades";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth /2)-textLenght/2;
            y = gp.screenHeight / 2 -(gp.tileSize *-1);
             g2.drawString(text, x, y);
            
            gp.gameThread = null;

        } else {
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, null);
            g2.drawString("x " + gp.player.hasKey, 80, 77);

            //Time
            playTime += (double)1/60;
            g2.drawString("Tiempo: "+dFormat.format(playTime), gp.tileSize*14, 77);
            
            
            //Message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.player.screenX, gp.player.screenY - 20);

                //Erase on screen the message after 2 seconds.
                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
