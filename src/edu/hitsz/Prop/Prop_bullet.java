package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.strategy.ScatterShoot;

public class Prop_bullet extends Prop{
    public Prop_bullet(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        heroAircraft.setStrategy(new ScatterShoot());
        System.out.println("FireSupply active!");

    }
}
