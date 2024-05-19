package edu.hitsz;
import javax.swing.*;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;

public class SwingDemo {
    private JPanel MainPanel;
    private JComboBox comboBox1;
    private JLabel musicLabel;
    private JButton easymode;
    private JButton mediummode;
    private JButton difficultmode;

    public SwingDemo() {
        easymode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Main.ischoosed = true;
                Main.easy = true;
            }
        });
        mediummode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Main.ischoosed = true;
                Main.medium = true;
            }
        });
        difficultmode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg4.jpg"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Main.ischoosed = true;
                Main.difficult = true;
            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(comboBox1.getSelectedItem().equals("开")){
                        Main.music = true;
                    }
                    else{
                        Main.music = false;
                    }
                }
            }
        });

    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
}




