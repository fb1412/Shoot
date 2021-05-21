package shoot;

import java.awt.image.BufferedImage;

/**
 * 子弹
 */
public class Bullet extends FlyingObject {
    private final int speed = 3;// 速度

    public Bullet(int x, int y) {
        super(Images.Bullet.getWidth(), Images.Bullet.getHeight(), x - Images.Bullet.getWidth() / 2, y);
    }

    public void step() {
        this.y -= speed;
    }

    @Override
    public BufferedImage getImg() {
        return Images.Bullet;
    }

    /**
     * 是否出界(出界为真)
     */
    public boolean isOut() {
        return y < 0;
    }

}
