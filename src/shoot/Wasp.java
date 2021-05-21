package shoot;

import java.awt.image.BufferedImage;

/**
 * 生命奖励
 */
public class Wasp extends Enemies implements Award{
    private int xSpeed = Setting.WaspXSpeed; //x速度
    private final int ySpeed = Setting.WaspYSpeed; //y速度
    int awardType; //奖励类型
    int value = Setting.WaspAddLife;
    ;//增长生命;
    
    public Wasp(){
        super(Images.Wasp[0].getWidth(), Images.Wasp[0].getHeight(), 0);
        awardType = Award.LIFE;
    }
    
    public void step(){
        y += ySpeed;
        x -= xSpeed;
        if (x > World.WIDTH - width || x < 0) {
            xSpeed *= -1;
        }
    }
    
    private int index = 1;
    
    @Override public BufferedImage getImg(){
        if (isLife == 0) {
            return Images.Wasp[0];
        } else {
            BufferedImage img = Images.Wasp[index];
            index++;
            if (index == Images.Wasp.length) {
                isLife = 2;
                index = 4;
            }
            return img;
        }
    }
    
    @Override public int getAwardType(){
        return awardType;
    }
    
    @Override public int getValue(){
        return value;
    }
}
