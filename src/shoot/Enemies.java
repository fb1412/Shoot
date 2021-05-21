package shoot;

import java.awt.image.BufferedImage;

public abstract class Enemies extends FlyingObject {

    protected int score;
    protected int life;

    public Enemies(int width, int height, int score, int life) {
        super(width, height);
        this.score = score;
        this.life = life;
    }

    @Override
    public abstract void step();

    @Override
    public abstract BufferedImage getImg();

    /**
     * 是否出界(出界为真)
     */
    public boolean isOut() {
        return y > World.HEIGHT;
    }

    public int getScore() {
        return score;
    }

    /** 死了为真 */
    public boolean goDead() {
        life--;
        if (life < 1) {
            return true;
        } else {
            return false;
        }
    }
}
