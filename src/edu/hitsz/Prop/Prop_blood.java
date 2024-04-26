package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;

public class Prop_blood extends Prop{
    private final int blood = 5;
    public Prop_blood(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        heroAircraft.uphp(blood);
    }
}
