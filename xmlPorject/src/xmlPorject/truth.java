package xmlPorject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 只有三个文档的类
 * @author king
 *
 */
public class truth {
public static void main(String[] args) {
	result3("all", "sex");
	result3("all", "name");
	result3("all", "age");
	
	result3("onlyChar", "sex");
	result3("onlyChar", "name");
	result3("onlyChar", "age");
	
	result3("onlyString", "sex");
	result3("onlyString", "name");
	result3("onlyString", "age");
	
	result3("StringEmojiMail", "sex");
	result3("StringEmojiMail", "name");
	result3("StringEmojiMail", "age");
	
	result3("StringLink", "sex");
	result3("StringLink", "name");
	result3("StringLink", "age");
}
/**
 * 生成五类文件夹和各自属性的txt文件
 */
public static void create() {

	result3("all", "sex");
	result3("all", "name");
	result3("all", "age");
	
	result3("onlyChar", "sex");
	result3("onlyChar", "name");
	result3("onlyChar", "age");
	
	result3("onlyString", "sex");
	result3("onlyString", "name");
	result3("onlyString", "age");
	
	result3("StringEmojiMail", "sex");
	result3("StringEmojiMail", "name");
	result3("StringEmojiMail", "age");
	
	result3("StringLink", "sex");
	result3("StringLink", "name");
	result3("StringLink", "age");
	System.out.println("****************************************");
	System.out.println("所有文件生成完成！");
	System.out.println("****************************************");

}

public static void result3(String str1,String str2) {

    try{
        File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
        FileReader fr=new FileReader(f); //fr对象 对应f的读出
        BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
        String shuchu=null;
        
        File file = new File("D:\\result3\\"+str1+"\\"+str2+".txt");
    	if (!file.exists()) {
    		if (!file.getParentFile().exists()) {
    			file.getParentFile().mkdirs();
			}
    		file.createNewFile();
    	}
    	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        while((shuchu=br.readLine())!=null){
        	String[] list1 = shuchu.split(":::");
        	//元数据所在文件夹
        	File f1 = new File("D:\\result2\\"+list1[0]+"\\"+str1+"\\"+str2+".txt");
        	if (f1.exists()) {
        	FileReader fr1=new FileReader(f1); 
            BufferedReader br1=new BufferedReader(fr1);
        		bw.write(br1.readLine());
        		bw.newLine();
        		br1.close();
        	}
        	
        }
        System.out.println(str1+"->"+str2+"生效！");
        bw.flush();
        bw.close();
        br.close();
       
    }catch(Exception ex){
        ex.printStackTrace();
    }}

//	/**
//	 * 所有问价的all文件夹下的Name.xml合集
//	 */
//    public static void allName() {
//    	
//    	try{
//    		File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
//    		FileReader fr=new FileReader(f); //fr对象 对应f的读出
//    		BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
//    		String shuchu=null;
//    		
//    		File allsex = new File("D:\\result3\\all\\name.txt");
//    		if (!allsex.exists()) {
//    			if (!allsex.getParentFile().exists()) {
//    				allsex.getParentFile().mkdirs();
//    			}
//    			allsex.createNewFile();
//    		}
//    		BufferedWriter allsexbw = new BufferedWriter(new FileWriter(allsex));
//    		while((shuchu=br.readLine())!=null){
//    			String[] list1 = shuchu.split(":::");
//    			//元数据所在文件夹
//    			File f1 = new File("D:\\result2\\"+list1[0]+"\\all\\name.txt");
//    			if (f1.exists()) {
//    				FileReader allsexfr=new FileReader(f1); 
//    				BufferedReader allsexbr=new BufferedReader(allsexfr);
//    				allsexbw.write(allsexbr.readLine());
//    				allsexbw.newLine();
//    				allsexbr.close();
//    			}
//    			
//    		}
//    		System.out.println("allName生成完成！");
//    		allsexbw.flush();
//    		allsexbw.close();
//    		br.close();
//    		
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
//    	
//    	
//}
//    
//    /**
//     * 所有问价的all文件夹下的Age.xml合集
//     */
//    public static void onlyCharAge() {
//    	
//    	try{
//    		File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
//    		FileReader fr=new FileReader(f); //fr对象 对应f的读出
//    		BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
//    		String shuchu=null;
//    		
//    		File allsex = new File("D:\\result3\\all\\age.txt");
//    		if (!allsex.exists()) {
//    			if (!allsex.getParentFile().exists()) {
//    				allsex.getParentFile().mkdirs();
//    			}
//    			allsex.createNewFile();
//    		}
//    		BufferedWriter allsexbw = new BufferedWriter(new FileWriter(allsex));
//    		while((shuchu=br.readLine())!=null){
//    			String[] list1 = shuchu.split(":::");
//    			//元数据所在文件夹
//    			File f1 = new File("D:\\result2\\"+list1[0]+"\\all\\age.txt");
//    			if (f1.exists()) {
//    				FileReader allsexfr=new FileReader(f1); 
//    				BufferedReader allsexbr=new BufferedReader(allsexfr);
//    				allsexbw.write(allsexbr.readLine());
//    				allsexbw.newLine();
//    				allsexbr.close();
//    			}
//    			
//    		}
//    		System.out.println("allAge生成完成！");
//    		allsexbw.flush();
//    		allsexbw.close();
//    		br.close();
//    		
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
//    	
//    	
//    }
//    
//    //------------------------------------------------------------------------
//    public static void onlyCharSex() {
//    	
//    	try{
//    		File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
//    		FileReader fr=new FileReader(f); //fr对象 对应f的读出
//    		BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
//    		String shuchu=null;
//    		
//    		File allsex = new File("D:\\result3\\onlyChar\\sex.txt");
//    		if (!allsex.exists()) {
//    			if (!allsex.getParentFile().exists()) {
//    				allsex.getParentFile().mkdirs();
//    			}
//    			allsex.createNewFile();
//    		}
//    		BufferedWriter allsexbw = new BufferedWriter(new FileWriter(allsex));
//    		while((shuchu=br.readLine())!=null){
//    			String[] list1 = shuchu.split(":::");
//    			//元数据所在文件夹
//    			File f1 = new File("D:\\result2\\"+list1[0]+"\\onlyChar\\sex.txt");
//    			if (f1.exists()) {
//    				FileReader allsexfr=new FileReader(f1); 
//    				BufferedReader allsexbr=new BufferedReader(allsexfr);
//    				allsexbw.write(allsexbr.readLine());
//    				allsexbw.newLine();
//    				allsexbr.close();
//    			}
//    			
//    		}
//    		System.out.println("onlyCharsex生成完成！");
//    		allsexbw.flush();
//    		allsexbw.close();
//    		br.close();
//    		
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}}
//    
//    /**
//     * 所有问价的all文件夹下的Name.xml合集
//     */
//    public static void onlyCharName() {
//    	
//    	try{
//    		File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
//    		FileReader fr=new FileReader(f); //fr对象 对应f的读出
//    		BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
//    		String shuchu=null;
//    		
//    		File allsex = new File("D:\\result3\\onlyChar\\name.txt");
//    		if (!allsex.exists()) {
//    			if (!allsex.getParentFile().exists()) {
//    				allsex.getParentFile().mkdirs();
//    			}
//    			allsex.createNewFile();
//    		}
//    		BufferedWriter allsexbw = new BufferedWriter(new FileWriter(allsex));
//    		while((shuchu=br.readLine())!=null){
//    			String[] list1 = shuchu.split(":::");
//    			//元数据所在文件夹
//    			File f1 = new File("D:\\result2\\"+list1[0]+"\\onlyChar\\name.txt");
//    			if (f1.exists()) {
//    				FileReader allsexfr=new FileReader(f1); 
//    				BufferedReader allsexbr=new BufferedReader(allsexfr);
//    				allsexbw.write(allsexbr.readLine());
//    				allsexbw.newLine();
//    				allsexbr.close();
//    			}
//    			
//    		}
//    		System.out.println("allName生成完成！");
//    		allsexbw.flush();
//    		allsexbw.close();
//    		br.close();
//    		
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
//    	
//    	
//    }
//    
//    /**
//     * 所有问价的all文件夹下的Age.xml合集
//     */
//    public static void allAge() {
//    	
//    	try{
//    		File f=new File("F:\\APDA\\truth.txt");//创建文件对象f
//    		FileReader fr=new FileReader(f); //fr对象 对应f的读出
//    		BufferedReader br=new BufferedReader(fr);//br是 对应fr的 读出缓冲区
//    		String shuchu=null;
//    		
//    		File allsex = new File("D:\\result3\\all\\age.txt");
//    		if (!allsex.exists()) {
//    			if (!allsex.getParentFile().exists()) {
//    				allsex.getParentFile().mkdirs();
//    			}
//    			allsex.createNewFile();
//    		}
//    		BufferedWriter allsexbw = new BufferedWriter(new FileWriter(allsex));
//    		while((shuchu=br.readLine())!=null){
//    			String[] list1 = shuchu.split(":::");
//    			//元数据所在文件夹
//    			File f1 = new File("D:\\result2\\"+list1[0]+"\\all\\age.txt");
//    			if (f1.exists()) {
//    				FileReader allsexfr=new FileReader(f1); 
//    				BufferedReader allsexbr=new BufferedReader(allsexfr);
//    				allsexbw.write(allsexbr.readLine());
//    				allsexbw.newLine();
//    				allsexbr.close();
//    			}
//    			
//    		}
//    		System.out.println("allAge生成完成！");
//    		allsexbw.flush();
//    		allsexbw.close();
//    		br.close();
//    		
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
//    	
//    	
//    }
}
