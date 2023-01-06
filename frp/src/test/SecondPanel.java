package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SecondPanel extends JPanel implements ActionListener {
    private JButton back;
    private JButton go;
    private MainFrame mainFrame;

    public SecondPanel(MainFrame mainFrame) {

        this.mainFrame=mainFrame;
        back = new JButton("second返回");
        go = new JButton("second前进");
        this.setLayout(null);
        this.add(back);

        back.addActionListener(this);
        back.setActionCommand("back");
        go.addActionListener(this);
        go.setActionCommand("go");
        back.setBounds(30, 40,100, 40);
        System.out.println("大家好，我倒第二个面板这里来了");
        this.add(go);
        go.setBounds(230, 40, 100, 40);
        this.setEnabled(false);
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("go")) {
            JOptionPane.showConfirmDialog(this, "大家好，我是第二个面板第二次点击引起的");

        }if (e.getActionCommand().equals("back")) {
            mainFrame.getFirstPanel().setEnabled(true);
            mainFrame.getFirstPanel().setVisible(true);
            //System.out.println(mainFrame.getFirstPanel().isValid());
            mainFrame.getSecondPanel().setVisible(false);
            mainFrame.getSecondPanel().setEnabled(false);
            System.out.println("已经将第一个面板社会自微ture");
        }
    }
}
