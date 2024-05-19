package edu.hitsz.application;

import edu.hitsz.Game.Game;
import edu.hitsz.Game.easyGame;
import edu.hitsz.Game.hardGame;
import edu.hitsz.Game.mediumGame;
import edu.hitsz.SwingDemo;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final CardLayout cardLayout = new CardLayout(0,0);
    public static final JPanel cardPanel = new JPanel(cardLayout);

    public static boolean ischoosed = false;
    public static boolean easy = false;
    public static boolean medium = false;
    public static boolean difficult = false;

    public static boolean music = true;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static Game game;


    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cardPanel);

        Runnable st = () -> {
            SwingDemo start = new SwingDemo();
            cardPanel.add(start.getMainPanel());
            frame.setVisible(true);
            while (!ischoosed) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread startthread = new Thread(st);
        startthread.start();
        try {
            startthread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        if (easy){
            game = new easyGame();
        } else if (medium){
            game = new mediumGame();
        } else {
            game = new hardGame();
        }
        cardPanel.add(game);
        cardLayout.next(cardPanel);
        game.action();
    }

}
