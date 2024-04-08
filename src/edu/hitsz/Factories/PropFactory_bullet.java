package edu.hitsz.Factories;

import edu.hitsz.Prop.Prop;
import edu.hitsz.Prop.Prop_bullet;

public class PropFactory_bullet implements PropFactory{
    public  Prop createProp(int locationX, int locationY, int speedX, int speedY)
    {
        return new Prop_bullet(locationX, locationY, speedX, speedY);
    }
}
