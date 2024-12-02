package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // lay lenh nhap tu ban phim

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            tileState(code);
        }
        // PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        //CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //TRADE STATE
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
    }

    public void tileState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                //ADD LATER
            }
            if (gp.ui.commandNum == 2) {
                //ADD LATER
            }
            if (gp.ui.commandNum == 3) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
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
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_B) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        //DEBUG
        if (code == KeyEvent.VK_T) {

            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/worldV3.txt", 0);
                case 1:
                    gp.tileM.loadMap("maps/interior01.txt", 1);
            }
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_B) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            gp.playMusic(0);
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) gp.ui.commandNum = 1;
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) gp.ui.commandNum = 0;
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            } else {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) enterPressed = true;
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) gp.ui.commandNum = 2;
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) gp.ui.commandNum = 0;
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1)
        {
            npcInventory(code);
            if(code==KeyEvent.VK_ESCAPE){
                gp.ui.subState=0;
            }
        }
        if (gp.ui.subState == 2)
        {
            playerInventory(code);
            if(code==KeyEvent.VK_ESCAPE){
                gp.ui.subState=0;
            }
        }
    }
    public void playerInventory(int code)
    {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerslotRow != 0) {
                gp.ui.playerslotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerslotCol != 0) {
                gp.ui.playerslotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerslotRow != 3) {
                gp.ui.playerslotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerslotCol != 4) {
                gp.ui.playerslotCol++;
                gp.playSE(9);
            }
        }
    }
    public void npcInventory(int code)
    {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcslotRow != 0) {
                gp.ui.npcslotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcslotCol != 0) {
                gp.ui.npcslotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcslotRow != 3) {
                gp.ui.npcslotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcslotCol != 4) {
                gp.ui.npcslotCol++;
                gp.playSE(9);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if( code == KeyEvent.VK_W){
            upPressed = false;
        }
        if( code == KeyEvent.VK_S){
            downPressed = false;
        }
        if( code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if( code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if( code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }

    }
}
