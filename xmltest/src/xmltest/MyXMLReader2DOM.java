package xmltest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;   

public class MyXMLReader2DOM {
    public static void main(String arge[]) {
    }
    
    @Test
    public  void copy() {
        int sum = 0;//计数txt文件
            try {
                File f = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\APDA\\00bc24cb4ffcfe779976091b451164fb.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(f);
                NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
                File ff = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\00bc24cb4ffcfe779976091b451164fb.xml");
                ff.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
                for (int i =1; i <=nl.getLength(); i++) {
                    if (doc.getElementsByTagName("document").item(i)!=null) {
                    if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
                    	String writeLine = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
                    	System.out.println(writeLine);
                    	if (isProbablyArabic2(writeLine)) {
                    		bw.write(writeLine);//注意去除字符串首尾空格
                            bw.newLine();
                            sum++;
                            System.out.println("写好了第"+sum+"个~");//计评论数
                    	}
                        
                    }}
               }
                bw.flush();//将缓冲区数据推入目标文件
                bw.close();
                //没有写入,删除
                if (sum==0) {
					System.out.println(ff.delete());
				}
               } catch (Exception e) {   
                e.printStackTrace();
               }
	}
    
    
    /**
	 * 字符串中是否包含某個字段
	 * @param str 字符串
	 * @param include 某個字段
	 * @return
	 */
	public static boolean incliude( String str,String include) {
		boolean status = str.contains(include);  
		if(status){  
			return true;
		}
		return false;
	}
  
    /**
     * 有阿拉伯數文
     * @param s
     * @return
     */
    public static boolean isProbablyArabic(String s) {
        for (int i = 0; i < s.length();) {
            int c = s.codePointAt(i);
            System.out.println(s.length());
            if (c >= 0x0600 && c <= 0x06E0) {
                return true;
                }
            i += Character.charCount(c);            
        }
        return false;
      }
    /**
     * 只有阿拉伯文
     * @param s
     * @return
     */
    public static boolean isProbablyArabic2(String s) {
    	if (!isSpecallyCode(s)&&!code1(s)&&!code2(s)) {
			return true;
		}
    	return false;
    }
    
    /**
     * 有特殊字符
     * @param s
     * @return
     */
    public static Boolean isSpecallyCode(String s) {
		Pattern p = Pattern.compile("[🍃💗😇✋️🌹🌷🌹🌷🌟🌹💐🌱🌷⋱‿🌸⁀⋱‿🌸⁀⋱‿😌🙏🏻😂😍🙋🌹😁😋🤔\\\\u200d]");
		Matcher m = p.matcher(s);
		if (m.find()) {
			return true;
		}
		return false;
	
    }
    /**
     * 是否包含#!,.?ا
     * @param s
     * @return
     */
    public static Boolean code1(String s) {
		Pattern p = Pattern.compile("[\\\\n\\\\\\\"\\\\'#!,.?]");
		Matcher m = p.matcher(s);
		if (m.find()) {
			return true;
		}
		return false;
	
    }
    
    /**
     * 郵件
     * @param s
     * @return
     */
    public static Boolean code2(String s) {
    	Pattern p = Pattern.compile("[0-9a-zA-Z:@/]");
    	Matcher m = p.matcher(s);
    	if (m.find()) {
    		return true;
    	}
    	return false;
    	
    }
    
    
}
