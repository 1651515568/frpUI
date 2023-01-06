package ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Forwards {
    private JPanel forwardPanel;

    private static boolean status;
    public JPanel getForwardPanel() {
        return forwardPanel;
    }
    public void setForwardPanel(JPanel forwardPanel) {
        this.forwardPanel = forwardPanel;
    }



    public Forwards(Home home) {
        setForwardPanel(new JPanel());
        forwardPanel.setLayout(null);
//        getForwardPanel().setBackground(Color.red);
        getForwardPanel().setBounds(0,35,360,533);
        JLabel labelName = new JLabel();
        JTextField valueName = new JTextField();
        JLabel labelLocalPort = new JLabel();
        JTextField valueLocalPort = new JTextField();
        JLabel labelRemotePort = new JLabel();
        JTextField valueRemotePort = new JTextField();
        JButton addForward = new JButton();
        JButton overForward = new JButton();
        JTextArea forwardInfo = new JTextArea();
        JTextArea interInfo = new JTextArea();
        JScrollPane scrollForwardInfo = new JScrollPane(forwardInfo);
        JScrollPane scrollInterInfo = new JScrollPane(interInfo);

//        scrollInterInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        labelName.setText("名称");
        labelLocalPort.setText("本地端口");
        labelRemotePort.setText("远程端口");
        addForward.setText("添加");
        overForward.setText("结束映射");

        labelName.setBounds(5,5,30,25);
        valueName.setBounds(labelName.getBounds().x + 30,labelName.getBounds().y,40,25);
        labelLocalPort.setBounds(valueName.getBounds().x + 50,5,60,25);
        valueLocalPort.setBounds(labelLocalPort.getBounds().x + 60,labelName.getBounds().y,60,25);
        labelRemotePort.setBounds(valueLocalPort.getBounds().x + 70,labelName.getBounds().y,60,25);
        valueRemotePort.setBounds(labelRemotePort.getBounds().x + 60,labelName.getBounds().y,60,25);
        addForward.setBounds(5,35,335,24);
        overForward.setBounds(5,60,335,24);
        scrollForwardInfo.setBounds(5,85,335,300);
        scrollInterInfo.setBounds(5,385,335,140);

        addForward.addActionListener(e ->{threadOpen(valueName,valueLocalPort,valueRemotePort,forwardInfo,interInfo);});
        overForward.addActionListener(e ->{killAllThread(interInfo);});

//        labelLocalPort.setForeground(Color.red);
//        labelLocalPort.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        forwardPanel.add(labelName);
        forwardPanel.add(valueName);
        forwardPanel.add(labelLocalPort);
        forwardPanel.add(valueLocalPort);
        forwardPanel.add(labelRemotePort);
        forwardPanel.add(valueRemotePort);
        forwardPanel.add(addForward);
        forwardPanel.add(overForward);
        forwardPanel.add(scrollForwardInfo);
        forwardPanel.add(scrollInterInfo);
    }
    public static void threadOpen(JTextField name,JTextField local,JTextField remote,JTextArea forward,JTextArea inter){
        String theName = name.getText();
        String vps = "101.34.164.131:7000";
        String localPort = local.getText();
        String remotePort = remote.getText();
//        String cmd = String.format("C:\\Users\\lz\\Desktop\\frp_0.46.0_windows_amd64\\frpc.exe tcp -s %s -n %s  -l %s -r %s",vps,theName,localPort,remotePort);
        String cmd = String.format("frpc.exe tcp -s %s -n %s  -l %s -r %s",vps,theName,localPort,remotePort);
        System.out.println(cmd);
        forward.append(theName + " : " + localPort + " -> " + remotePort + " >> " + vps.split(":")[0] + ":" + remotePort +"\n");
        new Thread(() -> startPortProxy(cmd,forward,inter)).start();
    }
    public static void killAllThread(JTextArea inter){
        System.out.println("thread");
        new Thread(() -> killAllFrp(inter)).start();
    }
    public static void killAllFrp(JTextArea infoArea){
        System.out.println("frp");
        Process p;
        try{
            p = Runtime.getRuntime().exec("taskkill /T /F /IM frpc.exe");
            InputStream fis=p.getInputStream();
            InputStreamReader isr=new InputStreamReader(fis,"gbk");
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                infoArea.append(line + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void startPortProxy(String cmd,JTextArea forward,JTextArea infoArea){
        Process p;
        try{
            p = Runtime.getRuntime().exec(cmd);
            InputStream fis=p.getInputStream();
            InputStreamReader isr=new InputStreamReader(fis,"gbk");
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while((line=br.readLine())!=null) {
                infoArea.append(line + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
