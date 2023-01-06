package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                System.out.print(">");
                if(sc.hasNextLine()){
                    String input = sc.nextLine();
                    if(input.contains("proxy")){
                        String[] cmd = input.split(" ");
                        threadOpen("x.x.x.x:7000",cmd[1],cmd[2],cmd[3]);
                    }else if(input.contains("socket")){
                        System.out.println("no fun");
                    }else if(input.contains("exit")){
                        System.exit(0);
                    }else{
                        System.out.println("error");
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("no code");
            }catch (Exception e){
                System.out.println(e);
            }

        }

//        threadOpen("x.x.x.x:7000","mc","25565","6666");
//        threadOpen("x.x.x.x:7000","ww","2555","6667");
//        threadOpen("x.x.x.x:7000","ww1","255","6668");
    }
    public static void threadOpen(String vps,String name,String localPort,String remotePort){
        String cmd = String.format("C:\\Users\\lz\\Desktop\\frp_0.46.0_windows_amd64\\frpc.exe tcp -s %s -n %s  -l %s -r %s",vps,name,localPort,remotePort);
        new Thread(() -> startPortProxy(cmd)).start();
    }
    public static void startPortProxy(String cmd){
        Process p;
        try
        {
            p = Runtime.getRuntime().exec(cmd);
            InputStream fis=p.getInputStream();
            InputStreamReader isr=new InputStreamReader(fis,"gbk");
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

