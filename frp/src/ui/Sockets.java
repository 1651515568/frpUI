package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


public class Sockets{
    private Home home;
    private JPanel socketsPanel;
    public static String configPath;
    public static String vps;

    public JPanel getSocketsPanel() {
        return socketsPanel;
    }
    public void setSocketsPanel(JPanel socketsPanel) {
        this.socketsPanel = socketsPanel;
    }
    public Sockets(Home home) {
        configPath = "C:\\Users\\lz\\Desktop\\frp_0.46.0_windows_amd64";
        vps = "101.34.164.131";
        setSocketsPanel(new JPanel());
//        getSocketsPanel().setBackground(Color.yellow);
        getSocketsPanel().setBounds(0,35,360,533);
        socketsPanel.setLayout(null);

        JLabel labelLocalPort = new JLabel();
        JTextField valueLocalPort = new JTextField();
        JLabel showInter = new JLabel();
        JButton startSockets = new JButton();
        JButton overSockets = new JButton();
        JTextArea showInfo = new JTextArea();
        JScrollPane scrollShowInfo = new JScrollPane(showInfo);

        labelLocalPort.setText("开放端口");
        valueLocalPort.setText("23333");
        showInter.setText("sockets: " + vps + ":" + valueLocalPort.getText());
        startSockets.setText("启动sockets");
        overSockets.setText("关闭sockets");

        showInter.setBackground(Color.red);

        labelLocalPort.setBounds(5,5,60,25);
        valueLocalPort.setBounds(labelLocalPort.getX() + 60,labelLocalPort.getY(),66,labelLocalPort.getHeight());
        showInter.setBounds(valueLocalPort.getX() + 80,labelLocalPort.getY(),195,labelLocalPort.getHeight());
        startSockets.setBounds(5,valueLocalPort.getY() + 30,335,labelLocalPort.getHeight());
        overSockets.setBounds(5,startSockets.getY() + 30,335,labelLocalPort.getHeight());
        scrollShowInfo.setBounds(5,overSockets.getY() + 27,335,430);

        socketsPanel.add(labelLocalPort);
        socketsPanel.add(valueLocalPort);
        socketsPanel.add(showInter);
        socketsPanel.add(startSockets);
        socketsPanel.add(overSockets);
        socketsPanel.add(scrollShowInfo);

        startSockets.addActionListener(e ->{openThread(valueLocalPort.getText(),showInfo,showInter);});
        overSockets.addActionListener(e ->{killAllThread(showInfo);});
        showInter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                a(showInter,showInfo);
            }
        });
//        overSockets.addActionListener(e ->{killAllThread(interInfo);});

    }
    public static void a(JLabel label,JTextArea info){
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(label.getText());
        clip.setContents(tText, null);
        info.append(label.getText() + " 已复制到剪切板\n");
    }
    public static void openThread(String port,JTextArea showInfo,JLabel label){
        label.setText("sockets: " + vps + ":" + port);
        new Thread(() -> startSockets(port,showInfo)).start();
    }
    public static void startSockets(String port,JTextArea showInfo) {
        String filepath = configPath + "\\sockets.ini";
        filepath = "sockets.ini";
        File file = new File(filepath);
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String fileString = "";
            String line;
            while((line = br.readLine()) != null){
                if(line.contains("remote_port")){
                    line = "remote_port = " + port;
                }
                fileString = fileString + line + "\n";
            }
            try (FileWriter fileWriter = new FileWriter(filepath)) {
                fileWriter.append(fileString);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        Process p;
        try{
//            p = Runtime.getRuntime().exec(configPath + "\\frpc.exe -c " + configPath + "\\sockets.ini");
            p = Runtime.getRuntime().exec("frpc.exe -c " + "sockets.ini");
            InputStream fis=p.getInputStream();
            InputStreamReader isr=new InputStreamReader(fis,"gbk");
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while((line=br.readLine())!=null) {
//                System.out.println(line);
                showInfo.append(line + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
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
}
