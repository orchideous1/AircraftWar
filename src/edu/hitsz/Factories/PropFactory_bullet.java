package edu.hitsz.Factories;

import edu.hitsz.Prop.Prop;
import edu.hitsz.Prop.Prop_bullet;
import edu.hitsz.Prop.Prop_bulletPlus;

public class PropFactory_bullet implements PropFactory{
    public  Prop createProp(int locationX, int locationY, int speedX, int speedY)
    {
        double random = Math.random();
        if (random < 0.5){
            return new Prop_bullet(locationX, locationY, speedX, speedY);
        } else {
            return new Prop_bulletPlus(locationX, locationY, speedX, speedY);
        }
        //return new Prop_bullet(locationX, locationY, speedX, speedY);
    }
}
