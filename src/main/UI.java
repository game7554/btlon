package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum=0;
    public int titleScreenState=0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        //CREATE HUB OBJECT
        Entity heart= new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

    }
    public void addMessage(String text) {

        message.add(text);
        messageCounter.add(0);

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
            drawMessage();
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
        //CHaracter STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        // OPTIONS STATE
        if(gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < message.size(); i++){
            if (message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;// messageCounter++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
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
    public  void drawCharacterScreen() {
        //CREAT A FRAME
        final int frameX = gp.tileSize*2;
        final  int frameY = gp.tileSize;
        final  int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize *10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX +20;
        int textY = frameY +gp.tileSize;
        final int lineHeight =35;

        //Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight +10 ;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight +15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        // VALUES
        int tailX =(frameX +frameWidth)-30;
        //RESET textY
        textY =frameY +gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
         textY +=lineHeight;

        value = String.valueOf(gp.player.life+ "/"+gp.player.maxLife);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.mana+ "/"+gp.player.maxMana);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.strength);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.attack);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.defense);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.exp);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.coin   );
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value, textX, textY);
        textY +=lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX -gp.tileSize, textY-24, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-24, null);
    }
    public void drawInventory(){
        // FRAME
        int frameX = gp.tileSize*12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;


        //DRAW PLAYER'S ITEMS
        for (int i = 0; i < gp.player.inventory.size(); i++){

            //EQUIP CURSOR
            if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight =gp.tileSize * 3;


        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()){
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;

            }
        }
    }
    public void drawGameOverScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        int x,y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text="Kết thúc";

        //Shadow
        g2.setColor(Color.black);
        x=getXforCenteredText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Thử lại";
        x=getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0) g2.drawString(">",x-40,y);
        //Quit
        text="Thoát";
        x=getXforCenteredText(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1) g2.drawString(">",x-40,y);
    }
    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*12;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: option_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE
        String text = "Tùy chọn";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Toàn màn hình", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("Nhạc", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("Hiệu ứng âm thanh", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Điều khiển", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("Kết thúc trò chơi", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize*2;
        g2.drawString("Quay lại", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + gp.tileSize*8;
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize*2;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Thay đổi sẽ có hiệu lực sau \nkhi khởi động lại trò chơi.";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize*9;
        g2.drawString("Quay lại", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Điều khiển";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Di chuyển", textX, textY); textY += gp.tileSize;
        g2.drawString("Xác nhận/Tấn công", textX, textY); textY += gp.tileSize;
        g2.drawString("Bắn", textX, textY); textY += gp.tileSize;
        g2.drawString("Màn hình nhân vật", textX, textY); textY += gp.tileSize;
        g2.drawString("Dừng", textX, textY); textY += gp.tileSize;
        g2.drawString("Tùy chọn", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*8;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("B", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Quay lại", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize*2;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Thoát khỏi trò chơi và quay \nlại màn hình chính?";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow* 5);
        return itemIndex;
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

        // DRAW MAX MANA
        x = gp.tileSize/2-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        //DRAW MANA
        x = gp.tileSize/2-5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
