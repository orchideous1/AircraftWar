package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class CircleShoot implements Strategy {
    public List<BaseBullet> shoot(AbstractAircraft aircraft)
    {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY();
        int speedX = aircraft.getSpeedX();
        int speedY = aircraft.getSpeedY() + 12 * aircraft.getDirection();

        int r = ImageManager.Boss_IMAGE.getWidth()/2;

        BaseBullet bullet;
        int shootNum = 20;
        for (int i=0; i<=shootNum; i++) {
            double phi = (double)i / shootNum * Math.PI * 2;
            if (aircraft instanceof HeroAircraft){
                bullet = new HeroBullet((int)(x + r * Math.cos(phi)), (int)(y + r * Math.sin(phi)),(int)(3 * Math.cos(phi)) + speedX, (int)(3 * Math.cos(phi))+ speedY, aircraft.getPower());
            } else {
                bullet = new EnemyBullet((int)(x + r * Math.cos(phi)), (int)(y + r * Math.sin(phi)), (int)(3 * Math.cos(phi)) + speedX, (int)(3 * Math.cos(phi))+ speedY, aircraft.getPower());
            }
            res.add(bullet);
        }
        return res;
    }
}
