package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

public class Prop_bomb extends Prop{
    public Prop_bomb(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(HeroAircraft heroAircraft){
        System.out.println("BombSupply active!");
        if (Main.music){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
    }
}
