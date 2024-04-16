package edu.hitsz.aircraft;

import edu.hitsz.Prop.Prop;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public abstract class enemyAircraft extends AbstractAircraft{

    public enemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }


    abstract public void aftercrash(List<Prop> MyProp);
}
