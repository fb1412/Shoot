package shoot;


import java.awt.image.BufferedImage;

/**
 * 子弹
 */
public class Bullet extends shoot.FlyingObject{
    private final int speed = 3;//速度
    
    public Bullet(int x, int y){
        super(shoot.Images.Bullet.getWidth(), shoot.Images.Bullet.getHeight(), x - shoot.Images.Bullet.getWidth() / 2, y);
    }
    
    public void step(){
        this.y -= speed;
    }
    
    @Override public BufferedImage getImg(){
        return shoot.Images.Bullet;
    }
    
    /**
     * 是否出界(出界为真)
     */
    public boolean isOut(){
        return y < 0;
    }
    
}
