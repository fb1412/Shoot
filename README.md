# 打飞机游戏

<https://github.com/fb1412/Shoot.git>

setting.json可以配置游戏参数：

+ interval --- 游戏速度，XX毫秒一帧(所以越大越慢)
+ skySpeed --- 背景速度
+ Hero:
  + life --- 初始血量
  + fire --- 初始火力
  + Bullet --- 子弹发射频率
  
+ enemies
  + frequency --- 敌人总体生成频率

  + Bee --- 火力值奖励机
    + frequency --- 生成概率
    + life --- 敌人生命值
    + addFire --- 每次击中奖励的火力
    + xSpeed --- 横向移动速度
    + YSpeed --- 纵向移动速度

  + Wasp --- 生命值奖励机
    + frequency --- 生成概率
    + life --- 敌人生命值
    + addLife --- 每次击中奖励的血量
    + xSpeed --- 横向移动速度
    + YSpeed --- 纵向移动速度

  + BigAirplane ---大飞机
    + frequency --- 生成概率
    + life --- 敌人生命值
    + score --- 分值
    + Speed --- 移动速度

  + Airplane ---基础飞机
    + life --- 敌人生命值
    + score --- 分值
    + Speed --- 移动速度
