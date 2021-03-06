package xmltest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

public class EmojiFilter { 
	public static void main(String[] args) {
		String a = "@MahmoudAhmad254لابطلتمتحمسة.جدبسالانترنيتimo؟انارديتعلىحداعاساسانت؟\r\n" + 
				"";
//		System.out.println(isEmail(a));
		System.out.println(filterEmoji(a));
	}
	
	
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
public static Boolean isEmail(String email) {
	Pattern pattern = Pattern.compile("[a-zA-Z]([a-zA-Z]|[.])*@[a-zA-Z]+([.][a-zA-Z]+)+");
    return pattern.matcher(email).matches();
}

private static String getALlAdress(String email){ 
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