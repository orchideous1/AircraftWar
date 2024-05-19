package edu.hitsz.Game;

import edu.hitsz.Factories.BossFactory;
import edu.hitsz.Factories.EliteFactory;
import edu.hitsz.Factories.MobFactory;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

public class hardGame extends Game{
    public hardGame(){
        super();
    }


    private double hardness = 0.6;
    @Override
    protected void generateAircraft() {
        System.out.println(time);
        // 新敌机产生
        generateBoss();
        if (enemyAircrafts.size() < enemyMaxNumber) {
            double prob = Math.random();
            if(prob < hardness) {

                Factory = new MobFactory();
                enemyAircrafts.add(Factory.createEnemy());
            }else {

                Factory = new EliteFactory();
                enemyAircrafts.add(Factory.createEnemy());
            }
        }
        // 敌机飞机射出子弹
        shootAction();
    }

    @Override
    protected void generateBoss() {
        if (this.score != 0 && this.score >= threhold * chapter  && !isboss) {
            Factory = new BossFactory();
            enemyAircrafts.add(Factory.createEnemy());
            isboss = true;


            if (Main.music){
                bossbgm = new MusicThread("src/videos/bgm_boss.wav");
                bossbgm.start();
                bossbgm.setloop();
            }

        }
    }

    @Override
    protected void afterBossEnd(){
        this.isboss = false;
        this.chapter += chapter;
        System.out.println(chapter * threhold);
        System.out.println("Boss Destroyed! ");
        System.out.println("难度提升！；");
        System.out.println("精英敌机生成概率 + 0.1");
        System.out.println("敌机最大数量 + 1");
        System.out.println("英雄机射击周期 + 80ms ");
        System.out.println("敌机生成周期 - 40ms ");
        System.out.println("Boss机血量 + 200");
        System.out.println("Boss机子弹数量 + 10");
        System.out.println("Boss机生成门槛 - 200");

        if (this.hardness > 0){
            this.hardness = this.hardness - 0.1;
        }
        this.enemyMaxNumber ++ ;
        this.heroshootInterval = this.heroshootInterval + 80;
        if (this.cycleDuration > 40) {
            this.cycleDuration -= 40;
        }
        BossFactory.increaseHp();
        BossFactory.increaseShootNum();

        if (this.threhold > 500) {
            this.threhold -= 200;
        }


        if (Main.music){
            bossbgm.end();
        }
    }
}
