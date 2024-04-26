package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.StraightShoot;
import edu.hitsz.strategy.Strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
//    private int shootNum = 1;

    /**
     * 子弹伤害
     */
//    private int power = 30;



    private Strategy strategy;
    private volatile static HeroAircraft heroAircraft = null;
    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public static HeroAircraft getInstance() {
        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 1000);
                    heroAircraft.power = 30;
                    heroAircraft.direction = -1;
                    heroAircraft.strategy = new StraightShoot();
                }
            }
        }
        return heroAircraft;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }


    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return this.strategy.shoot(this);
//        return this.strategy.shoot(this);
////        List<BaseBullet> res = new LinkedList<>();
////        int x = this.getLocationX();
////        int y = this.getLocationY() + direction*2;
////        int speedX = 0;
////        int speedY = this.getSpeedY() + direction*5;
////        BaseBullet bullet;
////        for(int i=0; i<shootNum; i++){
////            // 子弹发射位置相对飞机位置向前偏移
////            // 多个子弹横向分散
////            bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
////            res.add(bullet);
////        }
////        return res;
    }

    public void uphp(int hp_plus){
        if (this.hp + hp_plus < this.maxHp) {
            this.hp += hp_plus;
        }else {
            this.hp = this.maxHp;
        }
    }



}
