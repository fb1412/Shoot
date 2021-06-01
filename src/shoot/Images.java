package shoot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Images{
    
    public static BufferedImage Sky;
    public static BufferedImage Bullet;
    public static BufferedImage[] Hero;
    public static BufferedImage[] Airplane;
    public static BufferedImage[] BigAirplane;
    public static BufferedImage[] Bee;
    public static BufferedImage[] Wasp;
    public static BufferedImage[] Boss;
    
    
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameOver;
    
    static{
        Sky = readImage("img/background.png");
        Bullet = readImage("img/bullet.png");
        
        Hero = new BufferedImage[2];
        Hero[0] = readImage("img/hero0.png");
        Hero[1] = readImage("img/hero1.png");
        
        Airplane = new BufferedImage[5];
        for (int i = 0 ; i < Airplane.length ; i++) {
            Airplane[i] = readImage("img/airplane" + i + ".png");
        }
        BigAirplane = new BufferedImage[5];
        for (int i = 0 ; i < BigAirplane.length ; i++) {
            BigAirplane[i] = readImage("img/bigAirplane" + i + ".png");
        }
        Bee = new BufferedImage[5];
        for (int i = 0 ; i < Bee.length ; i++) {
            Bee[i] = readImage("img/bee" + i + ".png");
        }
        Wasp = new BufferedImage[5];
        for (int i = 0 ; i < Wasp.length ; i++) {
            Wasp[i] = readImage("img/wasp" + i + ".png");
        }
        
        Boss = new BufferedImage[27];
        Boss[0] = readImage("img/boss.png");
        Boss[1] = readImage("img/boss1.png");
        int j = 1;
        for (int i = 2 ; i < Boss.length ; i += 5) {
            Boss[i] = readImage("img/boss_hit" + j + ".png");
            Boss[i + 1] = readImage("img/boss_hit" + j + ".png");
            Boss[i + 2] = readImage("img/boss_hit" + j + ".png");
            Boss[i + 3] = readImage("img/boss_hit" + j + ".png");
            Boss[i + 4] = readImage("img/boss_hit" + j + ".png");
            j++;
        }
        
        start = readImage("img/start.png");
        pause = readImage("img/pause.png");
        gameOver = readImage("img/gameover.png");
    }
    
    public static BufferedImage readImage(String imgPath){
        try {
            return ImageIO.read(new FileInputStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}