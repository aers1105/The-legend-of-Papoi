package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import objects.Heart;
import objects.Key;
import objects.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font, arial_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDiaologue = "";
    public int commandNumber = 0;
    BufferedImage heart_full, heart_half, healt_blank, keyImage;

    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);

        Key key = new Key(gp);
        keyImage = key.image;

        Objects hearth = new Heart(gp);
        heart_full = hearth.image;
        heart_half = hearth.image2;
        healt_blank = hearth.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(arial_80);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.mainState) {
            drawMainScreen();
        }

        //Playstate
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            //drawTime();
            drawKeysAndTime();
            drawPlayerLife();

        }
        //Pausestate
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseGame();
        }
        //Dialogue state
        if (gp.gameState == gp.dioalogueState) {
            drawPlayerLife();

            g2.setFont(font);

            drawDialogueScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

    }

    public void drawPauseGame() {
        String text = "PAUSE";
        int x = getXForCenteredText(text);

        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);

    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    private void drawDialogueScreen() {
        int x = gp.tileSize * 2,
                y = gp.tileSize * 2,
                width = gp.screenWidth - (gp.tileSize * 4),
                height = gp.tileSize * 3;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDiaologue.split("\n")) {
            g2.drawString(line, x, y);
            y += 35;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 220);

        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    private void drawMainScreen() {
        g2.setColor(Color.lightGray);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(arial_80);
        String text = "Papoi Adventure";
        int x = getXForCenteredText(text),
                y = gp.tileSize * 3;
        //Shadow

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y = +gp.tileSize * 4;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        g2.setFont(font);

        text = "Nuevo Juego";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gp.tileSize + 13, y);
        }
        text = "Cargar Juego";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - gp.tileSize + 13, y);
        }
        text = "Salir";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNumber == 2) {
            g2.drawString(">", x - gp.tileSize + 13, y);
        }

    }

    private void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x, y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "Game Over";
        //Shadow
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize * 4;
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Reintentar";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - 42, y);

        }

        //Exit
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Reiniciar juego";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x + 55, y);

        if (commandNumber == 1) {
            g2.drawString(">", x + 15, y);
        }

    }

    private void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //dRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(healt_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //Draw current life
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    private void drawKeysAndTime() {
        g2.setFont(new Font("Arial", Font.PLAIN, 60));
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize + 50, gp.tileSize, gp.tileSize, null);

        g2.drawString("x " + gp.player.hasKey, gp.tileSize + 50, gp.tileSize * 2 + 25);

        //Time
        playTime += (double) 1 / 60;
        g2.drawString("Tiempo: " + dFormat.format(playTime), gp.tileSize * 13, 77);

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

    public void drawFinishGame() {
        if (gameFinished) {
            String text = "Encontraste un cofre";
            int textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth / 2 - textLenght / 2;
            int y = gp.screenHeight / 2 - (gp.tileSize);
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.yellow);

            text = "Felicidades";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth / 2) - textLenght / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * -1);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
    }


}
