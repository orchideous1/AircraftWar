package edu.hitsz.bullet;

import edu.hitsz.Observer.AbstractObserver;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements AbstractObserver {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void update() {
        this.vanish();
    }
}
