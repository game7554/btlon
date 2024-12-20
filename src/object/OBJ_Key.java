package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Chìa khóa";

    public OBJ_Key(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = objName;
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nNó để mở cửa.";
        price = 10;
        stackable = true;

        setDialogue();
    }
    public void setDialogue()
    {
        dialogues[0][0] = "Bạn dùng " + name + " và mở cửa.";

        dialogues[1][0] = "Bạn đang làm gì vậy?";
    }
    public boolean use(Entity entity)
    {
        int objIndex = getDetected(entity, gp.obj, "Cửa"); //user, target, name
        if(objIndex != 999)
        {
            startDialogue(this,0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else
        {
            startDialogue(this,1);
            return false;
        }
    }
}