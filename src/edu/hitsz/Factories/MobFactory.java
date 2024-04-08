package edu.hitsz.Factories;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.aircraft.enemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobFactory implements enemyFactory{
    public enemyAircraft createEnemy()
    {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                30
        );
    }
}
