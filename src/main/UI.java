package main;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum=0;
    public int titleScreenState=0;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        //CREATE HUB OBJECT
        SuperObject heart= new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState==gp.titleState) drawTitleScreen();
        //PLAY STATE
        if (gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawTitleScreen()
    {
        if(titleScreenState==0)
        {
        //TITLE SCREEN
        g2.setColor(Color.yellow);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        //LOGO HUST
        //g2.drawImage(gp.player.logo, 0, 0, gp.tileSize*12,gp.tileSize*7,null);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.HANGING_BASELINE,96F));
        String text= "7554";
        int x= getXforCenteredText(text);
        int y= gp.tileSize*2;
        g2.setColor(Color.red);
        g2.drawString(text,x,y);
        //IMAGE
        x=gp.screenWidth/2-(gp.tileSize*2)/2;
        y+=gp.tileSize/2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2,gp.tileSize*2,null);
        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,48F));
        text="Trò chơi mới";
        x=getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if (commandNum==0) g2.drawString(">",x-gp.tileSize,y);
        text="Chơi tiếp";
        x=getXforCenteredText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum==1) g2.drawString(">",x-gp.tileSize,y);
        text="Hướng dẫn";
        x=getXforCenteredText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum==2) g2.drawString(">",x-gp.tileSize,y);
        text="Thoát";
        x=getXforCenteredText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum==3) g2.drawString(">",x-gp.tileSize,y);
        }
        else
        {
            //TITLE SCREEN
            g2.setColor(Color.yellow);
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            //LOGO HUST
            //g2.drawImage(gp.player.logo, 0, 0, gp.tileSize*12,gp.tileSize*7,null);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.HANGING_BASELINE,96F));
            String text= "7554";
            int x= getXforCenteredText(text);
            int y= gp.tileSize*2;
            g2.setColor(Color.red);
            g2.drawString(text,x,y);
            //IMAGE
            x=gp.screenWidth/2-(gp.tileSize*2)/2;
            y+=gp.tileSize/2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2,gp.tileSize*2,null);
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.ITALIC,48F));
            text="Chon do kho";
            x=getXforCenteredText(text);
            y+=gp.tileSize*4;
            //g2.setColor(Color.white);
            g2.drawString(text,x,y);
            text="Dễ";
            x=getXforCenteredText(text);
            y+=gp.tileSize*2;
            g2.drawString(text,x,y);
            if (commandNum==0) g2.drawString(">",x-gp.tileSize,y);
            text="Binh thuong";
            x=getXforCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==1) g2.drawString(">",x-gp.tileSize,y);
            text="Khó";
            x=getXforCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==2) g2.drawString(">",x-gp.tileSize,y);
            text="Tro lại";
            x=getXforCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==3) g2.drawString(">",x-gp.tileSize,y);
        }
    }
    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height -10, 25, 25);
    }
    public void drawPlayerLife(){
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        //HEART BLANK
        while(i<1) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        //CURRENT LIFE
        while(i<gp.player.life)
        {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.player.life) g2.drawImage(heart_full,x,y,null);
            i++;
            x+=gp.tileSize;
        }

    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
