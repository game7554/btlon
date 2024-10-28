package main;

import java.io.*;
import java.nio.file.Path;
public class Config {

    GamePanel gp;
    public Config(GamePanel gp)
    {
        this.gp=gp;
    }
    public void saveConfig() {
        //Path saveData = Path.of("gameConfig.txt");
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));
            //Full screen
            if(gp.fullScreenOn==true) bw.write("On");
            else bw.write("Off");
            bw.newLine();

            //Music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfig(){
        //Path saveData = Path.of("gameConfig.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
            String s= br.readLine();

            //Full screen
            if(s.equals("On")) gp.fullScreenOn=true;
            else gp.fullScreenOn=false;

            //Music
            s=br.readLine();
            gp.music.volumeScale=Integer.parseInt(s);

            //SE
            s=br.readLine();
            gp.se.volumeScale=Integer.parseInt(s);
            br.close();
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
