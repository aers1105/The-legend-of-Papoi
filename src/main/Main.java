    package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    
    public Main() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Zelda");
        window.setIconImage(new ImageIcon(getClass().getResource("/images/menu/icon.png")).getImage());
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    
    
    
    }
    
}
