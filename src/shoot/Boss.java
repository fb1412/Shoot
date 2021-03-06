package shoot;

import java.awt.image.BufferedImage;

public class Boss extends Bosses{
    private int xSpeed = Setting.BossXSpeed;
    private int ySpeed = Setting.BossXSpeed;
    private final int limits = World.WIDTH / 20;
    
    
    public Boss(){
        super(Images.Boss[0].getWidth(), Images.Boss[0].getHeight(), Setting.BossScore, Setting.BossLife, (World.WIDTH - Images.Boss[0].getWidth()) / 2);
    }
    
    private boolean first = true;
    
    @Override public void step(){
        y += ySpeed;
        x += xSpeed;
        
        if (x > World.WIDTH - width - limits || x < limits) {
            xSpeed *= -1;
        }
        
        boolean heightOut = y > World.HEIGHT / 2 - height;
        if (heightOut) {
            first = false;
        }
        if ((heightOut || y < 0) && !first) {
            ySpeed *= -1;
        }
    }
    
    private int life_index = 0;
    private int death_index = 1;
    
    @Override public BufferedImage getImg(){
        if (isLife == 0) {
            return Images.Boss[life_index++ % 2];
        } else {
            BufferedImage img = Images.Boss[death_index];
            death_index++;
            if (death_index == Images.Boss.length) {
                isLife = 2;
                death_index = 22;
            }
            return img;
        }
    }
}
