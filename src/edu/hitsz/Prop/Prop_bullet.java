package edu.hitsz.Prop;

import edu.hitsz.application.Game;

public class Prop_bullet extends Prop{
    public Prop_bullet(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(Game game){
        game.bullet_activated();
        System.out.println("FireSupply active!");

    }
}
