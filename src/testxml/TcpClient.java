/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testxml;

import java.io.InputStream;
import java.net.*;
import java.io.*;
import org.dom4j.Document;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;

/**
 *
 * @author zoo88115
 */
public class TcpClient {
    public static int port = 1501; // 設定傳送埠為 1501。
    public static String address="140.109.19.104";
    public static String xml;
    public TcpClient(Document doc){
        this.xml=doc.asXML();
    }
    public void tcprun(){
        try{
            String xml2=new String(xml);
            System.out.println(xml2);
            Socket socket = SocketFactory.getDefault().createSocket();
            SocketAddress remoteaddr = new InetSocketAddress(address, port);
            socket.connect(remoteaddr, 30000);//time out 30
            // send rawText to server
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "Big5");
            PrintWriter pw = new PrintWriter(osw);
            pw.println(xml);
            pw.flush();
            // get return text
            String temp="";
            while(true){
                InputStreamReader isr = new InputStreamReader(socket.getInputStream(), "Big5");
                if(isr.ready()==true){
                    BufferedReader br = new BufferedReader(isr);
                    temp=br.readLine();
                    break;
                }
                else
                {
                    try{
                        Thread.sleep(1000);
                        System.out.println("等待回應");
                    }
                    catch(Exception e){
                        
                    }
                }
            }
            socket.close();
            System.out.println(temp);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
