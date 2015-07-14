package testxml;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.nio.charset.Charset;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zoo88115
 */
public class Emotion_Dictionary {
    String text;
    Document xml;
    public Emotion_Dictionary(String text){
        this.text=text;
    }
    public void writeXML(){
        Document doc = DocumentHelper.createDocument();
        Element root=doc.addElement("wordsegmentation");
        root.addAttribute("version","0.1");
        
        Element root_option=root.addElement("option");
        root_option.addAttribute("showcategory","1");
        
        Element root_authentication=root.addElement("authentication");
        root_authentication.addAttribute("username","miproject101");
        root_authentication.addAttribute("password","mi101project");
        
        Element root_text=root.addElement("text");
        root_text.addText(text);
        this.xml=doc;
        //System.out.println(doc.asXML());//測試xml結果
        try{
            // 儲存 XML 檔案：
            FileWriter fw = new FileWriter("C:\\Users\\zoo88_000\\Desktop\\temp.xml"); // 可自訂
            // 下面這行：預設自動換行、Tab 為 2 個空白
            // OutputFormat of = OutputFormat.createPrettyPrint(); // 格式化XML
            OutputFormat of = new OutputFormat(); // 格式化XML
            of.setIndentSize(4); // 設定 Tab 為 4 個空白
            of.setNewlines(true);// 設定 自動換行
            XMLWriter xw = new XMLWriter(fw, of);
            xw.write(doc);
            xw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
}