package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class Keyboard implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, swordPressed, bombPressed, runPressed, enterPressed, savePressed, pausePressed;

    GamePanel gp;

    int timeCounter = 0;

    public Keyboard(GamePanel gp) {
        this.gp = gp;
    }

    //Sin usar
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();

        timeCounter++;
        //MainState
        if (gp.gameState == gp.mainState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 2;
                }

            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 2) {
                    gp.ui.commandNumber = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNumber == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNumber == 1) {
                    try {
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;

                    } catch (ClassNotFoundException ex) {

                    }
                }
                if (gp.ui.commandNumber == 2) {
                    System.exit(0);
                }
            }

        }

        //GameOver
        if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }

            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;

                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNumber == 0) {
                    gp.retry();
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNumber == 1) {
                    gp.reset();
                    gp.gameState = gp.playState;
                }
            }
        }
        //Finished
        if (gp.gameState == gp.finishedState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }

            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;

                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNumber == 0) {
                    gp.reset();
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNumber == 1) {
                    System.exit(0);

                }
            }

        }

        //PlayState
        if (gp.gameState == gp.playState) {
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
            if (code == KeyEvent.VK_I) {
                swordPressed = true;
            }
            if (code == KeyEvent.VK_O) {
                bombPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                runPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                pausePressed = true;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_0) {
                savePressed = true;
            }
        }
        //PauseState
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {

                gp.gameState = gp.playState;
            }
        }
        //DialogueState
        if (gp.gameState == gp.dioalogueState) {
            if (code == KeyEvent.VK_I) {
                gp.gameState = gp.playState;
            }

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

        if (code == KeyEvent.VK_I) {
            swordPressed = false;
        }
        if (code == KeyEvent.VK_O) {
            bombPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            runPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            pausePressed = false;
        }
    }

}
