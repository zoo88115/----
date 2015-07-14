/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testxml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zoo88115
 */
public class TestXML {

    /**
     * @param args the command line arguments
     */
    public static String[] statement={"美國8日接連發生三起電腦系統當機事件，特別是紐約證交所電腦系統出包，導致暫停交易近4小時，成為歷來因這類問題而暫停交易最久的一次，令本已擔心中國大陸股災和希臘債務危機的投資者更加緊張不安。\n" +
"面對接二連三的系統當機，國土安全部、聯邦調查局（FBI）等單位立刻出來滅火，稱這些事件應非出自外部網路攻擊，也非某人的惡意行為導致，並強調三起當機事件並無關聯。\n" +
"然而，國際駭客組織「匿名者」（Anonymous）的官方「推特」帳號「YourAnonNews」，早在7日就曾發文預測：「不知明天華爾街是否會有壞事發生…且拭目以待。」這也使得陰謀論之說不脛而走。\n" +
"在8日這一天，美國最大民航公司「聯合航空」先是因內部系統的路由器故障，一度停飛美國國內所有班機，2個小時後才恢復正常。估計800多個航班延誤，60個航班被取消，旅客怨聲載道。這是聯航自6月2日以來，第二次發生內部系統故障，迫使班機停飛。\n" +
"無獨有偶，紐約證交所上午11:32（台灣時間8日晚上11:32）也因「技術問題」宣布暫停交易，經緊急修護，下午3:10左右恢復作業。當天上午美股一開盤就因希臘債務危機、中國大陸股災而出現賣壓，偏偏又碰上電腦當機，股價更是一蹶不振，三大指數終場均大跌1.5％以上。\n" +
"同一時間，《華爾街日報》網頁也因內部系統問題，網站一度無法開啟。紐約證交所稱，7日晚才剛更新使用軟體，測試也無問題，但還是不幸技術故障。不僅暫停所有場內交易，也取消了所有電子下單。所幸在紐約證交所掛牌的股票，仍可在美國其他交易所，如那斯達克正常買賣。\n" +
"紐約證交所2010年曾因為硬體系統出問題，發生「閃崩」，道瓊工業指數在10分鐘內狂洩600點。前年8月，以科技股為主的那斯達克交易市場同樣因技術問題暫停交易3小時。這些凸槌暴露電子交易系統是何等脆弱不堪，也對投資人造成不少困擾。"};
    
    public static void main(String[] args) {
        // TODO code application logic here
        SegChinese seg=new SegChinese();
        String url="C:\\Users\\zoo88_000\\Documents\\NetBeansProjects\\TestXML\\Article\\";
        try{
            ArrayList<SegmentStatus> arrayList= new ArrayList<SegmentStatus>();
            ArrayList<SegmentStatus> StoreArrayList= new ArrayList<SegmentStatus>();
            
            ReadFile readFile=new ReadFile();//讀檔
            StoreArrayList=readFile.getFile("C:\\Users\\zoo88_000\\Desktop\\123.txt");//獲取舊有斷詞
            
            for(int i=1;i<=50;i++){
                String noURL=url+i+".txt";
                System.out.println("目前讀取："+noURL);
                String temp=readFile.getFileString(noURL);
                System.out.println(temp);
                arrayList.clear();
                arrayList=seg.getSegment(new String[]{temp});
                Integrate(StoreArrayList,arrayList);
                Collections.sort(StoreArrayList);
            }
            writeFile(StoreArrayList);
        }
        catch(Exception e){
            System.out.println("系統發生錯誤，請重新執行!");
        }
        /*這段是使用中研院的api
        Emotion_Dictionary emo=new Emotion_Dictionary(statement[0]);
        emo.writeXML();
        TcpClient tcp=new TcpClient(emo.xml);
        tcp.tcprun();
        */
    }
    public static void Integrate(ArrayList<SegmentStatus> oldArrayList,ArrayList<SegmentStatus> newArrayList){
        for(SegmentStatus i:newArrayList){
            int check=0;
            for(SegmentStatus j:oldArrayList){
                if(j.compareTo(i)==0){
                    check=1;//已存在
                    j.setNewValue(i.time);
                    break;//後面不必再檢查，加快速度
                }
            }
            if(check!=1){
                oldArrayList.add(i);
            }
        }
    }
    public static void writeFile(ArrayList<SegmentStatus> arrayList){
        try{
            File file = new File("C:\\Users\\zoo88_000\\Desktop\\123.txt");
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "big5")); // 指點編碼格式，以免讀取時中文字符異常
            fw.write("斷詞\t次數\t篇數");
            fw.newLine();
            for(SegmentStatus d:arrayList){
                fw.write(d.echoString());
                fw.newLine();
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e){
            
        }
    }
}
