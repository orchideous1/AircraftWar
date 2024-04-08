package edu.hitsz.Factories;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.enemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteFactory implements enemyFactory{
    public enemyAircraft createEnemy()
    {
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                2,
                10,
                30
        );
    }
}
