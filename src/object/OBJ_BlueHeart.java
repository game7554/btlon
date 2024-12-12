package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

    GamePanel gp;
    public static final String objName = "Tim Xanh";
    public OBJ_BlueHeart(GamePanel gp)
    {
        super(gp);

        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogues();
    }
    public void setDialogues()
    {
        dialogues[0][0] = "Bạn nhặt được một viên ngọc xanh đẹp.";
        dialogues[0][1] = "Bạn tìm Tim Xanh, một kho báu huyền thoại!";
    }
    public boolean use(Entity entity) //when pickup this method will be called
    {
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }

}