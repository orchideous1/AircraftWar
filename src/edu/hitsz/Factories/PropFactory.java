package edu.hitsz.Factories;

import edu.hitsz.Prop.Prop;

public interface PropFactory {
    Prop createProp(int locationX, int locationY, int speedX, int speedY);
}
