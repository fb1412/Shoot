package shoot;

import java.awt.image.BufferedImage;

/**
 * 背景
 */
public class Sky extends FlyingObject {
    private final int speed = Setting.SkySpeed;// 速度
    protected int y1 = -World.HEIGHT;

    public Sky() {
        super(Images.Sky.getWidth(), Images.Sky.getHeight(), 0, 0);

    }

    public void step() {
        if (y == World.HEIGHT) {
            y = 0;
            y1 = -World.HEIGHT;
        } else {
            y += speed;
            y1 += speed;
        }
    }

    @Override
    public BufferedImage getImg() {
        return Images.Sky;
    }

}