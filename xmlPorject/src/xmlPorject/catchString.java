package xmlPorject;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.platform.commons.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 * 分类生成五类文件
 * @author king
 *
 */
public class catchString {
    public static void main(String arge[]) {
    	code();
    }
    /**
     * 2.每个样本中中只含有文本内容
     */
    public static void code() {
        int sum = 0;//计数txt文件
        int sum2 = 0;//计数txt文件
        int sum3 = 0;//计数txt文件
        int sum4 = 0;//计数txt文件
        int sum5 = 0;//计数txt文件
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                File files = new File("F:\\APDA\\");
                File[]xmlFiles = files.listFiles();
                for (File file : xmlFiles) {
                	if (file.getName().substring(file.getName().indexOf(".")).toUpperCase().equals(".TXT")) {
                		continue;
        			}
                	String dirPath = "D:/result/"+file.getName().substring(0,file.getName().indexOf("."));
                	File f2 = new File(dirPath);
                	if (!f2.exists()) {
    					f2.mkdirs();
    				}
                	//每个样本中中只含有文本内容的文件
                    File ff = new File(dirPath+"/onlyString.txt");
                    if (!ff.exists()) {
                    	ff.createNewFile();
    				}
                    File ff2 = new File(dirPath+"/onlyChar.txt");
                    if (!ff2.exists()) {
                    	ff2.createNewFile();
                    }
                    File ff3 = new File(dirPath+"/all.txt");
                    if (!ff3.exists()) {
                    	ff3.createNewFile();
                    }
                    File ff4 = new File(dirPath+"/StringEmojiMail.txt");
                    if (!ff4.exists()) {
                    	ff4.createNewFile();
                    }
                    File ff5 = new File(dirPath+"/StringLink.txt");
                    if (!ff5.exists()) {
                    	ff5.createNewFile();
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(ff2));
                    BufferedWriter bw3 = new BufferedWriter(new FileWriter(ff3));
                    BufferedWriter bw4 = new BufferedWriter(new FileWriter(ff4));
                    BufferedWriter bw5 = new BufferedWriter(new FileWriter(ff5));
    					if (isXml(file )) {
                    	Document doc = builder.parse(file);
                        NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
                        for (int i =0; i <nl.getLength(); i++) {
                            if (doc.getElementsByTagName("document").item(i)!=null) {
                            if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
                            String Line = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
                            String writeLine =  repalceBalank(Line);
                            //只有阿拉伯文
                            bw.write(filterString(writeLine));//注意去除字符串首尾空格
                            sum++;
                            //只有符号
                        		bw2.write(filterChar(writeLine));//注意去除字符串首尾空格
                                sum2++;
                            //每个样本中含有文本内容+@+表情
                            	bw4.write(filterEmoji(writeLine));//注意去除字符串首尾空格
                            	sum4++;
                            	//3.每个样本中含有文本内容+@+表情
                            	bw5.write(filterString(writeLine)+getAllLink(writeLine));//注意去除字符串首尾空格
                            	sum5++;
                        	//4.每个样本中只含有文本内容+网址链接
	                        	bw3.write(writeLine);//注意去除字符串首尾空格
	                            sum3++;
                            }}
                       }
                        System.out.println("1写了"+sum+"个~");//计评论数
                        System.out.println("2写了"+sum2+"个~");//计评论数
                        System.out.println("3写了"+sum3+"个~");//计评论数
                        System.out.println("4写了"+sum4+"个~");//计评论数
                        System.out.println("5写了"+sum5+"个~");//计评论数
                    }
                        bw2.flush();//将缓冲区数据推入目标文件
                        bw2.close();
                        bw.flush();//将缓冲区数据推入目标文件
                        bw.close();
                        bw3.flush();//将缓冲区数据推入目标文件
                        bw3.close();
                        bw4.flush();//将缓冲区数据推入目标文件
                        bw4.close();
                        bw5.flush();//将缓冲区数据推入目标文件
                        bw5.close();
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
     * 获取所以链接文本
     * @param Str
     * @return
     */
    private static String getAllLink(String Str) {
		String msg = "";
		Pattern pattern = Pattern.compile("[https]{4}\\:\\/\\/[a-z]*(\\.[a-zA-Z]*)*(\\/([a-zA-Z]|[0-9])*)*\\s?");
		Matcher matcher = pattern.matcher(Str);
		if(matcher.find()){
			msg=matcher.group(0);
		}
	return msg;
	}
    
    /**
     * 获取纯符号
     * @param writeLine
     * @return
     */
    private static String filterChar(String source) {
    	source = source.replaceAll("[\\u0600-\\u06FF]", "");
	 return source;
    }
    
    /**
     * 替换所有换行
     * @param str
     * @return
     */
	private static String repalceBalank(String str) {
    	String dest = "";
	if (str!=null) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		dest = m.replaceAll("");
	}
	return dest;}
	/**
private static boolean isEmojiCharacter(char codePoint) {
	
    return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
            || (codePoint == 0xD)
            || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
            || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
            || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
}

/**
 * 过滤emoji 或者 其他非文字类型的字符
 *
 * @param source
 * @return
 */
public static String filterEmoji(String source) {
    if (StringUtils.isBlank(source)) {
        return source;
    }
    StringBuilder buf = null;
    int len = source.length();
    for (int i = 0; i < len; i++) {
        char codePoint = source.charAt(i);
        if (!isEmojiCharacter(codePoint)) {
            if (buf == null) {
                buf = new StringBuilder(source.length());
            }
            buf.append(codePoint);
        }
    }
    if (buf == null) {
        return source;
    } else {
        if (buf.length() == len) {
            buf = null;
            return source;
        } else {
            return buf.toString();
        }
    }

}

	private static boolean isEmojiCharacter(char codePoint) {
	
    return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
            || (codePoint == 0xD)
            || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
            || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
            || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
}
    
    /**
     * 过滤阿拉伯文
     * @param source
     * @return
     */
    public static String filterString(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isArabic(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
    /**
     * 是否为阿拉伯文
     * @param codePoint
     * @return
     */
	private static boolean isArabic(char codePoint) {
		return String.valueOf(codePoint).matches("[\u0600-\u06FF]");
	}
	
/**
 * 是否为xml文件
 * @param file
 * @return
 */
public static Boolean isXml(File file) {
	String[] strArray = file.getName().split("\\.");
int suffixIndex = strArray.length -1;
if (strArray[suffixIndex].toUpperCase().equals("XML")) {
	return true;
}
return false;
}

private static String getAllAdress(String email){ 
	 String msg = ""; 

	 Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); 
	 Matcher m = p.matcher(email); 
	 while (m.find()) 
	 {
	 msg +=m.group();
	 }
	return msg;
	 }
}
