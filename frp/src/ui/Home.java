package ui;

import javax.swing.*;
import java.awt.*;

public class Home {
    private final Home home = this;
    private final Forwards forwards = new Forwards(home);
    private final Sockets sockets = new Sockets(home);
    private final JPanel homePanel = new JPanel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("frp");
        Home home = new Home();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        frame.setLayout(null);
        frame.setBounds(800,200,360,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(home.homePanel);
        frame.setVisible(true);
    }
    public Home() {
        homePanel.setLayout(null);
        homePanel.setBounds(0,0,360,600);
        homePanel.setBackground(Color.green);
        JButton forward = new JButton();
        forward.setText("端口转发");
        JButton socket = new JButton();
        socket.setText("sockets");
        forward.setBounds(0,0,175,35);
        socket.setBounds(175,0,175,35);
        homePanel.add(forward);
        homePanel.add(socket);
        homePanel.add(forwards.getForwardPanel());
        homePanel.add(sockets.getSocketsPanel());
        forward.addActionListener(e ->{home.ForwardPortAction();});
        socket.addActionListener(e ->{home.socketsAction();});
    }
    public void ForwardPortAction() {
        sockets.getSocketsPanel().setVisible(false);
        forwards.getForwardPanel().setVisible(true);
    }
    public void socketsAction(){
        forwards.getForwardPanel().setVisible(false);
        sockets.getSocketsPanel().setVisible(true);
    }
}
