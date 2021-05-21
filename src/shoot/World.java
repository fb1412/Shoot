package shoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel {
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    private int state = START;
    private int score = 0;

    public static final int WIDTH = Images.Sky.getWidth();
    public static final int HEIGHT = Images.Sky.getHeight();
    private static final int interval = Setting.Interval; // xx毫秒一帧
    private static final int NUM = Setting.EnemiesFrequency;// 敌人生成的总体概率
    private static final int bulletFrequency = Setting.Bullet; // 每xx帧射一发
    private static final int bigAirplaneFrequency = Setting.BigAirplaneFrequency; // 大飞机概率%
    private static final int BeeFrequency = Setting.BeeFrequency; // 火力奖励概率%
    private static final int waspFrequency = Setting.WaspFrequency; // 生命奖励概率%

    private Sky sky = new Sky();
    private Hero hero = new Hero();
    private Enemies[] enemies = {};
    private Bullet[] bullet = {};

    public void action() {
        MouseAdapter m = new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.move(x - hero.width / 2, y - hero.height / 2);
                }
            }

            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        score = 0;
                        sky = new Sky();
                        hero = new Hero();
                        enemies = new Enemies[0];
                        bullet = new Bullet[0];
                        state = START;
                        break;
                }
            }

            public void mouseExited(MouseEvent e) {
                if (state == RUNNING) {
                    state = PAUSE;
                }
            }

            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNNING;
                }
            }
        };
        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        System.out.println("开始");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (state == RUNNING) {
                    enemiesProduce();
                    attack();
                    hitEnemies();
                    hitHero();
                    enemiesRemove();
                    flyingStep();
                    // System.out.println(bullet.length + "|" + enemies.length);
                }
                repaint();
            }
        }, interval, interval);
    }

    /**
     * 飞行物移动
     */
    public void flyingStep() {
        sky.step();
        for (Enemies i : enemies) {
            // System.out.println(i.x + "," + i.y);
            i.step();
        }
    }

    /**
     * 敌人生成
     */
    public void enemiesProduce() {
        Random r = new Random();
        int type = r.nextInt(100);
        // System.out.println(type + "|" + (type % num == 0));
        if (type < NUM) {
            int flag = r.nextInt(100);
            enemies = Arrays.copyOf(enemies, enemies.length + 1);
            if (flag < BeeFrequency) {
                enemies[enemies.length - 1] = new Bee();
            } else if (flag < BeeFrequency + bigAirplaneFrequency) {
                enemies[enemies.length - 1] = new BigAirplane();
            } else if (flag < BeeFrequency + bigAirplaneFrequency + waspFrequency) {
                enemies[enemies.length - 1] = new Wasp();
            } else {
                enemies[enemies.length - 1] = new Airplane();
            }
        }
    }

    /**
     * 飞行物清除
     */
    public void enemiesRemove() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].isOut() || enemies[i].isLife == 2) {
                enemies[i] = enemies[enemies.length - 1];
                enemies = Arrays.copyOf(enemies, enemies.length - 1);
            }
        }

        // for (Enemies i : enemies) {
        // if (i.isOut()) {
        // i = enemies[enemies.length - 1];
        // enemies = Arrays.copyOf(enemies, enemies.length - 1);
        // }
        // }

        for (int i = 0; i < bullet.length; i++) {
            if (bullet[i].isOut() || bullet[i].isLife == 1) {
                bullet[i] = bullet[bullet.length - 1];
                bullet = Arrays.copyOf(bullet, bullet.length - 1);
            }
        }
    }

    /**
     * 发射
     */
    public int index = 0;

    public void attack() {
        index++;
        if (state == RUNNING && index > bulletFrequency) {
            index = 0;
            Bullet[] b = hero.shoot();
            bullet = Arrays.copyOf(bullet, bullet.length + b.length);
            System.arraycopy(b, 0, bullet, bullet.length - b.length, b.length);
        }
        for (Bullet i : bullet) {
            i.step();
        }
    }

    /**
     * 攻击
     */
    public void hitEnemies() {
        // System.out.println(enemies.length + "|" + bullet.length);
        for (int i = 0; i < bullet.length; i++) {
            Bullet b = bullet[i];
            for (int j = 0; j < enemies.length; j++) {
                Enemies e = enemies[j];
                if (b.isHit(e)) {
                    b.isLife = 1;
                    if (e.goDead()) {
                        e.isLife = 1;
                    }
                    score += e.getScore();
                    boolean isAward = e instanceof Award;
                    if (isAward) {
                        Award award = (Award) e;
                        hero.addFire(award.getAwardType() == Award.FIRE, award.getValue());
                        hero.addLife(award.getAwardType() == Award.LIFE, award.getValue());
                    }
                }
                // System.out.println(b.isHit(f));
            }
        }
    }

    /**
     * 撞击
     */
    public void hitHero() {
        for (int i = 0; i < enemies.length; i++) {
            boolean hit = hero.isHit(enemies[i]);
            if (hit) {
                enemies[i].isLife = 1;
                if (!hero.setLife(true)) {
                    state = GAME_OVER;
                }
                hero.clearFire();
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(sky.getImg(), sky.x, sky.y, null);
        g.drawImage(sky.getImg(), sky.x, sky.y1, null);
        // System.out.println(hero.x + "|" + hero.y);
        g.drawImage(hero.getImg(), hero.x, hero.y, null);
        for (Enemies i : enemies) {
            g.drawImage(i.getImg(), i.x, i.y, null);
        }
        for (FlyingObject i : bullet) {
            g.drawImage(i.getImg(), i.x, i.y, null);
        }

        g.drawString("打飞机", 50, 50);
        g.drawString("分数：" + score, 50, 65);
        g.drawString("血量：" + hero.getLife(), 50, 80);
        g.drawString("火力：" + hero.getFire(), 50, 95);
        switch (state) {
            case START:
                g.drawImage(Images.start, (WIDTH - Images.start.getWidth()) / 2,
                        (HEIGHT - Images.start.getHeight()) / 4, null);
                break;
            case PAUSE:
                g.drawImage(Images.pause, (WIDTH - Images.pause.getWidth()) / 2,
                        (HEIGHT - Images.pause.getHeight()) / 4, null);
                break;
            case GAME_OVER:
                g.drawImage(Images.gameover, (WIDTH - Images.gameover.getWidth()) / 2,
                        (HEIGHT - Images.gameover.getHeight()) / 4, null);
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭时结束程序
        frame.setSize(WIDTH, HEIGHT);// 设置窗口大小
        frame.setResizable(false);// 不可调节大小
        frame.setLocationRelativeTo(null); // 设置窗口打开时的位置
        frame.setVisible(true);// 设置可见

        world.action();
    }
}
