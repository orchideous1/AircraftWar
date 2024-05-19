package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShoot;
import edu.hitsz.strategy.StraightShoot;

public class Prop_bullet extends Prop{
    public Prop_bullet(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        Runnable r = () -> {
            try {
                heroAircraft.setStrategy(new ScatterShoot());
                heroAircraft.is_bomb += 1;
                //System.out.println("FireSupply active!");
                Thread.sleep(5000);
                if(heroAircraft.is_bomb == 1){
                    heroAircraft.setStrategy(new StraightShoot());
                    heroAircraft.is_bomb -= 1;
                } else {
                    heroAircraft.is_bomb -= 1;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread bullet = new Thread(r);
        bullet.start();




    }
}
