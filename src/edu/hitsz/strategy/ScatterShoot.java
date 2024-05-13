package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ScatterShoot implements Strategy {
    public List<BaseBullet> shoot(AbstractAircraft aircraft)
    {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        int speedX = aircraft.getSpeedX();
        int speedY = aircraft.getSpeedY() + aircraft.getDirection() * 4;
        BaseBullet bullet;
        int shootNum = 5;
        for(int i=0; i< shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
//            if (aircraft.getShootNum() % 2 == 0){
//                continue;
//            }
            if (aircraft instanceof edu.hitsz.aircraft.HeroAircraft){
                bullet = new HeroBullet(x + (i*2 -shootNum + 3)*10, y, speedX -2 + i, speedY, aircraft.getPower());
            } else {
                bullet = new EnemyBullet(x + (i*2 - shootNum + 3)*10, y, speedX -2 + i, speedY, aircraft.getPower());
            }
            res.add(bullet);
        }

        return res;
    }
}
