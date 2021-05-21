package shoot;

import java.awt.image.BufferedImage;

/**
 * 大飞机
 */
public class BigAirplane extends shoot.Enemies{
    private final int speed = Setting.BigAirplaneSpeed;//速度
    
    public BigAirplane(){
        super(shoot.Images.BigAirplane[0].getWidth(), shoot.Images.BigAirplane[0].getHeight(), Setting.BigAirplaneScore);
    }
    
    public void step(){
        y += speed;
    }
    
    private int index = 1;
    
    @Override public BufferedImage getImg(){
        if (isLife == 0) {
            return shoot.Images.BigAirplane[0];
        } else {
            BufferedImage img = shoot.Images.BigAirplane[index];
            index++;
            if (index == shoot.Images.BigAirplane.length) {
                isLife = 2;
                index = 4;// 应为当撞击导致gameover时线程不执行所以必须处理一下
            }
            return img;
        }
    }
}
