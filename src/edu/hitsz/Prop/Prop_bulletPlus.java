package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.strategy.CircleShoot;
import edu.hitsz.strategy.ScatterShoot;
import edu.hitsz.strategy.StraightShoot;

public class Prop_bulletPlus extends Prop{
    public Prop_bulletPlus(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        Runnable r = () -> {
            try {
                heroAircraft.setStrategy(new CircleShoot());
                System.out.println("FireSupply active!");
                Thread.sleep(5000);
                heroAircraft.setStrategy(new StraightShoot());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread bullet = new Thread(r);
        bullet.start();
    }
}
