//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[50];
        Objects.requireNonNull(gp);
        Objects.requireNonNull(gp);
        this.mapTileNum = new int[50][50];
        this.getTileImage();
        this.loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
            setup(0, "grass", false);
            setup(1, "wall", true);
            setup(2, "water", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "sand", false);
            
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            this.tile[index].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image= uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = this.getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(true) {
                Objects.requireNonNull(this.gp);
                if (col >= 50) {
                    break;
                }

                Objects.requireNonNull(this.gp);
                if (row >= 50) {
                    break;
                }

                String line = br.readLine();

                while(true) {
                    Objects.requireNonNull(this.gp);
                    if (col >= 50) {
                        Objects.requireNonNull(this.gp);
                        if (col == 50) {
                            col = 0;
                            ++row;
                        }
                        break;
                    }

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    this.mapTileNum[col][row] = num;
                    ++col;
                }
            }

            br.close();
        } catch (Exception var9) {
        }

    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(true) {
            Objects.requireNonNull(this.gp);
            if (worldCol >= 50) {
                break;
            }

            Objects.requireNonNull(this.gp);
            if (worldRow >= 50) {
                break;
            }

            int tileNum = this.mapTileNum[worldCol][worldRow];
            Objects.requireNonNull(this.gp);
            int worldX = worldCol * 48;
            Objects.requireNonNull(this.gp);
            int worldY = worldRow * 48;
            int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
            int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;
            Objects.requireNonNull(this.gp);
            if (worldX + 48 > this.gp.player.worldX - this.gp.player.screenX) {
                Objects.requireNonNull(this.gp);
                if (worldX - 48 < this.gp.player.worldX + this.gp.player.screenX) {
                    Objects.requireNonNull(this.gp);
                    if (worldY + 48 > this.gp.player.worldY - this.gp.player.screenY) {
                        Objects.requireNonNull(this.gp);
                        if (worldY - 48 < this.gp.player.worldY + this.gp.player.screenY) {
                            BufferedImage var10001 = this.tile[tileNum].image;
                            Objects.requireNonNull(this.gp);
                            Objects.requireNonNull(this.gp);
                            g2.drawImage(var10001, screenX, screenY, (ImageObserver)null);
                        }
                    }
                }
            }

            ++worldCol;
            Objects.requireNonNull(this.gp);
            if (worldCol == 50) {
                worldCol = 0;
                ++worldRow;
            }
        }

    }
}
