package edu.hitsz.Factories;

import edu.hitsz.Prop.Prop;
import edu.hitsz.Prop.Prop_blood;

public class PropFactory_blood implements PropFactory{
    public Prop createProp(int locationX, int locationY, int speedX, int speedY){
        return new Prop_blood(locationX, locationY, speedX, speedY);
    }
}
