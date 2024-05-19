package edu.hitsz.Factories;

import edu.hitsz.Prop.Prop;
import edu.hitsz.Prop.Prop_bomb;

public class PropFactory_bomb implements PropFactory{







    public Prop createProp(int locationX, int locationY, int speedX, int speedY)
    {

        return new Prop_bomb(locationX, locationY, speedX, speedY);
    }
}
