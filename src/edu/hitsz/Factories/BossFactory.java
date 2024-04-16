package edu.hitsz.Factories;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.enemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossFactory implements enemyFactory{
    public enemyAircraft createEnemy()
    {
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                ImageManager.ELITE_ENEMY_IMAGE.getHeight() / 2 ,
                2,
                0,
                500
        );
    }
}
