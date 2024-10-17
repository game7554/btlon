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

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        Objects.requireNonNull(gp);
        Objects.requireNonNull(gp);
        this.mapTileNum = new int[50][50];
        this.getTileImage();
        this.loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            this.tile[0] = new Tile();
            this.tile[0].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/grass.png"));
            this.tile[1] = new Tile();
            this.tile[1].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/wall.png"));
            this.tile[2] = new Tile();
            this.tile[2].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/water.png"));
            this.tile[3] = new Tile();
            this.tile[3].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/earth.png"));
            this.tile[4] = new Tile();
            this.tile[4].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/tree.png"));
            this.tile[5] = new Tile();
            this.tile[5].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException var2) {
            var2.printStackTrace();
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
                            g2.drawImage(var10001, screenX, screenY, 48, 48, (ImageObserver)null);
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
