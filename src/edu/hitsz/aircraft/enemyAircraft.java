package edu.hitsz.aircraft;

import edu.hitsz.Observer.AbstractObserver;
import edu.hitsz.Prop.Prop;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public abstract class enemyAircraft extends AbstractAircraft implements AbstractObserver {
    protected boolean isShooted = false;
    public enemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }

    public boolean isShooted() {
        return isShooted;
    }

    public void beingShooted(){
        isShooted = true;
    }

    abstract public void aftercrash(List<Prop> MyProp);
}
