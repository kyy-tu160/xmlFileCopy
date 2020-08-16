package xmltest;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
                        System.out.println(nl.getLength());
                        for (int i =0; i <nl.getLength(); i++) {
                            if (doc.getElementsByTagName("document").item(i)!=null) {
                            if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
                            String Line = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
                            String writeLine =  repalceBalank(Line);
                            //只有阿拉伯文
                            if (onlyArabic(writeLine)) {
                            		bw.write(writeLine);//注意去除字符串首尾空格
                                    sum++;
                            }
                            //只有符号
                            if(!isArabic(writeLine)){
                        		bw2.write(writeLine);//注意去除字符串首尾空格
                                sum2++;
                        }
                            //每个样本中含有文本内容+@+表情
                            if(isArabic(writeLine)&&containsEmoji(writeLine)&&isMail(writeLine)){
                            	//3.每个样本中含有文本内容+@+表情
                            	bw4.write(writeLine);//注意去除字符串首尾空格
                            	sum4++;
                            }
                            if(!isMail(writeLine)&&!containsEmoji(writeLine)){
                            	//3.每个样本中含有文本内容+@+表情
                            	bw5.write(writeLine);//注意去除字符串首尾空格
                            	sum5++;
                            }
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
//                File file = new File("F:\\APDA\\ffdfe4804d705408cf6866861c9a9017.xml");
            	
               
                
               } catch (Exception e) {   
                e.printStackTrace();
               }
	}
    
    /**
     * 存在阿拉伯文
     * @param str
     * @return
     */
    private static boolean isArabic(String str) {
		Pattern p = Pattern.compile("[\u0600-\u06FF]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
	}
	public static Boolean isXml(File file) {
    	String[] strArray = file.getName().split("\\.");
    int suffixIndex = strArray.length -1;
    if (strArray[suffixIndex].toUpperCase().equals("XML")) {
		return true;
	}
	return false;
	}
    /**
     * 郵件
     * @param s
     * @return
     */
    public static Boolean isMail(String s) {
    	return s.contains("@");
    }
    public static Boolean isMail2(String s) {
    	Pattern p = Pattern.compile("[@]");
    	Matcher m = p.matcher(s);
    	if (m.find()) {
    		return true;
    	}
    	return false;
    	
    }
    
  public static String repalceBalank(String str) {
	String dest = "";
	if (str!=null) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		dest = m.replaceAll("");
	}
	return dest;
	
}
    /**
             *网址链接
     * @param s
     * @return
     */
    public static Boolean islink(String s) {
		Pattern p = Pattern.compile("[.*?https.*?]");
		Matcher m = p.matcher(s);
		if (m.find()) {
			return true;
		}
//		System.out.println("isSpecallyCode不存在");
		return false;
	
    }
    
    
    /**
     * 字符串是否有表情
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }
    /**
	 * 是否只为阿拉伯文
	 * @param str
	 * @return
	 */
	public static Boolean onlyArabic(String str) {
		String reg = "[\\u0600-\\u06FF]+";
		return str.replaceAll(" ", "").matches(reg);
	}
}
