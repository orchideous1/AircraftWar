package edu.hitsz.Game;

import edu.hitsz.Factories.*;
import edu.hitsz.Prop.Prop;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.scoreboard;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract  class Game extends JPanel {

    protected int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    protected final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<enemyAircraft> enemyAircrafts;


    protected  enemyFactory Factory = null;

    protected boolean isboss = false;

    protected int chapter = 1;

    protected final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;

    protected final List<Prop> MyProp;

    public static final DAO dao = new recordDAO();
    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 6;

    /**
     * 当前得分
     */
    protected int score = 0;
    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration = 600;
    protected int cycleTime = 0;

    protected int heroshootInterval = 320;

    protected int threhold = 1000;

    protected int scoreplus = 20;
    /**
     * 游戏结束标志
     */
    protected boolean gameOverFlag = false;


    protected MusicThread bgm = new MusicThread("src/videos/bgm.wav");
    protected MusicThread bossbgm;
    public Game() {
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        MyProp = new LinkedList<>();
        /**
         *
         *
         *
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        playMusic();

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                generateAircraft();
            }

            if (time % heroshootInterval ==0){
                //英雄机射击子弹
                heroShootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();//处理道具等飞行物的状态

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                afterGame();



            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    protected void afterGame(){
        executorService.shutdown();
        gameOverFlag = true;
        System.out.println("Game Over!");
        if (Main.music){
            MusicThread end = new MusicThread("src/videos/game_over.wav");
            end.start();

            bgm.end();
            if(isboss) {
                bossbgm.end();
            }
        }
        System.out.println("*******************************************");
        System.out.println("                 得分排行榜                  ");
        System.out.println("*******************************************");


        //dao.showAllRecord();
        scoreboard board = new scoreboard(dao.getAllRecord());
        if (Main.easy){
            board.setMode("简单");
        }else if(Main.medium){
            board.setMode("普通");
        }else if(Main.difficult){
            board.setMode("困难");
        }
        Main.cardPanel.add(board.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
        //保存弹窗
        String win = JOptionPane.showInputDialog("请输入您的名字");
        //System.out.println(win);
        //输入以后更新排行榜
        if(win != null){
            this.keep_record(win);
            board.refresh();
//            scoreboard board_new = new scoreboard(dao.getAllRecord());
//            Main.cardPanel.add(board_new.getMainPanel());
//            Main.cardLayout.last(Main.cardPanel);
        }
        while(!board.isrestart){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(board.isrestart){

                Main.cardLayout.next(Main.cardPanel);
            }
        }
        board.isrestart = false;
        Runnable st = () -> {
                    while (!Main.ischoosed) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException E) {
                            throw new RuntimeException(E);
                        }
                    }
                };
                Thread startthread = new Thread(st);
                startthread.start();
                try {
                    startthread.join();
                } catch (InterruptedException E) {
                    throw new RuntimeException(E);
                }
                if (Main.easy) {
                    Main.game = new easyGame();
                } else if (Main.medium) {
                    Main.game = new mediumGame();
                } else if (Main.difficult) {
                    Main.game = new hardGame();
                }
                Main.cardPanel.add(Main.game);
                Main.cardLayout.last(Main.cardPanel);
                HeroAircraft.getInstance().restart();
                Main.game.action();
    }
    protected void playMusic(){
        if (Main.music){
            bgm.setloop();
            bgm.start();
        }
    }
    protected void generateAircraft(){
        System.out.println(time);
        // 新敌机产生
        generateBoss();


        if (enemyAircrafts.size() < enemyMaxNumber) {
            double prob = Math.random();
            if(prob < 0.6) {

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

    abstract protected void generateBoss();
//        if (this.score != 0 && this.score >= threhold * chapter  && !isboss) {
//            Factory = new BossFactory();
//            enemyAircrafts.add(Factory.createEnemy());
//            isboss = true;
//            this.chapter += 2;
//
//
//            if (Main.music){
//                bossbgm = new MusicThread("src/videos/bgm_boss.wav");
//                bossbgm.start();
//                bossbgm.setloop();
//            }
//
//        }


    protected boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    protected void shootAction() {
        // TODO 敌机射击
        for (enemyAircraft plane : enemyAircrafts) {
            enemyBullets.addAll(plane.shoot());
        }

    }

    protected void heroShootAction(){
        heroBullets.addAll(heroAircraft.shoot());
    }

    protected void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    protected void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    protected void propMoveAction()
    {
        for(Prop craft: MyProp){
            craft.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    protected void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet: enemyBullets){
            if(bullet.notValid()){
                continue;
            }
            if (heroAircraft.crash(bullet)){
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }

            for (enemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet) ) {
                    // 敌机撞击到英雄机子弹
                    // 子弹击中音效
                    if (Main.music) {
                        new MusicThread("src/videos/bullet_hit.wav").start();
                    }
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        if (enemyAircraft instanceof BossEnemy){
                            afterBossEnd();

                        }
                        // TODO 获得分数，产生道具补给
                        enemyAircraft.aftercrash(MyProp);
                        enemyAircraft.beingShooted();
                        //score += 20;放在postProcessAction中
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (Prop craft: MyProp) {
            if (craft.notValid()){
                continue;
            } else if (heroAircraft.crash(craft) || craft.crash(heroAircraft)){
                if(Main.music){
                    new MusicThread("src/videos/get_supply.wav").start();
                }

                craft.aftercrash(heroAircraft);
                craft.vanish();
            }
        }
    }

    protected void keep_record(String name){
        scoreRecord record = new scoreRecord(this.score, name);
        dao.addRecord(record);

    }

    protected void afterBossEnd(){
        this.isboss = false;
        System.out.println("Boss Destroyed!");
        if (Main.music){
            bossbgm.end();
        }
    }






    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    protected void postProcessAction() {
        for (enemyAircraft aircraft : enemyAircrafts){
            if(aircraft.isShooted()){
                this.score += scoreplus;
            }
        }
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);

        MyProp.removeIf(AbstractFlyingObject::notValid);

    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, MyProp);
        paintImageWithPositionRevised(g, enemyAircrafts);


        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);


        //绘制得分和生命值
        paintScoreAndLife(g);

        //绘制血量条
        printHeroHealthBar(g);

    }
    private void printHeroHealthBar(Graphics g) {
        int barWidth = heroAircraft.getWidth();
        int barHeight = 5;
        int x = heroAircraft.getLocationX() - (heroAircraft.getWidth() / 2);
        int y = heroAircraft.getLocationY() + (heroAircraft.getHeight() / 2);

        // 绘制血条底色
        g.setColor(Color.GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        int hp = heroAircraft.getHp();
        int maxHp = heroAircraft.getMaxHp();
        int fillWidth = hp * barWidth / maxHp; // 计算填充宽度
        Color fillColor = hp > maxHp / 4 ? Color.GREEN : Color.RED;

        // 绘制血条填充色
        g.setColor(fillColor);
        g.fillRect(x, y, fillWidth, barHeight);

        // 绘制血条边框
        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);
    }
    protected void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    protected void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
