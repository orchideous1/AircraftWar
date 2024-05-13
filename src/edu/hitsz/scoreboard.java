package edu.hitsz;

import edu.hitsz.application.Game;

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
                model.removeRow(row);
                Game.dao.deleteRecord((String) databoard.getValueAt(row, 0));
            }
        });
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
