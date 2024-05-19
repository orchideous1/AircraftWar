package edu.hitsz.aircraft;

import edu.hitsz.Factories.PropFactory;
import edu.hitsz.Factories.PropFactory_blood;
import edu.hitsz.Factories.PropFactory_bomb;
import edu.hitsz.Factories.PropFactory_bullet;
import edu.hitsz.Prop.Prop;
import edu.hitsz.application.ImageManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.strategy.CircleShoot;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends enemyAircraft{
//    private int shootNum = 20;
//
//    private int power = 10;



    private int propNum = 3;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum){
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = shootNum;
        this.power = 10;
        this.strategy = new CircleShoot();
    }

    @Override
    public List<BaseBullet> shoot(){
        return this.strategy.shoot(this);
    }
//        List<BaseBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY();
//        int speedY = 12;
//
//        int r = ImageManager.Boss_IMAGE.getWidth()/2;
//
//        BaseBullet bullet;
//        for (int i=1; i<=shootNum; i++) {
//            double phi = (double)i / shootNum * Math.PI * 2;
//            bullet = new EnemyBullet((int)(x + r * Math.cos(phi)), (int)(y + r * Math.sin(phi)), (int)(3 * Math.cos(phi)), (int)(3 * Math.cos(phi))+ speedY, power);
//            res.add(bullet);
//        }
//        return res;
//    }
    public void aftercrash(List<Prop> MyProp){
        int X = this.getLocationX();
        int Y = this.getLocationY();
        int speedX = 0;
        int speedY = 12;
        for (int j = 0; j < propNum; j++){
            X += (j - propNum / 2) * 40;
            double prob = Math.random();
            PropFactory propFactory;
            if (prob < 0.1) {

            } else if (prob < 0.4) {

                //MyProp.add(new Prop_blood(X, Y, speedX, speedY));
                propFactory = new PropFactory_blood();
                MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
            } else if (prob < 0.7) {

                //MyProp.add(new Prop_bomb(X, Y, speedX, speedY));
                propFactory = new PropFactory_bomb();
                MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
            } else {

                //MyProp.add(new Prop_bullet(X, Y, speedX, speedY));
                propFactory = new PropFactory_bullet();
                MyProp.add(propFactory.createProp(X, Y, speedX, speedY));
            }
        }
    }

    @Override
    public void update() {
        //Doing Nothing
    }
}
