package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FirstPanel extends JPanel implements ActionListener{
    private JButton back;
    private JButton go;
    private MainFrame mainFrame;

    public FirstPanel(MainFrame mainFrame) {
        this.mainFrame=mainFrame;
        back = new JButton("返 回");
        go = new JButton("前进");
        this.setLayout(null);
        this.add(back);

        back.addActionListener(this);
        back.setActionCommand("back");
        go.addActionListener(this);
        go.setActionCommand("go");
        back.setBounds(30, 40,80, 40);
        System.out.println("大家好，我倒第一个面板来了");
        this.add(go);
        go.setBounds(230, 40, 80, 40);
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("go")) {
            System.out.println("已经将这个设置为了false");
            mainFrame.getFirstPanel().setEnabled(false);
            mainFrame.getFirstPanel().setVisible(false);
            //System.out.println(mainFrame.getFirstPanel().isValid());
            mainFrame.getSecondPanel().setVisible(true);
            mainFrame.getSecondPanel().setEnabled(true);
            //  mainFrame.validate();
            //  mainFrame.repaint();
        }else if (e.getActionCommand().equals("back")) {

        }

    }
}
