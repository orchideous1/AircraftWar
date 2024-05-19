package edu.hitsz.Prop;

import edu.hitsz.Observer.AbstractObserver;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.enemyAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.bullet.BaseBullet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Prop_bomb extends Prop{


    public Prop_bomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);


    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        List<AbstractObserver> MyObserver = new ArrayList<>();
        System.out.println("BombSupply active!");
        try{
            Class game = Class.forName("edu.hitsz.Game.Game");
            Field enemyAircrafts = game.getDeclaredField("enemyAircrafts");
            enemyAircrafts.setAccessible(true);
            for (enemyAircraft aircraft: (List<enemyAircraft>) enemyAircrafts.get(Main.game)){
                MyObserver.add(aircraft);
            }

            Field enemybullets = game.getDeclaredField("enemyBullets");
            enemybullets.setAccessible(true);
            for (BaseBullet bullet: (List<BaseBullet>) enemybullets.get(Main.game)){
                MyObserver.add(bullet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Main.music){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
        for(AbstractObserver obs : MyObserver){
            obs.update();
        }
    }
}
