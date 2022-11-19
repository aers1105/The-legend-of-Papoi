/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author angel
 */
public class Keyboard implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, swordPressed, bombPressed, enterPressed,runPressed;

    
    //Sin usar
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_O){
            swordPressed = true;
        }
        if(code == KeyEvent.VK_P){
            bombPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT){
            runPressed = true;
        }
        

    }

    @Override
    public void keyReleased(KeyEvent ke) {
         int code = ke.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        
        if(code == KeyEvent.VK_O){
            swordPressed = false;
        }
        if(code == KeyEvent.VK_P){
            bombPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT){
            runPressed = false;
        }
        
        
    }

}
