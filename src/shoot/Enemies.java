package shoot;

import java.awt.image.BufferedImage;

public abstract class Enemies extends FlyingObject{
    
    protected int score;
    protected int life;
    
    public Enemies(int width, int height, int score, int life){
        super(width, height);
        this.score = score;
        this.life = life;
    }
    
    public Enemies(int width, int height, int score, int life, int x){
        super(width, height, x, -height);
        this.score = score;
        this.life = life;
    }
    
    /**
     * 是否出界(出界为真)
     */
    public boolean isOut(){
        return y > World.HEIGHT;
    }
    
    public int getScore(){
        return score;
    }
    
    /**
     * 死了为真
     */
    public boolean goDead(){
        if (isLife == 0) {
            life--;
        }
        if (life < 1) {
            isLife = 1;
            return true;
        } else {
            return false;
        }
    }
}
