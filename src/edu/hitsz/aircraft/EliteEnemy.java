package edu.hitsz.aircraft;

import edu.hitsz.Factories.PropFactory;
import edu.hitsz.Factories.PropFactory_blood;
import edu.hitsz.Factories.PropFactory_bomb;
import edu.hitsz.Factories.PropFactory_bullet;
import edu.hitsz.Observer.AbstractObserver;
import edu.hitsz.Prop.Prop;
import edu.hitsz.Prop.Prop_blood;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends enemyAircraft  {
//    private int shootNum = 1;
//
//    private int power = 6;



    private int propnum = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
        this.strategy = new StraightShoot();
    }

    @Override
    public void forward(){
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot(){
        return strategy.shoot(this);
    }
//        List<BaseBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction*2;
//        int speedX = 0;
//        int speedY = this.getSpeedY() + direction*4;
//        BaseBullet bullet;
//        for(int i=0; i<shootNum; i++){
//            // 子弹发射位置相对飞机位置向前偏移
//            // 多个子弹横向分散
//            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
//            res.add(bullet);
//        }
//        return res;
//    }

    @Override
    public void update(){
        this.vanish();
    }

    public void aftercrash(List<Prop> MyProp){
        double prob = Math.random();
        PropFactory propFactory;
        if (prob < 0.1) {

        } else if (prob < 0.4) {
            int X = this.getLocationX();
            int Y = this.getLocationY();
            int speedX = 0;
            int speedY = this.getSpeedY();
            //MyProp.add(new Prop_blood(X, Y, speedX, speedY));
            propFactory = new PropFactory_blood();
            MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
        } else if (prob < 0.7) {
            int X = this.getLocationX();
            int Y = this.getLocationY();
            int speedX = 0;
            int speedY = this.getSpeedY();
            //MyProp.add(new Prop_bomb(X, Y, speedX, speedY));
            propFactory = new PropFactory_bomb();
            MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
        } else {
            int X = this.getLocationX();
            int Y = this.getLocationY();
            int speedX = 0;
            int speedY = this.getSpeedY();
            //MyProp.add(new Prop_bullet(X, Y, speedX, speedY));
            propFactory = new PropFactory_bullet();
            MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
        }
    }


}
