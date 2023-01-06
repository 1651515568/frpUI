package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements ActionListener {

    private MainFrame mainFrame;
    private FirstPanel firstPanel;
    private JButton go;
    //下面定义为了切换面板方便
    private SecondPanel secondPanel;
    private ThirdPanel thirdPanel;
    public void setSecondPanel(SecondPanel secondPanel) {
        this.secondPanel = secondPanel;
    }
    public SecondPanel getSecondPanel() {
        return secondPanel;
    }
    public FirstPanel getFirstPanel() {
        return firstPanel;
    }
    public void setFirstPanel(FirstPanel firstPanel) {
        this.firstPanel = firstPanel;
    }
    public ThirdPanel getThirdPanel() {
        return thirdPanel;
    }
    public void setThirdPanel(ThirdPanel thirdPanel) {
        this.thirdPanel = thirdPanel;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public MainFrame() {
        mainFrame=this;
        firstPanel=new FirstPanel(mainFrame);
        secondPanel=new SecondPanel(mainFrame);
        firstPanel.setBounds(0, 0, 400, 800);
        this.add(firstPanel);
        firstPanel.setEnabled(true);
        //firstPanel.setVisible(true);
        this.add(secondPanel);
        this.setTitle("多面板测试");
        this.setSize(400, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
