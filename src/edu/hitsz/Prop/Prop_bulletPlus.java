package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.strategy.CircleShoot;

public class Prop_bulletPlus extends Prop{
    public Prop_bulletPlus(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        heroAircraft.setStrategy(new CircleShoot());
        System.out.println("FireSupply active!");
    }
}
