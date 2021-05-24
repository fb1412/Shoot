package shoot;

import java.awt.image.BufferedImage;

/**
 * 子弹
 */
public class Bullet extends FlyingObject {
    private final int speed = Setting.BulletSpeed;// 速度
    private int type;// 1左2右

    public Bullet(int x, int y) {
        super(Images.Bullet.getWidth(), Images.Bullet.getHeight(), x - Images.Bullet.getWidth() / 2, y);
    }

    public Bullet(int x, int y, int type) {
        super(Images.Bullet.getWidth(), Images.Bullet.getHeight(), x - Images.Bullet.getWidth() / 2, y);
        this.type = type;
    }

    @Override
    public void step() {
        switch (type) {
            case 1:
                this.x -= speed;
                this.y -= speed;
                break;
            case 2:
                this.x += speed;
                this.y -= speed;

                break;
            default:
                this.y -= speed;
                break;
        }
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
