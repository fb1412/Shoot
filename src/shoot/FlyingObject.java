package shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class FlyingObject{
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int isLife = 0;//0,活 1,死 2,移除
    
    public FlyingObject(int width, int height){
        this.width = width;
        this.height = height;
        Random rand = new Random();
        x = rand.nextInt(World.WIDTH - width);
        y = -height;
    }
    
    public FlyingObject(int width, int height, int x, int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    public abstract void step();
    
    public abstract BufferedImage getImg();
    
    /**
     * 是否碰撞(碰撞为真)
     */
    public boolean isHit(FlyingObject enemy){
        int myXLeft = this.x;
        int myXRight = this.x + this.width;
        int myYTop = this.y;
        int myYLow = this.y + this.height;
        
        int enemyXLeft = enemy.x;
        int enemyXRight = enemy.x + enemy.width;
        int enemyYTop = enemy.y;
        int enemyYLow = enemy.y + enemy.height;
        
        return (
                enemy.isLife == 0 && this.isLife == 0 && myXLeft < enemyXRight && myXRight > enemyXLeft
                        && myYTop < enemyYLow && myYLow > enemyYTop
        );
    }
    
}




