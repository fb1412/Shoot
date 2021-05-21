package shoot;

import java.awt.image.BufferedImage;

/**
 * 火力奖励
 */
public class Bee extends shoot.Enemies implements Award {
    private int xSpeed = Setting.BeeXSpeed; // x速度
    private final int ySpeed = Setting.BeeYSpeed; // y速度
    int awardType; // 奖励类型
    int value = Setting.BeeaddFire;// 增长生命;

    public Bee() {
        super(shoot.Images.Bee[0].getWidth(), shoot.Images.Bee[0].getHeight(), 0, Setting.BeeLife);
        awardType = Award.FIRE;
    }

    public void step() {
        y += ySpeed;
        x += xSpeed;
        if (x > World.WIDTH - width || x < 0) {
            xSpeed *= -1;
        }
    }

    private int index = 1;

    @Override
    public BufferedImage getImg() {
        if (isLife == 0) {
            return shoot.Images.Bee[0];
        } else {
            BufferedImage img = shoot.Images.Bee[index];
            index++;
            if (index == shoot.Images.Bee.length) {
                isLife = 2;
                index = 4;
            }
            return img;
        }
    }

    @Override
    public int getAwardType() {
        return awardType;
    }

    @Override
    public int getValue() {
        return value;
    }
}