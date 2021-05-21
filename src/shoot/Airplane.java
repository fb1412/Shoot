package shoot;

import java.awt.image.BufferedImage;

/**
 * 小飞机
 */
public class Airplane extends Enemies {
    private final int speed = Setting.AirplaneSpeed;// 速度

    public Airplane() {
        super(Images.Airplane[0].getWidth(), Images.Airplane[0].getHeight(), Setting.AirplaneScore,
                Setting.AirplaneLife);
    }

    public void step() {
        y += speed;
    }

    private int index = 1;

    @Override
    public BufferedImage getImg() {
        if (isLife == 0) {
            return Images.Airplane[0];
        } else {
            BufferedImage img = Images.Airplane[index];
            index++;
            if (index == Images.Airplane.length) {
                isLife = 2;
                index = 4;// 应为当撞击导致gameover时线程不执行所以必须处理一下
            }
            return img;
        }
    }

}