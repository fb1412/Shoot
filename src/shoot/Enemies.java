package shoot;

import java.awt.image.BufferedImage;

public abstract class Enemies extends shoot.FlyingObject{
    
    protected int score;
    
    public Enemies(int width, int height, int score){
        super(width, height);
        this.score = score;
    }
    
    @Override public abstract void step();
    
    @Override public abstract BufferedImage getImg();
    
    /**
     * 是否出界(出界为真)
     */
    public boolean isOut(){
        return y > World.HEIGHT;
    }
    
    
    public int getScore(){
        return score;
    }
}
