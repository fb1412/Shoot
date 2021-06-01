package shoot;

import java.awt.image.BufferedImage;

/**
 * 主角
 */
public class Hero extends FlyingObject{
    private int life = Setting.HeroLife;// 初始血量
    private int fire = Setting.HeroFire;// 火力
    
    public Hero(){
        super(Images.Hero[0].getWidth(), Images.Hero[0].getHeight(), (World.WIDTH - Images.Hero[0].getWidth()) / 2,
              450);
    }
    
    public void step(){
    }
    
    private int index = 0;
    
    @Override
    public BufferedImage getImg(){
        // if (index < Images.Hero.length - 1) {
        // index++;
        // } else {
        // index = 0;
        // }
        // // System.out.println(index);
        // return Images.Hero[index];
        return Images.Hero[index++ % Images.Hero.length];
    }
    
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * 主角受伤,死了返回false
     */
    public boolean setLife(boolean b){
        if (b) {
            this.life -= 1;
            if (life <= 0) {
                fire = 0;
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    public int getLife(){
        return life;
    }
    
    public void addFire(boolean b, int addFire){
        if (b) {
            fire += addFire;
        }
    }
    
    public void addLife(boolean b, int addLife){
        if (b) {
            life += addLife;
        }
    }
    
    public int getFire(){
        return fire;
    }
    
    public Bullet[] shoot(){
        Bullet[] b;
        if (fire > 20) {
            b = new Bullet[5];
            b[0] = new Bullet(x, y, 1);
            b[1] = new Bullet(width / 4 + x, y);
            b[2] = new Bullet(width * 2 / 4 + x, y);
            b[3] = new Bullet(width * 3 / 4 + x, y);
            b[4] = new Bullet(width + x, y, 2);
        } else if (fire > 10) {
            b = new Bullet[3];
            b[0] = new Bullet(width / 4 + x, y);
            b[1] = new Bullet(width * 2 / 4 + x, y);
            b[2] = new Bullet(width * 3 / 4 + x, y);
            fire--;
        } else if (fire > 0) {
            b = new Bullet[2];
            b[0] = new Bullet(width / 3 + x, y);
            b[1] = new Bullet(width * 2 / 3 + x, y);
            fire--;
        } else {
            b = new Bullet[1];
            b[0] = new Bullet((2 * x + width) / 2, y);
        }
        return b;
    }
    
    public void clearFire(){
        fire = 0;
    }
    
}
