package xmlPorject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.platform.commons.util.StringUtils;

/**
 * 
 * @author king
 *
 */
public class filter {
	public static void main(String[] args) {
		truthFile();
	}

	public static void truthFile() {

	    try{
	        File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
	        File f2=new File("D:\\result2");
	    	if (!f2.exists()) {
	    		f2.mkdir();
	    	}
	        FileReader fr=new FileReader(f); //fr对象 对应f的读出
	        BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
	        String shuchu=null;
	        while((shuchu=br.readLine())!=null){
	        	String[] list1 = shuchu.split(":::");
	        	//元数据所在文件夹
	        	File f1 = new File("D:\\result\\"+list1[0]);
	        	if (f1.exists()) {
					File[] f1list = f1.listFiles();
					for (File file : f1list) {
						 FileReader fr1=new FileReader(file); //fr对象 对应f的读出
					        BufferedReader br1=new BufferedReader(fr1);//br是 对应fr的 读出缓冲区
				        	File f3 = new File("D:\\result2\\"+list1[0]+"\\"+file.getName().substring(0, file.getName().indexOf("."))+"\\"+"sex.txt");
				        	File f4 = new File("D:\\result2\\"+list1[0]+"\\"+file.getName().substring(0, file.getName().indexOf("."))+"\\"+"age.txt");
				        	File f5 = new File("D:\\result2\\"+list1[0]+"\\"+file.getName().substring(0, file.getName().indexOf("."))+"\\"+"name.txt");
				        	if (!f3.exists()) {
				        		if (!f3.getParentFile().exists()) {
				        			f3.getParentFile().mkdirs();
								}
				        		f3.createNewFile();
				        	}
				        	if (!f4.exists()) {
				        		if (!f4.getParentFile().exists()) {
				        			f4.getParentFile().mkdirs();
								}
				        		f4.createNewFile();
				        	}
				        	if (!f5.exists()) {
				        		if (!f5.getParentFile().exists()) {
				        			f5.getParentFile().mkdirs();
								}
				        		f5.createNewFile();
				        	}
				        	BufferedWriter bw1 = new BufferedWriter(new FileWriter(f3));
			    			BufferedWriter bw2 = new BufferedWriter(new FileWriter(f4));
			    			BufferedWriter bw3 = new BufferedWriter(new FileWriter(f5));
					        String shuchu2=null;
							while((shuchu2=br1.readLine())!=null){
								bw1.write(list1[1] + "   "+shuchu2);
								bw2.write(list1[2] + "   "+shuchu2);
								bw3.write(list1[3] + "   "+shuchu2);
								System.out.println(list1[0]+"已写完");
							}
							System.out.println("已完成！！！！！");
							br1.close(); 
							bw1.flush();
							bw1.close();
							bw2.flush();
							bw2.close();
							bw3.flush();
							bw3.close();
					}
				}
	        }
	        br.close();
	        
	    }catch(Exception ex){
	        ex.printStackTrace();
	    }


	}
	/**
	 * 获取D:\\result\\'str'下的文件夹数组
	 * @param str
	 */
	public static File[] getFile(String str) {
		File fTxt = new File("D:\\result\\"+str);
		if (fTxt.isDirectory()) {
			return fTxt.listFiles();
		}
		return null;
		
	}}
