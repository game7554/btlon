package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    //Rectangle eventRect;
    //int eventRectDefaultX,eventRectDefaultY;
    public EventHandler(GamePanel gp)
    {
        this.gp=gp;
        eventRect= new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col=0;
        int row=0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow)
        {
            eventRect[col][row]= new EventRect();
            eventRect[col][row].x=23;
            eventRect[col][row].y=23;
            eventRect[col][row].width=2;
            eventRect[col][row].height=2;
            eventRect[col][row].eventRectDefaultX=eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY=eventRect[col][row].y;
            col++;
            if(col== gp.maxWorldCol)
            {col=0;
                row++;}

        }
    }
    public void checkEvent(){
        if(hit(27,16,"right")==true) damagePit(27,16,gp.dialogueState);
        if(hit(23,12,"up")==true) healingPool(23,12,gp.dialogueState);
    }
    public boolean hit(int col, int row, String reqDirection)
    {
        boolean hit = false;
        gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY+gp.player.solidArea.y;
        eventRect[col][row].x=col*gp.tileSize+eventRect[col][row].x;
        eventRect[col][row].y=row*gp.tileSize+eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]))
            if(gp.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")) hit=true;

        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        eventRect[col][row].x=eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y=eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void damagePit(int col, int row, int gameState){
        gp.gameState= gameState;
        gp.ui.currentDialogue="Hit by leaf";
        gp.player.life--;
        eventRect[col][row].eventDone=true;
    }
    public void healingPool(int col, int row, int gameState){
        if(gp.keyH.enterPressed==true){
        gp.gameState=gameState;
        gp.player.attackCanceled = true;
        gp.ui.currentDialogue="Bạn dã uống nuơc nen duoc hoi mau.";
        gp.player.life=gp.player.maxLife;
        eventRect[col][row].eventDone=true;
        }
    }
}
