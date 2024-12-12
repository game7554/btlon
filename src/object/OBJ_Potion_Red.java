package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    public static final String objName = "Thần dược đỏ";

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHồi sức sống với \n" + value + " máu.";
        price = 10;
        stackable = true;

        setDialogue();
    }
    public void setDialogue()
    {
        dialogues[0][0] = "Bạn uống " + name + "!\n" + "Sức sống của bạn được hồi với " + value + " máu.";
    }
    public boolean use(Entity entity)
    {
        startDialogue(this,0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}