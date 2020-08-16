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

public class catchOne2 {
    public static void main(String arge[]) {}
    /**
     * 2.每个样本中中只含有文本内容
     */
    @Test
    public  void code() {
        int sum = 0;//计数txt文件
        int sum2 = 0;//计数txt文件
        int sum3 = 0;//计数txt文件
        int sum4 = 0;//计数txt文件
            try {
            	//文件夹
                File f = new File("F:/APDA");
//                //文件夹下的文件
                File[] tempList = f.listFiles();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                //每个样本中中只含有文本内容的文件
                File ff = new File("D:/result/onlyString.xml");
                if (!ff.exists()) {
                	ff.createNewFile();
				}
                File ff2 = new File("D:/result/onlyString2.xml");
                if (!ff2.exists()) {
                	ff2.createNewFile();
                }
                File ff3 = new File("D:/result/onlyString3.xml");
                if (!ff3.exists()) {
                	ff3.createNewFile();
                }
                File ff4 = new File("D:/result/all.xml");
                if (!ff4.exists()) {
                	ff4.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(ff));
                BufferedWriter bw2 = new BufferedWriter(new FileWriter(ff2));
                BufferedWriter bw3 = new BufferedWriter(new FileWriter(ff3));
                BufferedWriter bw4 = new BufferedWriter(new FileWriter(ff4));
                for (File file : tempList) {
                	if (isXml(file)) {
//                	 File file=new File("F:/APDA/00bc24cb4ffcfe779976091b451164fb.xml");
                	Document doc = builder.parse(file);
                    NodeList nl = doc.getElementsByTagName("document");//获得item里面的所有结点数据
                    System.out.println(nl.getLength());
                    for (int i =0; i <nl.getLength(); i++) {
                        if (doc.getElementsByTagName("document").item(i)!=null) {
                        if(doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue()!=null){
                        String Line = doc.getElementsByTagName("document").item(i).getFirstChild().getNodeValue();
                        String writeLine =  repalceBalank(Line);
                        bw4.write(writeLine);//注意去除字符串首尾空格
                        sum4++;
                        if (!isMail2(writeLine)&&!isSpecallyCode(writeLine)&&!islink(writeLine)&&!isSpecallyCode(writeLine)) {
                        		bw.write(writeLine);//注意去除字符串首尾空格
                                sum++;
                        }
                        if(isMail(writeLine)&&containsEmoji(writeLine)){
                        	//3.每个样本中含有文本内容+@+表情
                    		bw2.write(writeLine);//注意去除字符串首尾空格
                            sum2++;
                    }
                        if (islink(writeLine)&&(!isSpecallyCode(writeLine))&&(!isMail(writeLine))) {
                    	//4.每个样本中只含有文本内容+网址链接
                        	System.out.println(writeLine);
                    	bw3.write(writeLine);//注意去除字符串首尾空格
                        sum3++;
					}
                        }}
                   }
                    System.out.println("1写了"+sum+"个~");//计评论数
                    System.out.println("2写了"+sum2+"个~");//计评论数
                    System.out.println("3写了"+sum3+"个~");//计评论数
                }}
                    bw2.flush();//将缓冲区数据推入目标文件
                    bw2.close();
                    bw.flush();//将缓冲区数据推入目标文件
                    bw.close();
                    bw3.flush();//将缓冲区数据推入目标文件
                    bw3.close();
                    bw4.flush();//将缓冲区数据推入目标文件
                    bw4.close();
                    //没有写入,删除
                    if (sum==0) {
    					System.out.println(ff.delete());
    				}
               
                
               } catch (Exception e) {   
                e.printStackTrace();
               }
	}
    
    
    public Boolean isXml(File file) {
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
    	Pattern p = Pattern.compile("[0-9a-zA-Z:@/]");
    	Matcher m = p.matcher(s);
    	if (m.find()) {
    		return true;
    	}
    	return false;
    	
    }
    public static Boolean isMail2(String s) {
    	Pattern p = Pattern.compile("[@]");
    	Matcher m = p.matcher(s);
    	if (m.find()) {
    		return true;
    	}
    	return false;
    	
    }
    
  public String repalceBalank(String str) {
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
     * 有特殊字符
     * @param s
     * @return
     */
    public static Boolean isSpecallyCode(String s) {
		Pattern p = Pattern.compile("[🙂ج 👀😪😑❤خ🙄ا🌺🎵🎶🌺🐝🍯🌧🍃🎶👌😒💖😄😐💗😇✋️🌹😅🍺😉😞☝🐧😳😕😰💛😖😻👅🍓😈🐶🙆😊🤨🤥🤤🦁🤕💕ﷺ💕💃👊😴🎤🤧🐜😜🤡😿✨💪😣😶ج💔💜🎼🏃👋🏼😔💚💙👏🔕🚶🏽✌🏼🐘🖤☺😆🥀🤣👧🌼😭😘😩😢🙈🙊🏂😬😎🤙🏾🔥💋👍🤗🙌😷☻😹🤫🌚♥ 🌷🌹🌷🌟🌹💐🌱🌷⋱‿🌸⁀⋱‿🌸⁀⋱‿😌🙏🏻😂😍🙋🌹😁😋🤔\\\\\\\\u200d]");
		Matcher m = p.matcher(s);
		if (m.find()) {
//			System.out.println("isSpecallyCode存在");
			return true;
		}
//		System.out.println("isSpecallyCode不存在");
		return false;
	
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
	
	public static Boolean isArabic(String str) {
		String reg = "[\\u0600-\\u06FF]";
		return str.replaceAll(" ", "").matches(reg);
	}
}
