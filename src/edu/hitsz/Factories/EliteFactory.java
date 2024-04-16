package edu.hitsz.Factories;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.enemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteFactory implements enemyFactory{
    public enemyAircraft createEnemy() {
        double randomNum = Math.random();
        if (randomNum <= 0.5){
            return new EliteEnemy(
                    (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                    (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                    2,
                    10,
                    30
            );
        } else {
            return new ElitePlusEnemy(
                    (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITEPLUS_ENEMY_IMAGE.getWidth())),
                    (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                    2,
                    10,
                    30
            );
        }
    }
}
