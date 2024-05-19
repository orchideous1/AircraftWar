package edu.hitsz;

import edu.hitsz.Game.Game;
import edu.hitsz.Game.easyGame;
import edu.hitsz.Game.mediumGame;
import edu.hitsz.Game.hardGame;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class scoreboard {
    private JPanel MainPanel;
    private JButton delete;
    private JTable databoard;
    private JLabel mode;
    private JLabel scoreboard;
    private JScrollPane tableScrollPanel;
    private JButton restart;

    public boolean isrestart = false;

//    public void setText(String text) {
////        mode.setText("难度："+ text);
////    }

    public scoreboard(String[][] data) {
        String[] columnName = {"名次","玩家名","成绩","时间"};
        String[][] datadefault = {
                {"1","","",""},
                {"2","","",""},
                {"3","","",""},
                {"4","","",""},
                {"5","","",""},
                {"6","","",""},
                {"7","","",""},
                {"8","","",""},
                {"9","","",""},
                {"10","","",""},
                {"11","","",""},
                {"12","","",""},
        };

        if (data == null) {
            data = datadefault;
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(data, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        databoard.setModel(model);
        tableScrollPanel.setViewportView(databoard);
        delete.addActionListener(e -> {
            int row = databoard.getSelectedRow();
            System.out.println(row);
            int result = JOptionPane.showConfirmDialog(delete,
                    "是否确定中删除？");
            if (JOptionPane.YES_OPTION == result && row != -1) {
                System.out.println(databoard.getValueAt(row, 0));
                Game.dao.deleteRecord((String) databoard.getValueAt(row, 0));

                model.removeRow(row);
                String[][] data_new = Game.dao.getAllRecord();
                DefaultTableModel model_new = new DefaultTableModel(data_new, columnName){
                    @Override
                    public boolean isCellEditable(int row, int col){
                        return false;
                    }
                };
                databoard.setModel(model_new);
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main.ischoosed = false;
                Main.easy = false;
                Main.medium = false;
                Main.difficult = false;
                System.out.println("restart");

                isrestart = true;

            }
        });
    }

    public void refresh(){
        String[][] data_new = Game.dao.getAllRecord();
        String[] columnName = {"名次","玩家名","成绩","时间"};
        DefaultTableModel model_new = new DefaultTableModel(data_new, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        databoard.setModel(model_new);
    }
    public void setMode(String mode) {
        System.out.println(mode);
        this.mode.setText("难度：" + mode);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public static void main(String[] args) {
        String[][] data = null;
        JFrame frame = new JFrame("scoreboard");
        frame.setContentPane(new scoreboard(data).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
