package edu.hitsz.Prop;

import edu.hitsz.application.Game;

public class Prop_bomb extends Prop{
    public Prop_bomb(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void aftercrash(Game game){
        game.bomb_activated();
        System.out.println("BombSupply active!");
    }
}
