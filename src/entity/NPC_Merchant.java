package entity;

import main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp)
    {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        setItems();

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
    }
    public void getImage()
    {
        up1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
    }
    public void setDialogue()
    {
        dialogues[0][0] = "Hehe haha.\nTa có một vài món đồ tốt. \nCậu có muốn trao đổi không?";
        dialogues[1][0] = "Hẹn gặp lại, hehe!";
        dialogues[2][0] = "Bạn cần nhiều xu hơn để mua nó!";
        dialogues[3][0] = "Bạn không thể mang thêm đồ nữa!";
        dialogues[4][0] = "Bạn không thể bán món đồ đã được trang bị!";
    }
    public void setItems()
    {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Tent(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_ManaCrystal(gp));
        inventory.add(new OBJ_Key(gp));
        //inventory.add(new OBJ_Pickaxe(gp));

    }
    public void speak()
    {
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}