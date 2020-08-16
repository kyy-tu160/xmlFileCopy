package xmltest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

public class isCn {
	public static void main(String[] args) {
	    String str1 = "acsasc🙏🏻💝ascacascaسؤالإلاكنتعارفراسكغاديتموتولكنعندكتختاركيفاشتموتواش";
	 	String str2 = "acsasc🙏🏻💝";
//	    String str3 = "لقد ترك لنا الأسلاف من أجدادنا الكثير من التراث الشعبي الذي يحق لنا أن نفتخر به ونحافظ عليه ونطوره ليبقى ذخراً لهذا الوطن والأجيال القادمةشوي 🙏🏻💝@";
//	    String str4 = "@AlWahdaFCC حق شو تتدربون";
	    System.out.println(filterEmoji(str1));
	    System.out.println(isArabic(str1)&&containsEmoji(str1)&&isMail(str1));
//	    System.out.println(onlyArabic(str2));
//	    System.out.println(isArabic(str2));
//	    System.out.println(isMail(str2));
//	    System.out.println(isMail(str4));
	}
	public static Boolean name(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		  return str.matches(regex);
	}
	
	public static Boolean onlyArabic(String str) {
		String reg = "[\\u0600-\\u06FF]+";
		return str.replaceAll(" ", "").matches(reg);
	}
	
	public static Boolean isArabic(String str) {
		Pattern p = Pattern.compile("[\u0600-\u06FF]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
	}
	public static boolean isLetterDigitOrChinese(String str) {
		  String regex = "^[\u0600-\u06FF]+$";
		  return str.matches(regex);
		 }
	
	public static Boolean isMail(String str) {
		return str.contains("@");
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
    public static String filterString(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isArabic(codePoint)) {
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
	private static boolean isArabic(char codePoint) {
		return String.valueOf(codePoint).matches("[\u0600-\u06FF]");
	}
	private static String replaceString(String codePoint) {
		codePoint = codePoint.replaceAll("[\\u0600-\\u06FF]", "");
	 return codePoint;}
	
	public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
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
		return String.valueOf(codePoint).matches("[\u10000-\u1F9FF]");
		}
}
