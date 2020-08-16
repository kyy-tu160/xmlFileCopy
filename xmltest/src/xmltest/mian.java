package xmltest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class mian {
        public static void main(String[] args) {
            try{
                File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
                FileReader fr=new FileReader(f); //fr对象 对应f的读出
                BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
                String shuchu=null;
                while((shuchu=br.readLine())!=null){
                	String[] list1 = shuchu.split(":::");
                    for (int i = 0; i < list1.length; i++) {
                    	System.out.println(list1[0]);
                    	System.out.println(list1[1]);
                    	System.out.println(list1[2]);
                    	System.out.println(list1[3]);
					}
                }
                br.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    
    /**
     * 2.每个样本中中只含有文本内容
     */
    @Test
    public  void code() {
        int sum = 0;//计数txt文件
            try {
            	//文件夹
                File f = new File("C:\\Users\\wuzai\\Desktop\\APDA");
                //文件夹下的文件
                File[] tempList = f.listFiles();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                //每个样本中中只含有文本内容的文件
                File ff = new File("C:\\Users\\wuzai\\Desktop\\onlyString.xml");
                if (!ff.exists()) {
                	ff.createNewFile();
				}
                
                for (File file : tempList) {
                	 String[] strArray = file.getName().split("\\.");
                     int suffixIndex = strArray.length -1;
                     if (!strArray[suffixIndex].toUpperCase().equals("XML")) {
						return;
					}
                	Document doc = builder.parse(file);
                    NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
                    
                    BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
                    for (int i =1; i <=nl.getLength(); i++) {
                        if (doc.getElementsByTagName("document").item(i)!=null) {
                        if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
                        	String writeLine = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
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
				}
               
                
               } catch (Exception e) {   
                e.printStackTrace();
               }
	}
    
    /**
     * 3.每个样本中含有文本内容+@+表情
     */
    @Test
    public  void codeAndFaceAndMail() {
    	int sum = 0;//计数txt文件
    	try {
    		//文件夹
    		File f = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\APDA\\");
    		//文件夹下的文件
    		File[] tempList = f.listFiles();
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		//每个样本中中只含有文本内容的文件
    		File ff = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\codeAndFaceAndMail.xml");
    		if (!ff.exists()) {
    			ff.createNewFile();
    		}
    		
    		for (File file : tempList) {
    			String[] strArray = file.getName().split("\\.");
    			int suffixIndex = strArray.length -1;
    			if (!strArray[suffixIndex].toUpperCase().equals("XML")) {
    				return;
    			}
    			Document doc = builder.parse(file);
    			NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
    			
    			BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
    			for (int i =1; i <=nl.getLength(); i++) {
    				if (doc.getElementsByTagName("document").item(i)!=null) {
    					if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
    						String writeLine = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
    						if (!incliude(writeLine, "http")) {
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
    		}
    		
    		
    	} catch (Exception e) {   
    		e.printStackTrace();
    	}
    }
    /**
     * 4.每个样本中只含有文本内容+网址链接
     */
    @Test
    public  void codeAndLink() {
    	int sum = 0;//计数txt文件
    	try {
    		//文件夹
    		File f = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\APDA\\");
    		//文件夹下的文件
    		File[] tempList = f.listFiles();
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		//每个样本中中只含有文本内容的文件
    		File ff = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\codeAndLink.xml");
    		if (!ff.exists()) {
    			ff.createNewFile();
    		}
    		
    		for (File file : tempList) {
    			String[] strArray = file.getName().split("\\.");
    			int suffixIndex = strArray.length -1;
    			if (!strArray[suffixIndex].toUpperCase().equals("XML")) {
    				return;
    			}
    			Document doc = builder.parse(file);
    			NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
    			
    			BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
    			for (int i =1; i <=nl.getLength(); i++) {
    				if (doc.getElementsByTagName("document").item(i)!=null) {
    					if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
    						String writeLine = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
    						if (!incliude(writeLine, "@")&&!isSpecallyCode(writeLine)) {
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
    		}
    		
    		
    	} catch (Exception e) {   
    		e.printStackTrace();
    	}
    }
    
    /**
     * 5.每个样本中含有非文本内容+文本内容(只经过解析)
     */
    @Test
    public  void all() {
    	int sum = 0;//计数txt文件
    	try {
    		//文件夹
    		File f = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\APDA\\");
    		//文件夹下的文件
    		File[] tempList = f.listFiles();
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		//每个样本中中只含有文本内容的文件
    		File ff = new File("C:\\Users\\Acer\\Desktop\\syt\\APDA\\all.xml");
    		if (!ff.exists()) {
    			ff.createNewFile();
    		}
    		BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
    		for (File file : tempList) {
    			String[] strArray = file.getName().split("\\.");
    			int suffixIndex = strArray.length -1;
    			if (!strArray[suffixIndex].toUpperCase().equals("XML")) {
    				return;
    			}
    			Document doc = builder.parse(file);
    			NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
    			for (int i =1; i <=nl.getLength(); i++) {
    				if (doc.getElementsByTagName("document").item(i)!=null) {
    					if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
    						String writeLine = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
    							bw.write(writeLine);//注意去除字符串首尾空格
    							bw.newLine();
    							sum++;
    							System.out.println("写好了第"+sum+"个~");//计评论数
    					}}
    			}
    			bw.flush();//将缓冲区数据推入目标文件
    			bw.close();
    			//没有写入,删除
    			if (sum==0) {
    				System.out.println(ff.delete());
    			}
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
		Pattern p = Pattern.compile("[🍃💗😇✋️🌹🌷🖤🌹🌷🌟🌹💐🌱🌷⋱‿🌸⁀⋱‿🌸⁀⋱‿😌🙏🏻😂😍🙋🌹😁😋🤔\\\\u200d]");
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
		Pattern p = Pattern.compile("[\\\\n\\\\\\\"\\\\'#!-,.?]");
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
